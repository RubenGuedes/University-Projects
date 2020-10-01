%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                  AUXILIAR PREDICATES

%%%%%%%%%%%%%%%%%% UNTRIED %%%%%%%%%%%%%%%%%%
/**
 * Search Action on Untried
 */
untried_get_action([[State, Action]|_], State, Action).
untried_get_action([_|Tail], State, ReturnAction) :-
    untried_get_action(Tail, State, ReturnAction).

/**
 * Get a list where all State appear
 */
untried_list_state(Untried, State, NList) :-
    findall(
	[State, Action],
	member( [State, Action], Untried ),
	NList
    ).
/**
 * Pop untried
 */
pop_untried(List, State, NAction, NList) :-
    untried_get_action(List, State, NAction),
    delete(List, [State, NAction], NList).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%% UNBACKTRACKED %%%%%%%%%%%%%%%%%%
/**
 * Search State on Unbacktracked
 */
unbacktracked_get_state([[State, Prev]|_], State, Prev).
unbacktracked_get_state([_|Tail], State, ReturnPrev) :-
    untried_get_action(Tail, State, ReturnPrev).

/**
 * Get a list where all State appear
 */
unbactracked_list_state(Unbactracked, State, NList) :-
    findall(
	[State, PrevState],
	member( [State, PrevState], Unbactracked),
	NList
    ).

/**
 * Pop unbactracked
 */
pop_unbacktracked(List, State, NState, NList) :-
    unbactracked_get_state(List, State, NState),
    delete(List, [State, NState], NList).

/**
 * Find action on result
 */
find_action([ [PrevState, NAction, State]|_], PrevState, State, NAction).
find_action([_|Tail], PrevState, State, NAction) :-
    find_action(Tail, PrevState, State, NAction).
/**
 * Element in list
 */
in_list(Cont, List) :- member(Cont, List).
/**
 * Add front
 */
add_front(Cont, List, NList) :- append(Cont, List, NList).
/**
 * linha 7 do pseudo código a contar com a posição das variaveis persistentes
 */
actions(List, State, NList) :-
    findall(
	[State, Op],
	(op(State, Op, _, _), \+ member([State, Op], List)),
	ListOps
    ),
    add_front(ListOps, List, NList).
/**
 * linhas 8 a 10 do pseudo código
 */
add_front_backtracking(
    StateInput, PrevState, Action, Result, Unbacktracked, NResult, NUnbacktracked
) :-
    add_front([PrevState, Action, StateInput], Result, NResult),
    add_front([StateInput, PrevState], Unbacktracked, NUnbacktracked).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
online_dfs_agent(
    State, Result, Untried, Unbacktracked, S, A, NResult, NUntried, NUnbacktracked, State, NAction
) :-
    % if State is final state -> stop
    final_state(State) -> NAction = stop;
    % else -> continue
    (
	% if s is not in untried the untried[s'] <- Actions(s')
	(
	    \+ in_list([State, _], Untried) -> (actions(Untried, State, UntriedAux)); (UntriedAux = Untried)
	),
	% if s id not null
	(
	    S \= [] -> add_front_backtracking(State, S, A, Result, Unbacktracked, NResult, UnbacktrackedAux); (NResult = Result, UnbacktrackedAux = Unbacktracked)
	),
	(
	    % If untried[s'] is empty then
	    untried_list_state(UntriedAux, State, []) -> (
		% if unbacktracked[s'] is empty then
		unbactracked_list_state(UnbacktrackedAux, State, []) -> (
		    % return stop
		    NAction = stop, !
		);
		% else a <- action b such result[s',b] == POP(Unbacktracked[s']
		(
		    pop_unbacktracked(UnbacktrackedAux, State, StateAux, NUnbacktracked),
		    find_action(Result, State, StateAux, NAction)
		)
	    );
	    % else a <- POP(Untried[s'])
	    pop_untried(UntriedAux, State, NAction, NUntried)
	)
    ).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Search Algorithm
 */
search_algorithm(node(State, S, A, Cost), Res, Untr, Unback, node(State, S, A, Cost)) :- 
    online_dfs_agent(State, Res, Untr, Unback, S, A, _, _, _, _, stop).

search_algorithm( node(State, S, A, Cost), Result, Untried, Unbacktracked, Answer) :-
    online_dfs_agent(
	State, Result, Untried, Unbacktracked, S, A, NResult, NUntried, NUnbacktracked, NPreviouState, NAction
    ),
    op(State, NAction, NewState, NCost),
    NewCost is NCost + Cost,
    search_algorithm(node(NewState, node(NPreviouState, S, A, Cost), NAction, NewCost), NResult, NUntried, NUnbacktracked, Answer).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Create two lists with visited rooms and actions
 */
get_actions_states(node( State, _, _, 0), States, Actions, NStates, Actions) :-
    append([State], States, NStates).
get_actions_states(node(State, Parent, Action, _), States, Actions, NStates, NActions) :-
    append([State], States, AuxStates),
    append([Action], Actions, AuxActions),
    get_actions_states(Parent, AuxStates, AuxActions, NStates, NActions).
/**
 * Get quantity of visited rooms 
 */
get_rooms(node(_, _, _, Rooms), Rooms).
/**
 * Print state and actions
 */
print_state_actions([State|_], []) :-
    write("-> "), writeln(State), writeln("").
print_state_actions([H1|T1], [H2|T2]) :-
    write("-> "), write(H1), write(" + "), writeln(H2),
    print_state_actions(T1, T2).
/**
 * Init Search
 */
search :-
    initial_state(Init),
    search_algorithm(node(Init, [], [], 0), [], [], [], Result),
    % Get info from node
    get_actions_states(Result, [], [], StateList, ActionsList),
    get_rooms(Result, Rooms),
    % Print content
    writef("Resulted Node: \n'%w'\n\n", [Result]),
    writeln("Caminho seguido até chegar à saída:"),
    print_state_actions(StateList, ActionsList),
    writef("Número de quartos visitados no total: \n'%w' quartos.\n\n", [Rooms]), !.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
