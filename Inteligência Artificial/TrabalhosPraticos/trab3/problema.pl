/**
 * Limits
 */
columns(5).
rows(4).
min(0).
/**
 * Type of game
 */
three_in_line(3).
four_in_line(4).
game_option(0, Value) :- three_in_line(Value).
game_option(1, Value) :- four_in_line(Value).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                CREATE NEW MAP
%
%               Y
%               0 |e e e e e
%               1 |e e e e e
%               2 |e e e e e
%               3 |e e e e e
%                  _ _ _ _ _
%                  0 1 2 3 4 X  
/**
 * Create a empty line with columns size
 */
create_column(Column, 0, Column) :- !.
create_column(Column, Size, Result) :-
    append( [e], Column, ColAux),
    Size1 is Size - 1,
    create_column(ColAux, Size1, Result).
/**
 * Create map
 */
create_map(Row, _, 0, Row) :- !.
create_map(Row, MAXCOL, RowNumb, Result) :-
    create_column([], MAXCOL, NCol),
    append([NCol], Row, RowAux),
    RowNumb1 is RowNumb - 1,
    create_map(RowAux, MAXCOL, RowNumb1, Result). 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%              GET ELEMENT FROM MAP
/**
 * Get Row
 */
get_row([Line|_], 0, Line).
get_row([_|Tail], X, Res) :-
    X1 is X - 1,
    get_row(Tail, X1, Res).
/**
 * Get Column
 */
get_column(Line, Ind, Res) :- get_row(Line, Ind, Res).
/**
 * Get element in map
 */
get_element(Map, X, Y, Elem) :-
    get_row(Map, X, Line),
    get_column(Line, Y, Elem), !.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%         SEARCH FOR PIECES IN A ROW
/**
 * Line Search
 */
line_search(_, _, _, 0, _).
line_search(Map, X, Y, NLine, Element) :-
    min(MIN), @>=(X, MIN), @>=(Y, MIN),
    columns(COL), @=<(X, COL),
    rows(ROW), @=<(Y, ROW),
    get_element(Map, X, Y, Ele),
    Element = Ele,
    NLine1 is NLine - 1,
    X1 is X + 1,
    line_search(Map, X1, Y, NLine1, Element).
/**
 * Column Search
 */
column_search(_, _, _, 0, _).
column_search(Map, X, Y, NLine, Element) :-
    min(MIN), @>=(X, MIN), @>=(Y, MIN),
    columns(COL), @=<(X, COL),
    rows(ROW), @=<(Y, ROW),
    get_element(Map, X, Y, Ele),
    Element = Ele,
    NLine1 is NLine - 1,
    Y1 is Y + 1,
    column_search(Map, X, Y1, NLine1, Element).
/**
 * Diagonal Search
 */
diagonal_search_right(_, _, _, 0, _).
diagonal_search_right(Map, X, Y, NLine, Element) :-
    min(MIN), @>=(X, MIN), @>=(Y, MIN),
    columns(COL), @=<(X, COL),
    rows(ROW), @=<(Y, ROW),
    get_element(Map, X, Y, Ele),
    Element = Ele,
    NLine1 is NLine - 1,
    X1 is X + 1,
    Y1 is Y + 1,
    diagonal_search_right(Map, X1, Y1, NLine1, Element).

diagonal_search_left(_, _, _, 0, _).
diagonal_search_left(Map, X, Y, NLine, Element) :-
    min(MIN), @>=(X, MIN), @>=(Y, MIN),
    columns(COL), @=<(X, COL),
    rows(ROW), @=<(Y, ROW),
    get_element(Map, X, Y, Ele),
    Element = Ele,
    NLine1 is NLine - 1,
    X1 is X - 1,
    Y1 is Y + 1,
    diagonal_search_left(Map, X1, Y1, NLine1, Element).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Auxiliar Predicate to 'find_solution'
 */
sub_solution(_, X, _, _, MaxX, _, _) :-
    @>(X, MaxX), !, fail.
sub_solution(Map, X, Y, RowSize, _, _, Elem) :-
    get_element(Map, X, Y, Elem),
    Elem \= e,
    (
	line_search(Map, X, Y, RowSize, Elem);
	column_search(Map, X, Y, RowSize, Elem);
	diagonal_search_right(Map, X, Y, RowSize, Elem);
	diagonal_search_left(Map, X, Y, RowSize, Elem)
    ).
sub_solution(Map, X, Y, RowSize, MaxX, MaxY, Elem) :-
    X1 is X + 1,
    sub_solution(Map, X1, Y, RowSize, MaxX, MaxY, Elem).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Predicate that uses the previous predicates 
 * (line, column and diagonal search) to find 
 * a row with the same elements
 */
find_solution(_, _, Y, _, _, MaxY, _) :-
    @>(Y, MaxY), !, fail.
find_solution(Map, X, Y, RowSize, MaxX, MaxY, Elem) :-
    sub_solution(Map, X, Y, RowSize, MaxX, MaxY, Elem).
find_solution(Map, X, Y, RowSize, MaxX, MaxY, Elem) :-
    Y1 is Y + 1,
    find_solution(Map, X, Y1, RowSize, MaxX, MaxY, Elem).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * The map is filled
 */
full([]).
full([Head|Tail]) :-
    \+ member(e, Head),
    full(Tail).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Search for a row with the same elements
 */
pieces_in_a_row(Map, Elem) :-
    % select game option
    game_option(0, RowSize),
    % begin
    min(X),
    min(Y),
    % end
    columns(MaxX),
    rows(MaxY),
    % call find_solution
    find_solution(Map, X, Y, RowSize, MaxX, MaxY, Elem).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%              DESCRIPTION
/**
 * Initial State
 */
initial_state(Init) :-
    columns(COL),
    rows(ROW),
    create_map([], COL, ROW, Init).
/**
 *           Example
 *
 *       Y
 *       0 |
 *       1 |o x 
 *       2 |x x o   o
 *       3 |x o x o x
 *          _ _ _ _ _
 *          0 1 2 3 4 X  
 */
example_state([[e,e,e,e,e],[o,x,e,e,e],[x,x,o,e,o],[x,o,x,o,x]]).
/**
 *           Example 2
 *
 *       Y
 *       0 |
 *       1 |
 *       2 |o       o
 *       3 |x o x o x
 *          _ _ _ _ _
 *          0 1 2 3 4 X  
 */
example_state_game([[e,e,e,e,e],[e,e,e,e,e],[e,e,e,e,e],[o,x,o,x,e]]).
/**
 * Final State
 */
final_state(Goal, Winner) :- pieces_in_a_row(Goal, Winner); full(Goal), Winner = x-o.
/**
 * Utility
 */
utility(x  , -1).
utility(x-o,  0).
utility(o  ,  1).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Auxiliar Predicates to Operations
 */
% Replace element in a single line
replace_line([_|Tail], 0, Elem, NMap, Result) :-
    append(NMap, [Elem], NMapAux),
    append(NMapAux, Tail, Result), !.
replace_line([Head|Tail], Index, Elem, NewMap, Result) :-
    Ind1 is Index - 1,
    append(NewMap, [Head], NewMapAux),
    replace_line(Tail, Ind1, Elem, NewMapAux, Result).

% Replace element in a map
replace_map([ToReplace |Tail], 0, Column, Elem, NMap, Result) :-
    replace_line(ToReplace, Column, Elem, [], NLine),
    append(NMap, [NLine], NMapAux),
    append(NMapAux, Tail, Result), !.
replace_map([Head|Tail], Line, Column, Elem, NMap, Result) :-
    Line1 is Line - 1,
    append(NMap, [Head], NMapAux),
    replace_map(Tail, Line1, Column, Elem, NMapAux, Result).

% Given a column index, search for available line.
see_available_line(_, _, Line, _) :-
    min(ROWS),
    @<(Line, ROWS), !, fail.
see_available_line(Map, Col, Line, Line) :-
    get_element(Map, Line, Col, e).
see_available_line(Map, Col, Line, Res) :-
    Line1 is Line - 1,
    see_available_line(Map, Col, Line1, Res).

/**
 * Operations
 */
op(Map, insert_col0, Elem, ResMap) :-
    Col is 0,
    rows(ROW), R1 is ROW - 1,
    see_available_line(Map, Col, R1, Line),
    replace_map(Map, Line, Col, Elem, [], ResMap).

op(Map, insert_col1, Elem, ResMap) :-
    Col is 1,
    rows(ROW), R1 is ROW - 1,
    see_available_line(Map, Col, R1, Line),
    replace_map(Map, Line, Col, Elem, [], ResMap).

op(Map, insert_col2, Elem, ResMap) :-
    Col is 2,
    rows(ROW), R1 is ROW - 1,
    see_available_line(Map, Col, R1, Line),
    replace_map(Map, Line, Col, Elem, [], ResMap).

op(Map, insert_col3, Elem, ResMap) :-
    Col is 3,
    rows(ROW), R1 is ROW - 1,
    see_available_line(Map, Col, R1, Line),
    replace_map(Map, Line, Col, Elem, [], ResMap).

op(Map, insert_col4, Elem, ResMap) :-
    Col is 4,
    rows(ROW), R1 is ROW - 1,
    see_available_line(Map, Col, R1, Line),
    replace_map(Map, Line, Col, Elem, [], ResMap).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Predicado que imprime o tabuleiro do jogo
 */
print_game(Map) :-
    nl,
    writeln(" Tabuleiro"),
    print_map(Map),
    nl.

print_map([]).
print_map([Head|Tail]) :-
    writeln(Head),
    print_map(Tail).
