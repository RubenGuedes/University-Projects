% Funcionalidades load & store
load( Name, Value) :- b_getval(Name, Value).
store(Name, Value) :- b_setval(Name, Value).

% interpretador
calc :- write('> '), read(Expression), exe(Expression).
% execução
exe(endfile)   :- !.
exe(Operation) :- do(Operation), calc.

% operador do
do(Expre) :- eval(Expre, Result), write(Result), nl.

% eval genérico
eval(Op, Result) :- atom(Op), name(Op, Result).
% eval afetação
eval(X=E, Result) :- Result is E.
% eval para operações básicas
eval( A + B, Result) :- eval(A, X), eval(B, Y), Result is X + Y.
eval( A - B, Result) :- eval(A, X), eval(B, Y), Result is X - Y.
eval( A * B, Result) :- eval(A, X), eval(B, Y), Result is X * Y.
eval( A / B, Result) :- eval(A, X), eval(B, Y), Result is X / Y.