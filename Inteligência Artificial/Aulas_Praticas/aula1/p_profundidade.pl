pesq_prof(EstEnd, EstEnd, [], 0).
pesq_prof(EstAct, EstEnd, [Accao| Tail], Cost) :-
    op(EstAct, Accao, EstNext, C),
    pesq_prof(EstNext, EstEnd, Tail, CT),
    Cost is C + CT.

pesquisa :-
    estado_inicial(Init),
    estado_final(Fin),
    pesq_prof(Init, Fin, L, C),
    writeln(L), writeln(C), !.