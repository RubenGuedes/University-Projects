% Jogador
next_player(o, x).
next_player(x, o).
/*
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
play :-
    initial_state(State),
    Player = x,
    writef("Começou o jogador: '%w'.\n\nJogadas:\n", [Player]),
    game(State, Player, Winner, _FinalState),
    writef("\nO vencedor do jogo é '%w'.\n\n", [Winner]), !.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Game
game(State, _, Player, State) :-
    final_state(State, Player).
game(State, Player, Winner, NS) :-
    minimax_decision(State, Player, Act),
    op(State, Act, Player, NState),
    writef("    Player: '%w' ; Estado: '%w'\n", [Player, NState]),
    next_player(Player, NPlayer),
    game(NState, NPlayer, Winner, NS).
*/
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
play :-
    Player = o, % Queremos o máximo desta peça
    example_state(State),
    minimax_decision(State, Player, Action),
    writef("A ação que irá ser tomada é '%w'.\n", [Action]),
    halt.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
minimax_decision(State, Player, Action) :-
    findall(
	Act-Utility,
	(
	    op(State, Act, Player, NState),
	    min_value(NState, Player, Utility)
	),
	ListAction
    ),
    arg_max(ListAction, Action).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
arg_max([Head|Tail], Action) :-
    arg_max(Tail, Head, Action).
arg_max([], Act-_, Act).
arg_max([_-Util|Tail], PrevAct-PrevUtil, Act) :-
    @<(Util, PrevUtil), !,
    arg_max(Tail, PrevAct-PrevUtil, Act).
arg_max([Head|Tail], _, Act) :-
    arg_max(Tail, Head, Act).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
max_value(State, _, Utility) :-
    final_state(State, Player),
    utility(Player, Utility).

max_value(State, Player, MaxUtil) :-
    next_player(Player, NPlayer),
    findall(
	Utility,
	(
	    op(State, _, NPlayer, NState), 
	    min_value(NState, NPlayer, Utility)
	),
	ListUtil
    ),
    maximum(ListUtil, MaxUtil).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
min_value(State, _, Utility) :-
    final_state(State, Player),
    utility(Player, Utility).

min_value(State, Player, MinUtil) :-
    next_player(Player, NPlayer),
    findall(
	Utility,
	(
	    op(State, _, NPlayer, NState),
	    max_value(NState, NPlayer, Utility)
	),
	ListUtil
    ),
    minimum(ListUtil, MinUtil).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
maximum([Head|Tail], Max) :-
    maximum(Tail, Head, Max).
maximum([], Max, Max).
maximum([Head|Tail], PrevUtil, Max) :-
    @<(Head, PrevUtil), !,
    maximum(Tail, PrevUtil, Max).
maximum([Head|Tail], _, Max) :-
    maximum(Tail, Head, Max).

minimum([Head|Tail], Min) :-
    minimum(Tail, Head, Min).
minimum([], Min, Min).
minimum([Head|Tail], PrevUtil, Min) :-
    @>(Head, PrevUtil), !,
    minimum(Tail, PrevUtil, Min).
minimum([Head|Tail], _, Min) :-
    minimum(Tail, Head, Min).
