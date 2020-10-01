% Estrutura de um nó
% node(EstAct, Pai, OP, Cost, Depth)

% Expandir um nó
expande( node(Act, Pai, Op, C, P), ResultList ) :-
    findall(
        node(Next, node(Act, Pai, Op, C, P), OpNext, CNext, P1),
        ( op(Act, OpNext, Next, CN), P1 is P + 1, CNext is CN + 1 ),
        ResultList
    ).

% Insere novos nós no fim da lista
insere([], L, L) :- !.
insere(L, [], L) :- !.
insere(Ele, [Head|Tail], [Head|Body]) :-
    insere(Ele, Tail, Body).

% Algoritmo de pesquisa em largura
pesquisa_largura([node(Act, Pai, Op, C, P)|_], node(Act, Pai, Op, C, P)) :- 
    estado_final(Act).
pesquisa_largura([Ele|Next], Result) :-
    expande(Ele, ResultList),
    insere(ResultList, Next, FinalList),
    pesquisa_largura(FinalList, Result).

% Pesquisa
pesquisa :-
    estado_inicial(Est),
    pesquisa_largura([node(Est, [], [], 0, 0)], Result),
    writeln(Result), !.