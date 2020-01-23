% Numerais
num(z).
num(s(X)) :- num(X).


%Numeral para número
num(z, 0).
num(s(X), SY) :- num(X, Y), SY is Y+1.

% Predicado "soma"
soma(z, X, X).
soma(s(X), Y, s(Z)) :- soma(X, Y, Z).

% Predicado "subtr"
subtr(A, B, X) :- soma(X, B, A).

% Predicado le
le(A, B) :- soma(A, _, B).

% Predicado lt
lt(A, B) :- soma(A, s(_), B).

% Predicado mult
mult(z, _, z).
mult(s(A), B, X) :- mult(A, B, Y), soma(B, Y, X).

% Predicado div/3
div(A, B, X) :- mult(X, B, A).

% Predicado div/4
div(A, B, Q, R) :- mult(B, Q, X), soma(X, R, A), lt(R, B).

% Predicado "mais1"
mais1(A, B) :- B = s(A).

% Predicado dobro
dobro(X, Y) :- soma(X, X, Y).

% Predicado pot
pot(_, z, s(z)).
pot(X, s(Y), Z) :- pot( X, Y, W) , mult( X, W, Z).

% quadrado
quadrado(X, Y) :- mult(X, X, Y).

% Clausula lista 
lista([]).
lista([_| L]) :- lista(L).

% Clausula membro
membro( X, [X|_] ).
membro( X, [_|L] ) :- membro(X, L).

% Clausula prefixo
prefixo( [], _).
prefixo( [X|A], [X|B] ) :- prefixo(A, B).

% Clausula sufixo
sufixo(A, A).
sufixo(A, [_|B]) :- sufixo(A, B).

% Clausula sublista
sublista(S, L ) :- prefixo(S, L).
sublista(S, [_|L] ) :- sublista(S,L).

% Clausula catena
catena( [], L, L).
catena( [X|Xs], L, [X|Y] ) :- catena(Xs, L, Y).


% Clausula ultimo
ultimo(X, Y) :- catena(_, [X], Y).

% Clausula nrev
nrev([], []).
nrev([X|A], B) :- nrev(A, AR), catena(AR, [X], B) .

% Clausula rev
rev(L, R ) :- rev(L, [], R ).

rev([], R, R).
rev([A|B], X, R) :- rev(B, [A|X], R).

% Clausula compr/2
compr([], 0).
compr([_|T], X) :- compr(T, Y), X is Y+1.

% Clausula sel/3
sel(E, [E|L], L).
sel(E, [X|L], [X|M]) :-sel(E, L, M).

% Clausula perm/2
perm([], []).
perm( L, [X|LP] ) :- sel(X, L, LX) , perm(LX, LP).

% Clausula ord/1
ord([]).
ord([_]).
ord([A,B|X]) :- A < B, ord( [B|X] ).

% Clausula isort/2
isort() :- isort().

isort([], S, S).
isort([X|Xs], SI, SO) :- insord(X, SI, SX), isort(Xs, SX, SO).

insord(X, [], [X]).
insord(X, [A|As], [X,A|As] ) :-  X =< A.
insord(X, [A|As], [A|AAs]) :- X > A, insord(X, As, AAs).

% equals
igual(A, A).

% Clausula cortar
cortar(L, E, S1, S2) :- 
    sufixo(SA1, L), \+membro(E, SA1), catena([E], SA1, S1),
    prefixo(S2, L),\+membro(E, S2).%sss \+igual([], S2).