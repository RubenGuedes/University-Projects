:- [problema].

:- set_prolog_stack(global, limit(2 000 000 000)).
:- set_prolog_stack(trail,  limit(2 000 000 000)).
:- set_prolog_stack(local,  limit(2 000 000 000)).

/**
 * Jogadores
 */
computer(0).
adversary(1).

/**
 * Dado o tempo atual, adiciona "Timer" segundos
 * a esse tempo.
 */
stop_at_time(Timer, STOPTIME) :-
    get_time(ACT_TIME),
    STOPTIME is ACT_TIME + Timer.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
best :-
    timer5(Timer),
    stop_at_time(Timer, STOP),
    initial_state(Board),
    minimax_decision(Board, 0, STOP, Action),
    writef("A melhor jogada escolhida por este algoritmo foi: jogar na coluna '%w'.", [Action]).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
play :-
    % Configurations
    computer(Player),
    timer5(Timer),
    % Start
    initial_state(Board),
    game_cycle(Board, Player, Timer), !.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
game_cycle(game(Score, Board), _, _) :-
    final_state(game(Score, Board)),

    print_board(game(Score, Board)),
    winner(Score, Winner),
    writeln(Winner), !.

% Computador
game_cycle(Board, 0, Timer) :-
    print_board(Board),
    
    stop_at_time(Timer, STOP),
    minimax_decision(Board, 0, STOP, Action),
    op(Board, Action, 0, NBoard),

    Act is 6-Action,
    writef("O computador jogou na posição '%w'\n", [Act]),
    
    next_player(0, NPlayer),
    pass_beans(NBoard, 0, PassOrNot, ResBoard),
    (
        (PassOrNot =:= -1) ->
	    (finish_game(ResBoard));
	    (game_cycle(ResBoard, NPlayer, Timer))
    ).

% Adversário
game_cycle(Board, 1, Timer) :-
    print_board(Board),

    option(Act),
    op(Board, Act, 1, NBoard),
    
    next_player(1, NPlayer),
    pass_beans(NBoard, 1, PassOrNot, ResBoard),
    (
	    (PassOrNot =:= -1) ->
	    ( finish_game(ResBoard) );
	    ( game_cycle(ResBoard, NPlayer, Timer) )
    ).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
option(Action) :-
    writeln("É a sua vez a jogar!\nJogadas possíveis:
  1 -> Inserir na coluna 1 (Mais à esquerda)
  2 -> Inserir na coluna 2
  3 -> Inserir na coluna 3
  4 -> Inserir na coluna 4
  5 -> Inserir na coluna 5
  6 -> Inserir na coluna 6 (Mais à direita)"
  ),
  read(Option), interpreter_move(Option, Action).
/**
 * interpreter_move(+Val, -Res)
 */
interpreter_move(Val, Res) :- Res is Val - 1.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Caso não haja mais jogadas :-
 * o programa recolhe os feijões relativos aos 2 jogados,
 * adicionar os feijões recolhidos às pontuações de cada jogador,
 * e, por fim, mostra o vencedor
 * 
 * finish_game(+State)
 */
finish_game(State) :-
    % Recolhe os feijões
    remove_beans_line(  State, 0, Collected0,  RBState),
    remove_beans_line(RBState, 1, Collected1, FRBState),
    
    % Adicionar à pontuação de cada jogador
    store_beans(   FRBState, Collected0, 0,     StoreState ),
    store_beans( StoreState, Collected1, 1, game(Score, Map) ),
    
    % Apresenta o vencedor.
    print_board(game(Score, Map)),
    winner(game(Score, _), Winner),
    writeln(Winner), !.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
minimax_decision(Board, Player, STOP, Action) :-
    get_score_player(Board, Player, PrevScore),
    findall(
	    Col-Utility,
	    (
            op(Board, Col, Player, NBoard),
	        min_value(NBoard, Player, STOP, PrevScore, Utility)
	    ),
	    ListAction
    ),
    arg_max(ListAction, Action).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
arg_max([Head|Tail], Action) :-
    arg_max(Tail, Head, Action).
arg_max([], Act-_, Act).
arg_max([_-Util|Tail], PrevAct-PrevUtil, Act) :-
    Util < PrevUtil, !,
    arg_max(Tail, PrevAct-PrevUtil, Act).
arg_max([Head|Tail], _, Act) :-
    arg_max(Tail, Head, Act).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
max_value(Board, Player, STOP, PrevScore, Utility) :-
    get_time(Act),
    Act >= STOP, !,
    get_score_player(Board, Player, ActScore),
    compare_score(PrevScore, ActScore, Utility).

max_value(game(Score, Map), _, _, _, Utility) :-
    final_state(game(Score, Map)), !,
    utility(Score, Utility).

max_value(Board, Player, STOP, PrevScore, MinUtil) :-
    next_player(Player, NPlayer),
    findall(
	    Utility,
	    (
	        op(Board, _, NPlayer, NBoard),
	        pass_beans(NBoard, NPlayer, _, FBoard),
	        min_value(FBoard, NPlayer, STOP, PrevScore, Utility)
	    ),
	    ListUtil
    ),
    maximum(ListUtil, MinUtil).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
min_value(Board, Player, STOP, PrevScore, Utility) :-
    get_time(Act),
    Act >= STOP, !,
    get_score_player(Board, Player, ActScore),
    compare_score(PrevScore, ActScore, Utility).

min_value(game(Score, Map), _, _, _, Utility) :-
    final_state(game(Score, Map)), !,
    utility(Score, Utility).

min_value(Board, Player, STOP, PrevScore, MinUtil) :-
    next_player(Player, NPlayer),
    findall(
	    Utility,
	    (
	        op(Board, _, NPlayer, NBoard),
	        pass_beans(NBoard, NPlayer, _, FBoard),
	        max_value(FBoard, NPlayer, STOP, PrevScore, Utility)
	    ),
	    ListUtil
    ),
    minimum(ListUtil, MinUtil).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
maximum([Head|Tail], Max) :-
    maximum(Tail, Head, Max).
maximum([], Max, Max).
maximum([Head|Tail], PrevUtil, Max) :-
    Head < PrevUtil, !,
    maximum(Tail, PrevUtil, Max).
maximum([Head|Tail], _, Max) :-
    maximum(Tail, Head, Max).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
minimum([Head|Tail], Min) :-
    minimum(Tail, Head, Min).
minimum([], Min, Min).
minimum([Head|Tail], PrevUtil, Min) :-
    Head > PrevUtil, !,
    minimum(Tail, PrevUtil, Min).
minimum([Head|Tail], _, Min) :-
    minimum(Tail, Head, Min).
