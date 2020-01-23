sum([], 0).
sum([A|L], X) :- sum(L, T), X is A + T.

prod([], 1).
prod([A|L], X) :- prod(L, T), X is A * T.

len([], 0) :- !.
len([_|L], N):-
            len(L, N1), N is N1 + 1.

compr(_, 0,[], 0).