/**
 * Auxiliar Predicates
 */
% Insert in list
insert_begin(L1, L2, Res) :- append(L1, L2, Res).

% Get depth
get_depth(node(_, _, _, _, Depth), Depth).

% List with quantity of visited states and max quantity of states in memory
update_mem([Vis, Max], VisVal, MaxVal, [VisRes, MaxRes]) :-
    VisRes is Vis + VisVal,
    MaxRes is max(Max,MaxVal).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Search Algorithm
 */
% Expand node
expand( node(Act, Pai, Op, C, P), ResultList ) :-
    findall(
        node(Next, node(Act, Pai, Op, C, P), OpNext, CNext, P1),
        ( op(Act, OpNext, Next, CN), P1 is P + 1, CNext is CN + C ),
        ResultList
    ).

% Iterative Deeping Search Algorithm
recursive_dls([node(Est, Op, Pai, Cost, P)|_], Limit, ListMem, node(Est, Op, Pai, Cost, P), ListMemR) :-
    P < Limit,
    final_state(Est),
    update_mem(ListMem, 1, 0, ListMemR), !.
recursive_dls([node(Est, Op, Pai, Cost, P)|Tail], Limit, ListMem, Solution, ListMemR) :-
    P < Limit,
    expand(node(Est, Op, Pai, Cost, P), ListNext),
    insert_begin(ListNext, Tail, LAdded),
    % Atualizar o número de nós acedidos assim como o número máximos de nós em memória
    length(LAdded, LenAdded),
    update_mem(ListMem, 1, LenAdded, ListResult),
    recursive_dls(LAdded, Limit, ListResult, Solution, ListMemR).
recursive_dls([node(_, _, _, _, P)|Tail], Limit, ListMem, Solution, ListMemR) :-
    P == Limit,
    update_mem(ListMem, 1, 0, LAdded),
    recursive_dls(Tail, Limit, LAdded, Solution, ListMemR).

% Iterative Search
iterative(Node, Limit, ListMem, Solution, ListMemR) :-
    recursive_dls(Node, Limit, ListMem, Solution, ListMemR).
iterative(Node, Limit, ListMem, Solution, ListMemR) :-
    Limit1 is Limit + 1,
    iterative(Node, Limit1, ListMem, Solution, ListMemR).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Main Predicate
 */
search :-
    initial_state(Init),
    iterative([node(Init, [], [], 0, 0)], 1, [0,0], Solution, [Visited, MaxInMem]),
    writeln("\nSolução:"),
    writeln(Solution),
    write("\n"),
    write("O número total de estados visitados foram: "), writeln(Visited),
    write("O número máximo de estados que estiveram simultaneamente em memória foram: "), writeln(MaxInMem),
    write("\n"), !.
