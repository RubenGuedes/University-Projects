% ------------Regras------------
mulher(X) :- \+ homem(X).

casal(A, B) :- progenitor(A, X) , progenitor(B, Y).

pai(A, B) :- progenitor(A, B) , homem(A).
mae(A, B) :- progenitor(A, B) , mulher(A). 

irmao(A, B) :- pai(X, A) , mae(Y, A), pai(X, B) , mae(Y, B), homem(A).
irma(A, B)  :- pai(X, A) , mae(Y, A), pai(X, B) , mae(Y, B), mulher(A).

primo(A, B) :- progenitor(X, A) , progenitor(Y, B) , (irmao(X, Y) ; irma(X, Y) ) , homem(A).
prima(A, B) :- progenitor(X, A) , progenitor(Y, B) , (irmao(x, Y) ; irma(X, Y) ) , mulher(A).

primodireito(A ,B) :- pai(A, B) ; mae(A, B) ; avo(A, B).

tio(A, B) :- homem(A) , (\+pai(A, B) , \+mae(A, B)) , ( pai(A, X) ; mae(A, X)) , (primo(X, B) ; prima(X, B) ).
tia(A, B) :- mulher(A), (\+pai(A, B) , \+mae(A, B)) , ( pai(A, X) ; mae(A, X)) , (primo(X, B) ; prima(X, B) ).

avo(A, B) :- progenitor(A , X) , progenitor(X ,B).

neto(A, B) :- avo(B, A) , homem(A).
neta(A, B) :- avo(B, A) , mulher(A).

antepassado(A, B) :- pai(A, B); mae(A, B) ; tio(A, B); tia(A, B).
descendente(A, B) :- antepassado(B, A).

parente(A, B) :- irma(A, B) ; irmao(A, B) ; primo(A, B); prima(A, B) ;antepassado(A, B); descendente(A, B) ; antepassado() ; avo(A, B) ; avo(B,A). 
% ------------Parentesco------------

% Filhos da 1ª Geração
progenitor(antonio, sandra).
progenitor(antonio, joao).
progenitor(antonio, alice).
progenitor(luisa, sandra).
progenitor(luisa, joao).
progenitor(luisa, alice).

% Filhos da 2ª Geração
progenitor(ricardo, ruben).
progenitor(ricardo, ines).
progenitor(sandra, ruben).
progenitor(sandra, ines).

progenitor(carlos, alberto).
progenitor(carlos, beatriz).
progenitor(alice, alberto).
progenitor(alice, beatriz).

progenitor(joao, nuno).
progenitor(joao, ana).
progenitor(joao, tiago).
progenitor(rita, nuno).
progenitor(rita, ana).
progenitor(rita, tiago).

% ------------Géneros------------
homem(antonio).
homem(ricardo).
homem(carlos).
homem(joao).
homem(ruben).
homem(alberto).
homem(nuno).
homem(tiago).