/**
 * Initial & Final States
 */
initial_state([1,1]).
final_state([1,4]).

/**
 * Limits:
 */
min(1).
max(4).

/**
 * Blocked Passages:
 */
pass([1,1], [1,2]).
pass([2,1], [2,2]).
pass([3,1], [4,1]).
pass([3,2], [3,3]).
pass([4,2], [4,3]).

block_pass(P1, P2) :- pass(P1,P2); pass(P2,P1).

/**
 * Operations:
 * op( ActualState, Move, NextState, Cost ).
 */
op([XI,YI], left, [XF, YI], 1) :-
    XF is XI - 1,
    min(MIN), XF >= MIN,
    \+ block_pass([XI, YI], [XF, YI]).

op([XI, YI], right, [XF, YI], 1) :-
    XF is XI + 1,
    max(MAX), XF =< MAX,
    \+ block_pass([XI, YI], [XF, YI]).

op([XI, YI], up, [XI, YF], 1) :-
    YF is YI - 1,
    min(MIN), YF >= MIN,
    \+ block_pass([XI, YI], [XI, YF]).

op([XI, YI], down, [XI, YF], 1) :-
    YF is YI + 1,
    max(MAX), YF =< MAX,
    \+ block_pass([XI, YI], [XI, YF]).
