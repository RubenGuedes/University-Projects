%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Auxiliar Predicates
 */
% Ge heuristic
get_heuristic(node(_, _, _, Heu, _, _), Heu).

% Insert in list
insert_begin(L1, L2, Res) :- append(L1, L2, Res).

% List with quantity of visited states and max quantity of states in memory
update_mem([Vis, Max], VisVal, MaxVal, [VisRes, MaxRes]) :-
    VisRes is Vis + VisVal,
    MaxRes is max(Max,MaxVal).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Merge sort
 */
mergeSort([], []).
mergeSort([X], [X]).
mergeSort(List, Sorted) :-
    divide(List, L1, L2),
    mergeSort(L1, Sorted1),
    mergeSort(L2, Sorted2),
    merge(Sorted1, Sorted2, Sorted).
% merge
merge([], L, L).
merge(L ,[], L).
merge([X|T1], [Y|T2], [X|T]) :-
    get_heuristic(X, HeuX),
    get_heuristic(Y, HeuY),
    @<(HeuX, HeuY),
    merge(T1, [Y|T2], T).
merge([X|T1], [Y|T2], [Y|T]) :-
    get_heuristic(X, HeuX),
    get_heuristic(Y, HeuY),
    @>=(HeuX, HeuY),
    merge([X|T1], T2, T).
% divide
divideAux( [Head|Tail], Start, End, AuxList, AuxList, [Head|Tail]) :-
    Start = End, !.
divideAux( [Head|Tail], Start, End, EmptyList, L1, L2) :-
    @<(Start, End),
    append(EmptyList, [Head], LR1),
    Start1 is Start + 1,
    divideAux( Tail, Start1, End, LR1, L1, L2).
divide([], [], []).
divide([R], [R], []).
divide(List, L1, L2) :-
    length(List, Size),
    Half is floor(Size / 2),
    divideAux(List, 0, Half, [], L1, L2).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Search Algorithm
 */
% Expand node
expand( node(Act, Pai, Op, Heu, C, P), ResultList) :-
    findall(
        node(Next, node(Act, Pai, Op, Heu, C, P), OpNext, HeuNext, CNext, P1),
        (op(Act, OpNext, Next, CN), P1 is P + 1, CNext is CN + C, h(Next, Heur), HeuNext is Heur + CNext),
        ResultList
    ).

% A* Search
a_star([node(Est, Op, Pai, Heur, Cost, P)|_], ListMem, node(Est, Op, Pai, Heur, Cost, P), ListMemR) :-
    final_state(Est),
    update_mem(ListMem, 1, 0, ListMemR), !.
a_star([node(Est, Op, Pai, Heur, Cost, P)|Tail], ListMem, Solution, ListMemR) :-
    % Expande nó -> Colocar nós expandidos no início -> Ordenar lista por ordem crescente da heuristica
    expand(node(Est, Op, Pai, Heur, Cost, P), Expanded),
    insert_begin(Expanded, Tail, LAdded),
    mergeSort(LAdded, SortedList),
    % Guardar o nó visitado assim como o número máximo de estados em memória
    length(SortedList, SizeSortedList),
    update_mem(ListMem, 1, SizeSortedList, NListMem),
    % Chamada recursiva
    a_star(SortedList, NListMem, Solution, ListMemR).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Main Predicate
 */
search :-
    initial_state(Init),
    h(Init, Heur),
    a_star([node(Init, [], [], Heur, 0, 0)], [0,0], Solution, [Visited,MaxInMem]),
    writeln("\nSolução:"),
    writeln(Solution),
    write("\n"),
    write("O número total de estados visitados foram: "), writeln(Visited),
    write("O número máximo de estados que estiveram simultaneamente em memória foram: "), writeln(MaxInMem),
    write("\n"), !.
