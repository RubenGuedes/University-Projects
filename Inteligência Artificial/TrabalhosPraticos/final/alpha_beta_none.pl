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

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Os dois predicados abaixo servirão como
 * argumentos.
 *
 * swipl -t <predicado> alpha_beta.pl
 */
-p :- computer(Val),  main(Val).
-s :- adversary(Val), main(Val).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * main(+Turn)
 */
main(Turn) :-
    timer15(Timer),               % Temporizador
    initial_state(Init),          % Estado Inicial
    game_ouri(Init, Turn, Timer). % O jogo começa

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * game_ouri(+Board, +Player, +Timer)
 */
game_ouri(game(Score, Board), 0, _) :-
    final_state(game(Score, Board)),
    % O computador não consegue fazer mais jogadas (Devolve o valor zero)
    write_ln(0),
    % Procura o vencedor e imprime-o
    winner(Score, Winner),
    writeln(Winner).

% Computador a jogar
game_ouri(State, 0, Timer) :-
    stop_at_time(Timer, STOP),
    alpha_beta_search(State, 0, STOP, Action),
    Act is 6 - Action,
    writeln(Act), % Imprime no terminal a coluna que o computador jogou
    
    op(State, Action, 0, NState),
    
    next_player(0, NPlayer),
    pass_beans(NState, NPlayer, PassOrNot, FState),
    (
        (PassOrNot =:= 1) ->
	    % Joga e passa a vez
	    (game_ouri(FState, NPlayer, Timer), !);   
	    % Não há jogada possivel, recolhe os restantes feijões e termina
	    (finish_game(FState), !)
    ).

%%%%%%%%%%%%%%%%
game_ouri(game(Score, Board), 1, _) :-
    final_state(game(Score, Board)),
    winner(Score, Winner),
    writeln(Winner).

% Pessoa a jogar
game_ouri(State, 1, Timer) :-
    % Pedir ação
    move(Action), 
    (
        % Se não houver jogadas possiveis -> o jogo acaba
        (Action =:= -1) ->
        (finish_game(State), !);
        (
            op(State, Action, 1, NState),
            next_player(1, NPlayer),
            pass_beans(NState, NPlayer, PassOrNot, FState),
            (
                (PassOrNot =:= 1) ->
                % Joga e passa a vez
	            (game_ouri(FState, NPlayer, Timer), !);
        	    % Não há jogada possivel, recolhe os restantes feijões e termina
	            (finish_game(FState), !)
            )
        )
    ).

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

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * move(-Action)
 */
move(Action) :-
  read(Option),
  interpreter_move(Option, Action).
/**
 * interpreter_move(+Val, -Res)
 */
interpreter_move(Val, Res) :- Res is Val - 1.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * alpha_beta_search(+State, +Player, +STOP, -MaxAction)
 */
alpha_beta_search(State, Player, STOP, MaxAction) :-
    get_score_player(State, Player, PrevScore),
    findall(
	    Action-Utility,
	    (
	        op(State, Action, Player, NState),
	        min_value(NState, -100, 100, Player, STOP, PrevScore, Utility)
	    ),
	    ListActUtil
    ),
    max_action(ListActUtil, MaxAction).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
max_action([Head|Tail], Action) :-
    max_action(Tail, Head, Action).
max_action([], Act-_, Act).
max_action([_-Util|Tail], PrevAct-PrevUtil, Act) :-
    Util < PrevUtil, !,
    max_action(Tail, PrevAct-PrevUtil, Act).
max_action([Head|Tail], _, Act) :-
    max_action(Tail, Head, Act).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * max_value(+Board, +A, +B, +Player, +STOP, +PrevScore, -Utility)
 */
max_value(Board, _, _, Player, STOP, PrevScore, Utility) :-
    get_time(Act),
    Act >= STOP, !,
    get_score_player(Board, Player, ActScore),
    compare_score(PrevScore, ActScore, Utility).

max_value(game(Score, Map), _, _, _, _, _, Utility) :-
    final_state( game(Score, Map) ), !,
    utility(Score, Utility).

max_value(State, A, B, Player, STOP, PrevScore, Utility) :-
    next_player(Player, NPlayer),
    V is -100,
    findall(
	    FState,
	    (
	        op(State, _, NPlayer, NState),
	        pass_beans(NState, NPlayer, _, FState)
	    ),
	    ListNextState
    ),
    actions_max(ListNextState, A, B, NPlayer, STOP, PrevScore, V, Utility), !.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * actions_max(+List, +A, +B, +Player, +STOP, +V, -Result)
 */
actions_max([], _, _, _, _, _, Utility, Utility).
actions_max([State|Tail], A, B, Player, STOP, PrevScore, V, Result) :-
    min_value(State, A, B, Player, STOP, PrevScore, Util),
    max(Util, V, MaxUtil),
    (
	    MaxUtil >= B, Result is MaxUtil;
	    max(A, MaxUtil, NewA), actions_max(Tail, NewA, B, Player, STOP, PrevScore, MaxUtil, Result)
    ).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * min_value(+Board, +A, +B, +Player, +STOP, -Utility)
 */
min_value(Board, _, _, Player, STOP, PrevScore, Utility) :-
    get_time(Act),
    Act >= STOP, !,
    get_score_player(Board, Player, ActScore),
    compare_score(PrevScore, ActScore, Utility).

min_value(game(Score, Map), _, _, _, _, _, Utility) :-
    final_state(game(Score, Map)), !,
    utility(Score, Utility).

min_value(State, A, B, Player, STOP, PrevScore, Utility) :-
    next_player(Player, NPlayer),
    V is 100,
    findall(
	    FState,
	    (
	        op(State, _, NPlayer, NState),
	        pass_beans(NState, NPlayer, _, FState)
	    ),
	    ListNextState
    ),
    actions_min(ListNextState, A, B, NPlayer, STOP, PrevScore, V, Utility), !.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * actions_min( +ListNextState, +A, +B, +Player, +Stop, +PrevScore, +V, -Utility)
 */
actions_min([], _, _, _, _, _, Utility, Utility).
actions_min([State|Tail], A, B, Player, STOP, PrevScore, V, Result) :-
    max_value(State, A, B, Player, STOP, PrevScore, Util),
    min(Util, V, MinUtil),
    (
	    MinUtil =< A, Result is MinUtil;
	    min(B, MinUtil, NewB), actions_min(Tail, A, NewB, Player, STOP, PrevScore, MinUtil, Result)
    ).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Minimum value
 * min(+Value1, +Value2, -Value)
 */
min(Value1, Value2, Value1) :-
    Value1 =< Value2.
min(Value1, Value2, Value2) :-
    Value1 > Value2.

/**
 * Maximum value
 * max(+Value1, +Value2, -Value)
 */
max(Value1, Value2, Value1) :-
    Value1 >= Value2.
max(Value1, Value2, Value2) :-
    Value1 < Value2.
