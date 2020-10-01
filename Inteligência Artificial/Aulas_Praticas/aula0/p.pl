homem('Afonso Henriques','rei de Portugal',1109).
homem('Henrique de Borgonha','conde de Portugal',1069).

homem('Sancho I','rei de Portugal',1154).
homem('Fernando II','rei de Leão',1137).
homem('Afonso IX', 'rei de Leão e Castela', 1171).
homem('Afonso II', 'rei de Portugal',1185).

homem('Sancho II', 'rei de Portugal',1207).
homem('Afonso III', 'rei de Portugal',1210).


mulher('Teresa de Castela', 'condessa de Portugal', 1080).
mulher('Mafalda', 'condessa de Saboia', 1125).
mulher('Urraca', 'infanta de Portugal',1151).
mulher('Dulce de Barcelona','infanta de Aragão',1160).
mulher('Berengária', 'infanta de Portugal',1194).
mulher('Urraca C','infanta de Castela',1186).


filho('Afonso Henriques','Henrique de Borgonha').
filho('Afonso Henriques','Teresa de Castela').
filho('Urraca','Afonso Henriques').
filho('Sancho I','Afonso Henriques').
filho('Urraca','Mafalda').
filho('Sancho I','Mafalda').
filho('Afonso IX','Urraca').
filho('Afonso IX','Fernando II').
filho('Afonso II','Sancho I').
filho('Afonso II','Dulce de Barcelona').
filho('Berengária','Sancho I').
filho('Berengária','Dulce de Barcelona').
filho('Sancho II','Afonso II').
filho('Afonso III','Afonso II').
filho('Sancho II','Urraca C').
filho('Afonso III','Urraca C').


irmao(Nome1, Nome2) :- 
    filho(Nome1, Parent), 
    filho(Nome2, Parent), 
    Nome1 \= Nome2, !.

irmaos(Nome1, Result) :-
    findall(Nome2, irmao(Nome1, Nome2), Result).

primoDireito(Nome1,Nome2) :-
    filho(Nome1, Parent1),
    irmao(Parent1, Parent2),
    filho(Nome2, Parent2).

primosDireito(Nome1, Lista) :-
    findall(Nome2, primoDireito(Nome1, Nome2), Lista).

esposa(Mulher, Homem) :-
    mulher(Mulher, _, _),
    filho(FilhoComum, Mulher),
    filho(FilhoComum, Homem),
    homem(Homem, _, _), !.

ascendente(Z, Y) :- 
    filho(Y, Z).

ascendente(W, Y) :- 
    filho(Y, Z), 
    ascendente(W, Z).
/* 

    Dado um nome deve construir uma lista com todos 
    os ascendentes na base de dados, pais, avós, bisavós, etc.
*/
descende(Nome1, Lista) :-
    findall(Nome2, ascendente(Nome2, Nome1), Lista).

/*
    Dado um nome deve construir uma lista com todos os descendentes, filhos,
    netos, bisnetos, etc.
*/
descendentes(Nome1, Lista) :-
    findall(Nome2, ascendente(Nome1, Nome2), Lista).

pai(Pai, Filho) :-
    filho(Filho, Pai),
    homem(Pai, _, _).

mae(Mae, Filho) :-
    filho(Filho, Mae),
    mulher(Mae, _, _).

% Exercício 9
ascendentes( Nome, c(Nome, AscendentesPai, AscendentesMae)) :-
    pai(Pai, Nome),
    mae(Mae, Nome),
    ascendentes(Pai, AscendentesPai),
    ascendentes(Mae, AscendentesMae).
ascendentes(Nome, c(Nome, 0, 0)) :-
    \+ filho(Nome, _).
/*
  Exercicio 10
*/
% Se não tiver esposa
descendentesLista( Nome, c(Nome, 0, []) ) :-
    homem(Nome, _, _),
    \+ esposa( _, Nome).
% Se não tiver marido
descendentesLista( Nome, c( 0, Nome, []) ) :-
    mulher(Nome, _, _),
    \+ esposa( Nome, _).
% Se não tiver descendentes (Marido)
descendentesLista( Nome, c(Nome, Esposa, []) ) :-
    homem(Nome, _, _),
    \+ esposa(Esposa, Nome),
    \+ filho(_, Nome).
% Se não tiver descendentes (Esposa)
descendentesLista( Nome, c(Marido, Nome, []) ) :-
    mulher(Nome, _, _),
    \+ esposa(Nome, Marido),
    \+ filho(_, Nome).
% Recursão
descendentesLista( Nome, c(Nome, NomeEsposa, ListaDescendentes) ) :-
    homem(Nome, _, _),
    esposa(NomeEsposa, Nome),
    findall(Node, (filho(Filho, Nome), descendentesLista(Filho, Node)), ListaDescendentes).
descendentesLista( Nome, c(Marido, Nome, ListaDescendentes) ) :-
    mulher(Nome, _, _),
    esposa(Nome, Marido),
    findall(Node, (filho(Filho, Nome), descendentesLista(Filho, Node)), ListaDescendentes).

/*
  Exercício 11
*/
% Retira os nomes dos descendentes da estrutura
extractDesc( NomePar, c( _, NomeB, _), NomeB) :-
    filho(NomeB, NomePar).
extractDesc( NomePar, c(NomeA, _, _), NomeA) :-
    filho(NomeA, NomePar).

% Predicado que devolve uma lista com a próxima geração
proxGeracao([], []).
proxGeracao([c(_, _, ListaDesc)], ListaDesc).
proxGeracao([c(_, _, Lista)|T], Result).

% Predicado responsável por extrair os nomes
% e junta-los numa lista
getListNames( c( _, _, []), []).
getListNames( c(Nome, _, [No]), [NomeRes]) :- extractDesc(Nome, No, NomeRes).
getListNames( c(Nome, _, [Head|Tail]), Lista).

% Predicado responsável por ir ao nível desejado
descendentesNivelInterno( 0, _, []).
descendentesNivelInterno( 1, Desc, Lista) :-
    getListNames(Desc, Lista).
descendentesNivelInterno( N, Desc, Lista) :-
    N1 is N - 1,
    descendentesNivelInterno( N1, Desc, Lista).

% Predicado que trabalha com a estrutura recebida do exercício 10
descendentesNivel(Nome, Nivel, Descend) :-
    descendentesLista( Nome, Desc),
    descendentesNivelInterno( Nivel, Desc, Descend).
/*
  Exercício 12
*/
/*
  Exercício 13
  -> InsertionSort
*/
inserir(Value, [], [Value]) :- !.
inserir(Value, [Head|Tail], [Value, Head| Tail]) :-
    @=<(Value, Head).
inserir(Value, [Head|Tail], [Head| Tail2]) :-
    inserir(Value, Tail, Tail2).

ordenaNomeAlf_Interno([], L, L) :- !.
ordenaNomeAlf_Interno([Head|Tail], List1, List) :-
    inserir(Head, List1, Lista2),
    ordenaNomeAlf_Interno(Tail, Lista2, List).

ordenaNomeAlf(List, OrdList) :-
    ordenaNomeAlf_Interno(List, [], OrdList).
/*
  Exercício 14
  -> Merge sort
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
    @=<(X, Y),
    merge(T1, [Y|T2], T).
merge([X|T1], [Y|T2], [Y|T]) :-
    @>(X, Y),
    merge([X|T1], T2, T).
% divide
divideAux( [Head|Tail], Start, End, AuxList, AuxList, [Head|Tail]) :-
    Start = End, !.
divideAux( [Head|Tail], Start, End, EmptyList, L1, L2) :-
    @<(Start, End),
    append(EmptyList, [Head], LR1),
    Start1 is Start + 1,
    divideAux( Tail, Start1, End, LR1, L1, L2).
divide( [], [], []).
divide( [R], [R], []).
divide(List, L1, L2) :-
    length(List, Size),
    Half is floor(Size / 2),
    divideAux( List, 0, Half, [], L1, L2).
/*
  Exercício 16
*/
elementoN( [H|_], N, End, H) :-
    N = End, !.
elementoN( [_|T], N, End, Res) :-
    N1 is N - 1,
    elementoN( T, N1, End, Res).
elemento( List, Index, Res) :-
    elementoN( List, Index, 0, Res).

replaceAux( [H], Pos, End, Elem, [], [Elem| H]) :- Pos = End, !.
replaceAux( [_], Pos, End, Elem, Aux, Res) :-
    Pos = End,
    append(Aux, [Elem], Res), !.
replaceAux( [_|T], Pos, End, Elem, [], Res) :-
    Pos = End,
    append([Elem], T, Res), !.
replaceAux( [_|T], Pos, End, Elem, Aux, Res) :-
    Pos = End,
    append(Aux, [Elem], ListAux),
    append(ListAux, T, Res), !.
replaceAux( [H|T], Pos, End, Elem, Aux, Result) :-
    Pos1 is Pos - 1,
    append(Aux, [H], NAux),
    replaceAux( T, Pos1, End, Elem, NAux,Result).
replace( Lista, Pos, Elem, ListaResult) :-
    replaceAux( Lista, Pos, 0, Elem, [], ListaResult).
troca(Lista, P1, P2, ListaTrocada) :-
    elemento(Lista, P1, Ele1),
    elemento(Lista, P2, Ele2),
    replace(Lista, P1, Ele2, ListaAux),
    replace(ListaAux, P2, Ele1, ListaTrocada).
