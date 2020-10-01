min(-1).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Create row
 */
create_row(Row, Col) :- create_row(Row, [], Col).

create_row(0, Row, Row) :- !.
create_row(Row, Empt, Res) :-
    R1 is Row - 1,
    append([e], Empt, NEmpt),
    create_row(R1, NEmpt, Res).

/**
 * Create map
 */
create_map(N, Map) :- create_map(N, N, [], Map).

create_map(_, 0, Map, Map) :- !.
create_map(N, Var, Empt, Res) :-
    V1 is Var - 1,
    create_row(N, Row),
    append([Row], Empt, NEmpt),
    create_map(N, V1, NEmpt, Res).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Replace row
 * replace_row(List, Index, Elem, NList).
 */
replace_row(List, Ind, Elem, Result) :-
    replace_row(List, Ind, Elem, [], Result).

replace_row([], 0, _, Res, Res).
replace_row([_|Tail], 0, Elem, Res, Result) :-
    append([Elem], Tail, NL),
    append(Res, NL, Result), !.
replace_row([Head|Tail], Ind, Elem, Res, Result) :-
    I1 is Ind - 1,
    append(Res, [Head], NRes),
    replace_row(Tail, I1, Elem, NRes, Result).

/**
 * Replace element in map
 */
replace_map(Map, Row, Col, Elem, Result) :-
    replace_map(Map, Row, Col, Elem, [], Result).

replace_map([Head|Tail], 0, Col, Elem, Res, Result) :-
    replace_row(Head, Col, Elem, NRow),
    append([NRow], Tail, NHead),
    append(Res, NHead, Result), !.
replace_map([Head|Tail], Ind, Col, Elem, Res, Result) :-
    I1 is Ind - 1,
    append(Res, [Head],  NRes),
    replace_map(Tail, I1, Col, Elem, NRes, Result).

/**
 * Inserir na coluna x, a rainha na posição y
 */
insert_queen(Board, Row, Col, NBoard) :-
    replace_map(Board, Row, Col, q, NBoard).

/**
 * Predicado para inserir rainhas em todas as colunas do tabuleiro
 */
insert_random_queens(Board, _, 0, Board) :- !.
insert_random_queens(Board, MAXN, N, NBoard) :-
    RandRow is random(MAXN),
    N1 is N - 1,
    insert_queen(Board, RandRow, N1, AuxBoard),
    insert_random_queens(AuxBoard, MAXN, N1, NBoard).

/**
 * Criar tabuleiro
 */
build_board(NN, Board) :-
    create_map(NN, Map),
    insert_random_queens(Map, NN, NN, Board).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Select Element
 */
get_element(Board, Row, Col, Elem) :-
    nth0(Row, Board, Line),
    nth0(Col, Line, Elem), !.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Pesquisa no espaço de estados
 */
% +1
east_search( _, MAX, _, _, _, MAX, 0).
east_search( Board, _, _, PrevCol, Row, Col, 1) :-
    PrevCol \= Col,
    get_element(Board, Row, Col, q), !.
east_search( Board, MAX, Row, Col, ActRow, ActCol, Value) :-
    C1 is ActCol + 1,
    east_search(Board, MAX, Row, Col, ActRow, C1, Value).

% +1 +1
southeast_search(_, MAX, _, _, MAX, MAX, 0).
southeast_search(_, MAX, _, _,   _, MAX, 0).
southeast_search(_, MAX, _, _, MAX,   _, 0).
southeast_search(Board, _, PrevRow, PrevCol, Row, Col, 1) :-
    (PrevRow \= Row, PrevCol \= Col),
    get_element(Board, Row, Col, q), !.
southeast_search(Board, MAX, Row, Col, ActRow, ActCol, Value) :-
    R1 is ActRow + 1,
    C1 is ActCol + 1,
    southeast_search(Board, MAX, Row, Col, R1, C1, Value).

% +1
south_search( _, MAX, _, _, MAX, _, 0) :- !.
south_search(Board, _, PrevRow, _, Row, Col, 1) :-
    PrevRow \= Row,
    get_element(Board, Row, Col, q), !.
south_search(Board, MAX, Row, Col, ActRow, ActCol, Value) :-
    R1 is ActRow + 1,
    south_search(Board, MAX, Row, Col, R1, ActCol, Value).

% +1 -1
southwest_search(_, MAX, _, _, MAX, MIN, 0) :-
    min(MIN), !.
southwest_search(_, _, _, _, _, MIN, 0) :-
    min(MIN), !.
southwest_search(_, MAX, _, _, MAX, _, 0) :- !.
southwest_search(Board, _, PrevRow, PrevCol, Row, Col, 1) :-
    (PrevRow \= Row, PrevCol \= Col),
    get_element(Board, Row, Col, q).
southwest_search(Board, MAX, Row, Col, ActRow, ActCol, Value) :-
    R1 is ActRow + 1,
    C1 is ActCol - 1,
    southwest_search(Board, MAX, Row, Col, R1, C1, Value).

/**
 * Search in point
 */
search_point(Board, MAX, Row, Col, Value) :-
    east_search(Board, MAX, Row, Col, Row, Col, Val0),
    southeast_search(Board, MAX, Row, Col, Row, Col, Val1),
    south_search(Board, MAX, Row, Col, Row, Col, Val2),
    southwest_search(Board, MAX, Row, Col, Row, Col, Val3),
    Value is Val0 + Val1 + Val2 + Val3.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Search Row
 */
search_row(Board, MAX, CoordRow, CoordCol, Value) :-
    search_row(Board, MAX, CoordRow, CoordCol, 0, Value).

search_row(_, _, _, -1, Val, Val).
search_row(Board, MAX, CoordRow, CoordCol, Val, Value) :-
    NCoordCol is CoordCol - 1,
    get_element(Board, CoordRow, NCoordCol, q),
    search_point(Board, MAX, CoordRow, NCoordCol, V),
    NV is Val + V,
    search_row(Board, MAX, CoordRow, NCoordCol, NV, Value).
search_row(Board, MAX, CoordRow, CoordCol, Val, Value) :-
    NCoordCol is CoordCol - 1,
    search_row(Board, MAX, CoordRow, NCoordCol, Val, Value).
/**
 * Search for attacks in the map
 */
search_map(Board, MAX, CoordR, CoordC, Value) :-
    search_map(Board, MAX, CoordR, CoordC, 0, Value).

search_map(_, _, -1, _, Value, Value).
search_map(Board, MAX, CoordRow, CoordCol, Val, Value) :-
    NCoordRow is CoordRow - 1,
    search_row(Board, MAX, NCoordRow, CoordCol, V),
    NVal is Val + V,
    search_map(Board, MAX, NCoordRow, CoordCol, NVal, Value).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Find Queens
 */
find_queens(Board, MAX, Res) :-
    find_queens_row(Board, 0, MAX, [], Res), !.

% Rows
find_queens_row(    _, MAX, MAX, Aux, Aux).
find_queens_row(Board, Row, MAX, Aux, Res) :-
    find_queens_col(Board, Row, 0, MAX, Aux, NAux),
    R1 is Row + 1,
    find_queens_row(Board, R1, MAX, NAux, Res).

% Columns
find_queens_col(    _,   _, MAX, MAX, Aux, Aux).
find_queens_col(Board, Row, Col, MAX, Aux, Res) :-
    get_element(Board, Row, Col, q),
    append(Aux, [[Row, Col]], NAux),
    C1 is Col + 1,
    find_queens_col(Board, Row, C1, MAX, NAux, Res).
find_queens_col(Board, Row, Col, MAX, Aux, Res) :-
    C1 is Col + 1,
    find_queens_col(Board, Row, C1, MAX, Aux, Res).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
find_one_queen_col(_, MAX, _, Row, _) :-
    @>(Row, MAX), !, fail.
find_one_queen_col(Board,   _, Col, Row, Row) :-
    get_element(Board, Row, Col, q).
find_one_queen_col(Board, MAX, Col, Row, Res) :-
    R1 is Row + 1,
    find_one_queen_col(Board, MAX, Col, R1, Res).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Estado Inicial
 */
initial_state(NN, Board) :-
    build_board(NN, Board).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Heuristica
 */
heuristic(Board, MAX, Value) :-
    search_map(Board, MAX, MAX, MAX, Value), !.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Final State
 */
final_state(Board, Max) :-
    heuristic(Board, Max, Val), Val = 0.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Free Positions
 *
 * Dada uma lista com as coordenadas das rainhas, 
 * retorna todas as coordenadas que não contêm rainhas.
 */
free_positions(QueensList, MAX, AllFreePositions) :-
    free_positions(QueensList, MAX, [], AllFreePositions).

free_positions([], _, Res, Res).
free_positions([[R,C]|Tail], MAX, Aux, Res) :-
    operator(R, C, MAX, List),
    append(Aux, List, NAux),
    free_positions(Tail, MAX, NAux, Res).

/**
 * Dada uma coordenada, descobre todas 
 * as coordenadas livres na coluna dessa coordenada
 */
operator(Row, Col, MAX, List) :-
    create_list(0, Col, MAX, [Row], List), !.

create_list(Row, Col, End, Except, NList) :-
    create_list(Row, Col, End, Except, [], NList).

create_list(End, _, End, _, Aux, Aux).

create_list(Row, Col, End, Except, Aux, NList) :-
    member(Row, Except),
    R1 is Row + 1,
    create_list(R1, Col, End, Except, Aux, NList).

create_list(Row, Col, End, Except, Aux, NList) :-
    append(Aux, [[Row,Col]], NAux),
    R1 is Row + 1,
    create_list(R1, Col, End, Except, NAux, NList).
