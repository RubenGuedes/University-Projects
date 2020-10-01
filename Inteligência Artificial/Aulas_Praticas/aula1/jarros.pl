max(c1, 2).
max(c2, 3).

espaco_disponivel(Max, Quant, Disp) :-
    Disp is Max - Quant.

quantidade_restante(QuantAPreencher, QuantATirar, Diff) :-
    Diff is abs(QuantAPreencher - QuantATirar).

estado_inicial([0,0]).
estado_final([0,1]).

% Despejar jarro 1
op([_,Ci2], desp_1, [0, Ci2], 1).

% Despejar jarro 2
op([Ci1, _], desp_2, [Ci1, 0], 1).

% Encher jarro 1
op([_, Ci2], enche_1, [Quant, Ci2], 1) :-
    max(c1, Quant).

% Enchar jarro 2
op([Ci1, _], enche_2, [Ci1, Quant], 1) :-
    max(c2, Quant).

% Passar conteudo jarro 1 para jarro 2
op([Ci1, Ci2], pass_1_2, [Cf1, Cf2], 1) :-
    max(c2, QuantC2),
    espaco_disponivel(QuantC2, Ci2, EspAPreencher),
    EspAPreencher =< Ci1,
    Cf1 is Ci1 - EspAPreencher,
    Cf2 is Ci2 + EspAPreencher.
op([Ci1, Ci2], pass_1_2, [Cf1, Cf2], 1) :-
    max(c2, QuantC2),
    espaco_disponivel(QuantC2, Ci2, EspAPreencher),
    EspAPreencher > Ci1,
    Cf1 is 0,
    Cf2 is Ci2 + Ci1.

% Passar conteudo jarro 2 para jarro 1
op([Ci1, Ci2], pass_2_1, [Cf1, Cf2], 1) :-
    max(c1, QuantC1),
    espaco_disponivel(QuantC1, Ci1, EspAPreencher),
    EspAPreencher =< Ci2,
    Cf1 is Ci1 + EspAPreencher,
    Cf2 is Ci2 - EspAPreencher.
op([Ci1, Ci2], pass_2_1, [Cf1, Cf2], 1) :-
    max(c1, QuantC1),
    espaco_disponivel(QuantC1, Ci1, EspAPreencher),
    EspAPreencher > Ci2,
    Cf1 is Ci1 + Ci2,
    Cf2 is 0.
