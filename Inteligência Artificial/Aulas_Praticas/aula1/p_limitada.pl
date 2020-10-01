% Expand node
expand( node(Act, Pai, Op, C, P), ResultList ) :-
    findall(
        node(Next, node(Act, Pai, Op, C, P), OpNext, CNext, P1),
        ( op(Act, OpNext, Next, CN), P1 is P + 1, CNext is CN + 1 ),
        ResultList
    ).

% Insert in list
insert_begin(L1, L2, Res) :- append(L1, L2, Res).

% Iterative Deeping Search Algorithm
recursive_dls([node(Est, Op, Pai, Cost, P)|_], Limit, node(Est, Op, Pai, Cost, P)) :-
    P < Limit,
    estado_final(Est), !.
recursive_dls([node(Est, Op, Pai, Cost, P)|Tail], Limit, Solution) :-
    P < Limit,
    expand(node(Est, Op, Pai, Cost, P), ListNext),
    insert_begin(ListNext, Tail, LAdded), 
    recursive_dls(LAdded, Limit, Solution).
recursive_dls([node(_, _, _, _, P)|Tail], Limit, Solution) :-
    P == Limit,
    recursive_dls(Tail, Limit, Solution).

% Iterative Search
iterative(Node, Limit, Solution) :-
    recursive_dls(Node, Limit, Solution).
iterative(Node, Limit, Solution) :-
    Limit1 is Limit + 1,
    iterative(Node, Limit1, Solution).

% Get depth
get_depth(node(_, _, _, _, Depth), Depth).

% Main Predicate
search :-
    estado_inicial(Init),
    iterative([node(Init, [], [], 0, 0)], 1, Solution),
    get_depth(Solution, Depth),
    writeln(Solution),
    write("A solução está no nível "), writeln(Depth), !.
