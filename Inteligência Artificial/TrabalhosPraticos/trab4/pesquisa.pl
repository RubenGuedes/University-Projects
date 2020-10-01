get_queen_coord([_,_], [], []).
get_queen_coord([_,Y], [[X1, Y]|_], [X1, Y]).
get_queen_coord([X,Y], [[_, _]|Tail], NewCoord) :-
    get_queen_coord([X,Y], Tail, NewCoord).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
new_states(Board, MAX, QueensList, FreePos, ListStates) :-
    new_states(Board, MAX, QueensList, FreePos, [], ListStates).

new_states( _, _, _, [], Res, Res).
new_states(Board, MAX, QueensList, [[X,Y]|Tail], Aux, Result) :-
    get_queen_coord( [X,Y], QueensList, [X1, Y]),
    replace_map(Board, X, Y, q, NBoard),
    replace_map(NBoard, X1, Y, e, FState),
    heuristic(FState, MAX, Heur),
    append([state(FState, Heur)], Aux, NAux),
    new_states(Board, MAX, QueensList, Tail, NAux, Result).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Algoritmo que procura pelo estado que tem menor heuristica
 */
lower_heuristic([], []).
lower_heuristic([state(St, Heur)|Tail], Res) :-
    lower_heuristic(Tail, state(St, Heur), Res).

lower_heuristic( [], state(State, _), State ).
lower_heuristic([state(_, Heur)|Tail], state(PrevSt, PrevHeur), Res) :-
    @>(Heur, PrevHeur), !,
    lower_heuristic( Tail, state(PrevSt, PrevHeur) , Res).
lower_heuristic([Head|Tail], _, Res) :-
    lower_heuristic(Tail, Head, Res).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Tendo um estado, devolve o próximo estado que tenha a menor heuristica
 */
mutation(State, MAX, ResState) :-
    find_queens(State, MAX, QueensList),
    free_positions(QueensList, MAX, FreePos),
    new_states(State, MAX, QueensList, FreePos, NStates),
    lower_heuristic(NStates, ResState), !.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
small_prob(Set_Ind, ChildHeur) :-
    small_prob(Set_Ind, 1000000, ChildHeur).

small_prob([], Aux, ChildHeur) :-
    @=<(ChildHeur, Aux).
small_prob([state( _, Heur)|Tail], Aux, ChildHeur) :-
    @<(Heur, Aux), !,
    small_prob(Tail, Heur, ChildHeur).
small_prob([_|Tail], Aux, ChildHeur) :-
    small_prob(Tail, Aux, ChildHeur).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * De uma lista com "state(Board, Heuristic)", este predicado 
 * retorna a maior heuristica
 */ 
highest_heur(List, Res) :-
    highest_heur(List, -1, Res).

highest_heur([], Min, Min).
highest_heur([state(_,Heur)|Tail], Min, Res) :-
    @>(Heur, Min),
    highest_heur(Tail, Heur, Res).
highest_heur([_|Tail], Min, Res) :-
    highest_heur(Tail, Min, Res).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Dado um dominio, [Coluna0, COlunaN], este predicado vai copiar
 * a rainha e coloca-o noutro tabuleiro
 */
copy_board( Child, _,   _, End, End, Child).
copy_board(Board0, Board1, MAX, Init, End, Child) :-
    find_one_queen_col(Board1, MAX, Init, 0, Row),
    replace_map(Board0, Row, Init, q, NBoard0),
    I1 is Init + 1,
    copy_board(NBoard0, Board1, MAX, I1, End, Child).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Predicado que cria um tabuleiro filho tendo como base, os 2 
 * tabuleiros parentes.
 */
reproduce(State0, State1, MAX, Child) :-
    Div is round( MAX / 2 ),
    random(1, Div, RandVal),
    create_map(MAX, OriginalBoard),
    Rand1 is RandVal + 1,
    copy_board(OriginalBoard, State0, MAX, 0, Rand1, ResBoard),
    copy_board(ResBoard, State1, MAX, Rand1, MAX, Child), !.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Valor aleatório entre "Begin" e "Until" excepto o valor "Except"
 */
random_value(Begin, Until, Except, Res) :-
    random(Begin, Until, Res),
    Res =\= Except, !.
random_value(Begin, Until, Except, Res) :-
    random_value(Begin, Until, Except, Res).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
genetic(Set_Ind, _,  1, AuxPop, NewSetInd) :-
    append(Set_Ind, AuxPop, NewSetInd).
genetic(Set_Ind, MAX, Len, AuxPop, NewSetInd) :-

    random(0, Len, Ind0),
    random_value(0, Len, Ind0, Ind1),
    
    nth0(Ind0, Set_Ind, state(Pop0, _)),
    nth0(Ind1, Set_Ind, state(Pop1, _)),

    reproduce(Pop0, Pop1, MAX, Child),
    heuristic(Child, MAX, HeurChild),
    
    (
	small_prob(Set_Ind, HeurChild) ->
	(mutation(Child, MAX, NChild));
	(NChild = Child)
    ),

    heuristic(NChild, MAX, Value),
    
    % Se o tabuleiro não existe, coloca-o na lista,
    % caso contrário, coloca um tabuleiro aleatório.
    (
	(\+ member(state(NChild, _), AuxPop)) ->
	(
	    append(AuxPop, [state(NChild, Value)] , NAuxPop)
	);
	(
	    % Insere um tabuleiro aleatório
	    build_board(MAX, RandBoard),
	    heuristic(RandBoard, MAX, HR),
	    append(AuxPop, [state(RandBoard, HR)], NAuxPop)
	)
    ),

    L1 is Len - 1,
    genetic(Set_Ind, MAX, L1, NAuxPop, NewSetInd).    

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Implementação do algoritmo genérico
 */
genetic_algorithm(SetInd, _, Pop) :-
    member(state(Pop, 0), SetInd), !.

genetic_algorithm(Set_Individuals, MAX, Population ) :-
    length(Set_Individuals, Len),
    genetic(Set_Individuals, MAX, Len, [], NewSetInd), !,
    reduce_list(NewSetInd, MAX, FSetInd),
    
    %%
    % Para evitar loop, remover um tabuleiro e adicionar um aleatorio
    highest_heur(FSetInd, Heur0),
    select(state(_, Heur0), FSetInd, NList), !,
    build_board(MAX, RandBoard),
    heuristic(RandBoard, MAX, HR),
    append(NList, [state(RandBoard, HR)], FList),
    %%
    
    genetic_algorithm(FList, MAX, Population).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
start :-
    LineColumn is 8,
    initial_state(LineColumn, Board0),
    initial_state(LineColumn, Board1),
    heuristic(Board0, LineColumn, Heur0),
    heuristic(Board1, LineColumn, Heur1),
    
    writeln("Estados Iniciais Aleatórios:"),
    writeln("Estado 1:"),
    print_board(Board0),
    
    writeln("Estado 2:"),
    print_board(Board1),

    % Aplicar o algoritmo
    genetic_algorithm([state(Board0, Heur0), state(Board1, Heur1)], LineColumn, Pop),

    writeln("Estado final:"),
    print_board(Pop), !.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Imprime o tabuleiro num formato mais apresentavel
 */
print_board([]) :- writeln("").
print_board([Head|Tail]) :-
    writeln(Head),
    print_board(Tail).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Ciclo que vai manter uma lista com o tamanho inferior ao Número de Colunas + 5
 * PARA EVITAR QUE O PROGRAMA ENTRE EM "ERROR: Out of global stack"
 */
reduce_list(List, MAX, List) :-
    length(List, Len),
    M5 is MAX + 5,
    @=<(Len, M5), !.
reduce_list(List, MAX, Res) :-
    highest_heur(List, Heur0),
    select(state(_, Heur0), List, NList), !,
    reduce_list(NList, MAX, Res).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * O algoritmo entra em loop em mínimos locais
 *
hill_climbing(State, MAX) :-
    final_state(State, MAX),
    writeln("Chegou-se ao estado pretendido"),
    print_board(State).

hill_climbing(State, MAX) :-
    mutation(State, MAX, NState),
    print_board(NState),
    hill_climbing(NState, MAX).

start_hill :-
    NN is 4,
    writeln("Estado Inicial:"),
    initial_state(NN, Board),
    print_board(Board),
    hill_climbing(Board, NN).
*/
