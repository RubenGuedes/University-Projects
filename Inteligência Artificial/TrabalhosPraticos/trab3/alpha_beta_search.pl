next_player(o, x).
next_player(x, o).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Predicado para medir o tempo
test_time :-
    Player = o,
    example_state(State),
    alpha_beta_search(State, Player, Action),
    writef("A ação que irá ser tomada é '%w'.\n", [Action]),
    halt.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
play :-
    example_state_game(Init),
    game(Init, x). % Começa a pessoa

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
game(State, _) :-
    final_state(State, Winner),
    writef("O vencedor é '%w'!", [Winner]),
    print_game(State).

% Pessoa a jogar
game(State, x) :-
    print_game(State),
    % Pedir ação
    move(Action),
    op(State, Action, x, NState),
    next_player(x, NPlayer),
    game(NState, NPlayer).

% Computador a jogar
game(State, o) :-
    alpha_beta_search(State, o, Action),
    op(State, Action, o, NState),
    next_player(o, NPlayer),
    game(NState, NPlayer).  

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
move(Action) :-
    writeln("Jogadas:
    0 -> Inserir 'x' na coluna 0 (Mais à esquerda)
    1 -> Inserir 'x' na coluna 1
    2 -> Inserir 'x' na coluna 2
    3 -> Inserir 'x' na coluna 3
    4 -> Inserir 'x' na coluna 4 (Mais à direita)
    "),
    read(Option),
    interpreter_move(Option, Action).

interpreter_move(0, insert_col0).
interpreter_move(1, insert_col1).
interpreter_move(2, insert_col2).
interpreter_move(3, insert_col3).
interpreter_move(4, insert_col4).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
alpha_beta_search(State, Player, MaxAction) :-
    findall(
	Action-Utility,
	(
	    op(State, Action, Player, NState),
	    min_value(NState, -100, 100, Player, Utility)
	),
	ListActUtil
    ),
    max_action(ListActUtil, MaxAction).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
max_action([Head|Tail], Action) :-
    max_action(Tail, Head, Action).
max_action([], Act-_, Act).
max_action([_-Util|Tail], PrevAct-PrevUtil, Act) :-
    @<(Util, PrevUtil), !,
    max_action(Tail, PrevAct-PrevUtil, Act).
max_action([Head|Tail], _, Act) :-
    max_action(Tail, Head, Act).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
max_value(State, _, _, _, Utility) :-
    final_state(State, Player),
    utility(Player, Utility), !.

max_value(State, A, B, Player, Utility) :-
    next_player(Player, NPlayer),
    V is -100,
    findall(
	NState,
	op(State, _, NPlayer, NState),
	ListNextState
    ),
    actions_max(ListNextState, A, B, NPlayer, V, Utility), !.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
actions_max([], _, _, _, Utility, Utility).
actions_max([State|Tail], A, B, Player, V, Result) :-
    min_value(State, A, B, Player, Util),
    max(Util, V, MaxUtil),
    (
	@>=(MaxUtil, B), Result is MaxUtil;
	max(A, MaxUtil, NewA), actions_max(Tail, NewA, B, Player, MaxUtil, Result)
    ).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
min_value(State, _, _, _, Utility) :-
    final_state(State, Player),
    utility(Player, Utility), !.

min_value(State, A, B, Player, Utility) :-
    next_player(Player, NPlayer),
    V is 100,
    findall(
	NState,
	op(State, _, NPlayer, NState),
	ListNextState
    ),
    actions_min(ListNextState, A, B, NPlayer, V, Utility), !.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
actions_min([], _, _, _, Utility, Utility).
actions_min([State|Tail], A, B, Player, V, Result) :-
    max_value(State, A, B, Player, Util),
    min(Util, V, MinUtil),
    (
	@=<(MinUtil, A), Result is MinUtil;
	min(B, MinUtil, NewB), actions_min(Tail, A, NewB, Player, MinUtil, Result)
    ).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Minimum value
min(Value1, Value2, Value1) :-
    @=<(Value1, Value2).
min(Value1, Value2, Value2) :-
    @>(Value1, Value2).

% Maximum value
max(Value1, Value2, Value1) :-
    @>=(Value1, Value2).
max(Value1, Value2, Value2) :-
    @<(Value1, Value2).
