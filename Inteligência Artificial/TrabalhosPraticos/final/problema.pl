/**
 * Limites
 */
min(-1).
maxRow(2).
maxColumn(6).

timer5(5).
timer15(15).
timer30(30).

/**
 * Jogadores
 */
next_player(0, 1).
next_player(1, 0).

/**
 * Cria a estrutura do jogo
 * game( [pontuação jogadores], [tabuleiro] )
 */
initial_state(game([0,0], [[4, 4, 4, 4, 4, 4], [4, 4, 4, 4, 4, 4]])).

/**
 * Estado Final
 */
final_state(game([P0, _], _)) :-
    P0 >= 25, !.
final_state(game([ _, P1], _)) :-
    P1 >= 25, !.
final_state(Game) :-
    cant_continue(Game), !.

/**
 * Utility
 */
utility([P0, P1], 49) :- P0  >  P1, !.
utility([P0, P1],  0) :- P0 =:= P1, !.
utility([P0, P1], -1) :- P0  <  P1, !.

/**
 * Imprime o resultado do jogo
 */
winner([P0, P1], 'O vencedor foi o computador'    ) :- P0  >  P1.
winner([P0, P1], 'Empate (computador-adversário)' ) :- P0 =:= P1.
winner(      _ , 'O vencedor foi o adversário'    ).

/**
 * Dado uma coordenada, mostra o número de sementes
 *
 * get_element(+Board, +Row, +Col, -Element).
 */
get_element(game(_, [[ Element, _, _, _, _, _]|_]), 0, 0, Element).
get_element(game(_, [[ _, Element, _, _, _, _]|_]), 0, 1, Element).
get_element(game(_, [[ _, _, Element, _, _, _]|_]), 0, 2, Element).
get_element(game(_, [[ _, _, _, Element, _, _]|_]), 0, 3, Element).
get_element(game(_, [[ _, _, _, _, Element, _]|_]), 0, 4, Element).
get_element(game(_, [[ _, _, _, _, _, Element]|_]), 0, 5, Element).
get_element(game(_, [_,[ Element, _, _, _, _, _]]), 1, 0, Element).
get_element(game(_, [_,[ _, Element, _, _, _, _]]), 1, 1, Element).
get_element(game(_, [_,[ _, _, Element, _, _, _]]), 1, 2, Element).
get_element(game(_, [_,[ _, _, _, Element, _, _]]), 1, 3, Element).
get_element(game(_, [_,[ _, _, _, _, Element, _]]), 1, 4, Element).
get_element(game(_, [_,[ _, _, _, _, _, Element]]), 1, 5, Element).

/**
 * Adiciona uma semente numa determinada zona do tabuleiro
 *
 * add_bean(+Board, +Row, +Col, -Board).
 */
add_bean(game(Pont, [[Element, Var1, Var2, Var3, Var4, Var5]|Tail]), 0, 0, game(Pont, [[E1, Var1, Var2, Var3, Var4, Var5]|Tail])) :- E1 is Element + 1.
add_bean(game(Pont, [[Var0, Element, Var2, Var3, Var4, Var5]|Tail]), 0, 1, game(Pont, [[Var0, E1, Var2, Var3, Var4, Var5]|Tail])) :- E1 is Element + 1.
add_bean(game(Pont, [[Var0, Var1, Element, Var3, Var4, Var5]|Tail]), 0, 2, game(Pont, [[Var0, Var1, E1, Var3, Var4, Var5]|Tail])) :- E1 is Element + 1.
add_bean(game(Pont, [[Var0, Var1, Var2, Element, Var4, Var5]|Tail]), 0, 3, game(Pont, [[Var0, Var1, Var2, E1, Var4, Var5]|Tail])) :- E1 is Element + 1.
add_bean(game(Pont, [[Var0, Var1, Var2, Var3, Element, Var5]|Tail]), 0, 4, game(Pont, [[Var0, Var1, Var2, Var3, E1, Var5]|Tail])) :- E1 is Element + 1.
add_bean(game(Pont, [[Var0, Var1, Var2, Var3, Var4, Element]|Tail]), 0, 5, game(Pont, [[Var0, Var1, Var2, Var3, Var4, E1]|Tail])) :- E1 is Element + 1.
add_bean(game(Pont, [Head,[Element, Var1, Var2, Var3, Var4, Var5]]), 1, 0, game(Pont, [Head,[E1, Var1, Var2, Var3, Var4, Var5]])) :- E1 is Element + 1.
add_bean(game(Pont, [Head,[Var0, Element, Var2, Var3, Var4, Var5]]), 1, 1, game(Pont, [Head,[Var0, E1, Var2, Var3, Var4, Var5]])) :- E1 is Element + 1.
add_bean(game(Pont, [Head,[Var0, Var1, Element, Var3, Var4, Var5]]), 1, 2, game(Pont, [Head,[Var0, Var1, E1, Var3, Var4, Var5]])) :- E1 is Element + 1.
add_bean(game(Pont, [Head,[Var0, Var1, Var2, Element, Var4, Var5]]), 1, 3, game(Pont, [Head,[Var0, Var1, Var2, E1, Var4, Var5]])) :- E1 is Element + 1.
add_bean(game(Pont, [Head,[Var0, Var1, Var2, Var3, Element, Var5]]), 1, 4, game(Pont, [Head,[Var0, Var1, Var2, Var3, E1, Var5]])) :- E1 is Element + 1.
add_bean(game(Pont, [Head,[Var0, Var1, Var2, Var3, Var4, Element]]), 1, 5, game(Pont, [Head,[Var0, Var1, Var2, Var3, Var4, E1]])) :- E1 is Element + 1.

/**
 * Remove e devolve as sementes de uma coordenada
 *
 * remove_beans(+Board, +Row, +Col, -Board, -Element)
 */
remove_beans(game(Pont, [[Element, Var1, Var2, Var3, Var4, Var5]|Tail]), 0, 0, game(Pont, [[0, Var1, Var2, Var3, Var4, Var5]|Tail]), Element).
remove_beans(game(Pont, [[Var0, Element, Var2, Var3, Var4, Var5]|Tail]), 0, 1, game(Pont, [[Var0, 0, Var2, Var3, Var4, Var5]|Tail]), Element).
remove_beans(game(Pont, [[Var0, Var1, Element, Var3, Var4, Var5]|Tail]), 0, 2, game(Pont, [[Var0, Var1, 0, Var3, Var4, Var5]|Tail]), Element).
remove_beans(game(Pont, [[Var0, Var1, Var2, Element, Var4, Var5]|Tail]), 0, 3, game(Pont, [[Var0, Var1, Var2, 0, Var4, Var5]|Tail]), Element).
remove_beans(game(Pont, [[Var0, Var1, Var2, Var3, Element, Var5]|Tail]), 0, 4, game(Pont, [[Var0, Var1, Var2, Var3, 0, Var5]|Tail]), Element).
remove_beans(game(Pont, [[Var0, Var1, Var2, Var3, Var4, Element]|Tail]), 0, 5, game(Pont, [[Var0, Var1, Var2, Var3, Var4, 0]|Tail]), Element).
remove_beans(game(Pont, [Head,[Element, Var1, Var2, Var3, Var4, Var5]]), 1, 0, game(Pont, [Head,[0, Var1, Var2, Var3, Var4, Var5]]), Element).
remove_beans(game(Pont, [Head,[Var0, Element, Var2, Var3, Var4, Var5]]), 1, 1, game(Pont, [Head,[Var0, 0, Var2, Var3, Var4, Var5]]), Element).
remove_beans(game(Pont, [Head,[Var0, Var1, Element, Var3, Var4, Var5]]), 1, 2, game(Pont, [Head,[Var0, Var1, 0, Var3, Var4, Var5]]), Element).
remove_beans(game(Pont, [Head,[Var0, Var1, Var2, Element, Var4, Var5]]), 1, 3, game(Pont, [Head,[Var0, Var1, Var2, 0, Var4, Var5]]), Element).
remove_beans(game(Pont, [Head,[Var0, Var1, Var2, Var3, Element, Var5]]), 1, 4, game(Pont, [Head,[Var0, Var1, Var2, Var3, 0, Var5]]), Element).
remove_beans(game(Pont, [Head,[Var0, Var1, Var2, Var3, Var4, Element]]), 1, 5, game(Pont, [Head,[Var0, Var1, Var2, Var3, Var4, 0]]), Element).

/**
 * Devolve a pontuação
 */
get_score(game(Pont, _), Pont).

get_score_player(game([P0,  _], _), 0, P0).
get_score_player(game([ _, P1], _), 1, P1).
/**
 * Compara a pontuação atual da antiga
 * compare_score(+Prev, +Act, -Value)
 */
compare_score(P, A, Diff) :- A > P, Diff is A - P, !.
compare_score(_, _, 0).

/**
 * Guarda a quantidade de sementes à pontuação do jogador
 * store_beans( +Board, +Beans, +Player, -NewBoard).
 */
store_beans(game([Sc|Tail],Board), Beans, 0, game([S1|Tail],Board)) :- S1 is Sc + Beans.
store_beans(game([Head,Sc],Board), Beans, 1, game([Head,S1],Board)) :- S1 is Sc + Beans.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                PREDICADOS QUE DEFINEM UMA JOGADA                 %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Predicado que fornece a próxima posição
 * (Tendo em conta o tabuleiro e a movimentação)
 */
next_position(0, 0, 1, 0).
next_position(0, Col, 0, NCol) :-
    NCol is Col - 1.
next_position(1, 5, 0, 5).
next_position(1, Col, 1, NCol) :-
    NCol is Col + 1.

/**
 * Calcula as proximas coordenadas
 * "Val" posições a seguir
 */
next_n_positions(0, X, Y, X, Y) :- !.
next_n_positions(Val, X, Y, FX, FY) :-
    V1 is Val - 1,
    next_position(X, Y, NX, NY), !,
    next_n_positions(V1, NX, NY, FX, FY).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Predicado que percorre o tabuleiro no sentido anti-horário (direita -> cima -> esquerda -> baixo)
 * com o objectivo de adicionar feijões.
 *
 * add_n_beans_board(+Beans, +Row, +Col, +Board, -ResultBoard)
 */
add_n_beans_board(0, _, _, Board, Board) :- !.
add_n_beans_board(Beans, Row, Col, Board, Res) :-
    add_bean(Board, Row, Col, NBoard),
    B1 is Beans - 1,
    next_position(Row, Col, NRow, NCol),
    add_n_beans_board(B1, NRow, NCol, NBoard, Res).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Predicado que colecta os feijões
 * collect_beans( +Board, +Row, +Column, +Player, -Final Board, -Collected Beans).
 */
collect_beans(Board, Row, Col, Player, FinalBoard, CollectedBeans) :-
    collect_beans(Board, Row, Col, Player, 0, FinalBoard, CollectedBeans).

% Computador (Player = 0)
% Se não está no campo do adversário
collect_beans(Board, Row, _, 0, CollectedBeans, Board, CollectedBeans) :-
    Row =:= 0.

% Se já passou o limite máximo de colunas
collect_beans(Board, _, Col, 0, CollectedBeans, Board, CollectedBeans) :-
    Col < 0.

% Se o número de feijões não está entre [2,3]
collect_beans(Board, Row, Col, 0, CollectedBeans, Board, CollectedBeans) :-
    get_element(Board, Row, Col, NBeans),
    \+ (NBeans >= 2, NBeans =< 3).

% Colecta os feijões
collect_beans(Board, Row, Col, 0, Aux, FinalBoard, CollectedBeans) :-
    remove_beans(Board, Row, Col, NBoard, Collect),
    NCol is Col - 1,
    NAux is Aux + Collect,
    collect_beans(NBoard, Row, NCol, 0, NAux, FinalBoard, CollectedBeans).

% Inimigo (Player = 1)
% Se não está no campo do adversário
collect_beans(Board, Row, _, 1, CollectedBeans, Board, CollectedBeans) :-
    Row =:= 1.

% Se já passou o limite máximo de colunas
collect_beans(Board, _, Col, 1, CollectedBeans, Board, CollectedBeans) :-
    Col > 5.

% Se o número de feijões não está entre [2,3]
collect_beans(Board, Row, Col, 1, CollectedBeans, Board, CollectedBeans) :-
    get_element(Board, Row, Col, NBeans),
    \+ (NBeans >= 2, NBeans =< 3).

collect_beans(Board, Row, Col, 1, Aux, FinalBoard, CollectedBeans) :-
    remove_beans(Board, Row, Col, NBoard, Collect),
    NCol is Col + 1,
    NAux is Aux + Collect,
    collect_beans(NBoard, Row, NCol, 1, NAux, FinalBoard, CollectedBeans).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Um jogador não pode escolher uma posição sem sementes
 * Se nalguma posicao do tabuleiro do jogador tiver um espaço com
 * mais de uma semente, o jogador não poderá escolher i espaço que
 * contém a semente.
 */
constraints_move(game(_, Map), _, 0) :-
    empty_board(Map).

constraints_move(game(_, Map), Player, 1) :-
    nth0(Player, Map, Line),
    ones_in_line(Line).

constraints_move( _, _, Beans) :-
    Beans > 1.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Movimento de um jogador
 * 1º Retira as sementes
 * 2º Distribui essas sementes pelos restantes espaços
 * 3º Verficar se a última semente posta está no campo do adversário, se sim, irá se retirar sementes tendo em conta as regras do jogo
 * 4ª Adicionar as sementes apanhadas na pontuação
 */
player_move(Board, Row, Col, Player, ResultBoard) :-
    remove_beans(Board, Row, Col, NBoard, Beans),
    constraints_move(Board, Player, Beans), % Verifica se a ação cumpre as normas
    next_position(Row, Col, NRow, NCol), % Avança 1 posição
    add_n_beans_board(Beans, NRow, NCol, NBoard, ResBoard),
    next_n_positions(Beans, Row, Col, FRow, FCol),
    collect_beans(ResBoard, FRow, FCol, Player, FBoard, CollectedBeans),
    store_beans(FBoard, CollectedBeans, Player, ResultBoard).

/**
 * Jogada no espaço de estados, em que o Player corresponde à linha
 * do tabuleiro
 */
op(Board, Col, Player, NBoard) :-
    player_move(Board, Player, Col, Player, NBoard).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Verifica se a quantidade de feijões em cada espaço 
 * da linha equivale a um ou a zero
 */
ones_in_line([]) :- !.
ones_in_line([Head|Tail]) :-
    Head =< 1,
    ones_in_line(Tail).

/**
 * Verificar se uma linha e do tabuleiro está vazia
 */
empty_line(game(_, [[0,0,0,0,0,0], _]), 0).
empty_line(game(_, [_, [0,0,0,0,0,0]]), 1).

/**
 * Verifica se o tabuleiro está vazio
 */
empty_board(game(_, [[0,0,0,0,0,0],[0,0,0,0,0,0]])).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Se um lado tem sementes mas o outro não TENTA PASSAR AS SEMENTES.

 * É enviado um valor que indica se foi possivel distruibuir as sementes ou não.
 * Se não foi possível, o jogo pára.
 * possible_beans( +Board, +Player, -Index).
 */
possible_beans(Board, Player, Index) :-
    possible_beans(Board, Player, 0, Index).

/**
 * possible_beans(+Board, +Player, +Index, -Index)
 */
% Falha se sair do limite máximo de colunas
possible_beans(_, _, Ind, -1) :-
    maxColumn(Ind), !, fail.

% Player 0    diração: <-| (esquerda)
possible_beans(Board, 0, Ind, Ind) :-
    get_element(Board, 0, Ind, Ele),
    RemainBeans is Ele - (Ind+1),
    RemainBeans >= 0, !.
possible_beans(Board, 0, Ind, Res) :-
    I1 is Ind + 1,
    possible_beans(Board, 0, I1, Res).

% Player 1    direção: |-> (direita)
possible_beans(Board, 1, Ind, Ind) :-
    get_element(Board, 1, Ind, Ele),
    LeftPos is 5 - Ind,
    RemainBeans is Ele - (LeftPos+1),
    RemainBeans >= 0, !.
possible_beans(Board, 1, Ind, Res) :-
    I1 is Ind + 1,
    possible_beans(Board, 1, I1, Res).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * has_beans_in_way(+Board, +Player, +Col, +Beans)
 */
has_beans_in_way(_, _, _, -1) :- !, fail.
has_beans_in_way(_, _, _,  6) :- !, fail.

has_beans_in_way(Board, 0, Col, _) :-
    \+ get_element(Board, 0, Col, 0), !.
has_beans_in_way(Board, 0, Col, Beans) :-
    next_position(0, Col, NRow, NCol),
    B1 is Beans - 1,
    has_beans_in_way(Board, NRow, NCol, B1).

has_beans_in_way(Board, 1, Col, _) :-
    \+ get_element(Board, 1, Col, 0), !.
has_beans_in_way(Board, 1, Col, Beans) :-
    next_position(1, Col, NRow, NCol),
    B1 is Beans + 1,
    has_beans_in_way(Board, NRow, NCol, B1).

/**
 * Se chega ao tabuleiro do adversário e que, no espaço onde
 * parar, contenha sementes
 */
reach_opponent_beans(Board, 0) :-
    reach_opponent_beans(Board, 0, 5).

reach_opponent_beans(Board, 1) :-
    reach_opponent_beans(Board, 1, 0).

% Player 0    diração: <-| (esquerda)
reach_opponent_beans(_, 0, Ind) :-
    min(Val), Ind =< Val, !, fail.
reach_opponent_beans(Board, 0, Ind) :-
    get_element(Board, 0, Ind, Ele),
    RemainBeans is Ele - (Ind+1),
    RemainBeans >= 0,
    has_beans_in_way(Board, 1, 0, RemainBeans).
reach_opponent_beans(Board, 0, Ind) :-
    I1 is Ind - 1,
    reach_opponent_beans(Board, 0, I1).

% Player 1    direção: |-> (direita)
reach_opponent_beans(_, 1, Ind) :-
    maxColumn(Val), Ind >= Val, !, fail.
reach_opponent_beans(Board, 1, Ind) :-
    get_element(Board, 1, Ind, Ele),
    LeftPos is 5 - Ind,
    RemainBeans is Ele - (LeftPos+1),
    RemainBeans >= 0,
    has_beans_in_way(Board, 0, 5, RemainBeans).
reach_opponent_beans(Board, 1, Ind) :-
    I1 is Ind + 1,
    reach_opponent_beans(Board, 1, I1).

/**
 * Verificar se já não é possível fazer mais nenhuma jogada
 */
cant_continue(Board) :-
    \+ ( reach_opponent_beans(Board, 0) ; reach_opponent_beans(Board, 1) ).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Algoritmo que verifica se o jogador tem ou não peças.
 * Se não tiver e for possivel retirar do adversário, fá-lo.
 *
 * pass_beans(+Board, +Player, -PassOrNot, -NBoard).
 */
pass_beans(Board, Player, PassOrNot, NBoard) :-
    % Se ao realizar um movimento o jogador actual fica sem sementes
    empty_line(Board, Player),

    % Vê se o adversário consegue partilhar sementes
    next_player(Player, NPlayer),
    possible_beans(Board, NPlayer, Ind),
    (
	    % Se não é possível colocar sementes no campo do adversário
	    (Ind =:= -1) ->
	    (
	        NBoard = Board,
	        PassOrNot is -1
	    );
	    (
	        remove_beans(Board, NPlayer, Ind, AuxBoard, NBeans),
	        next_position(NPlayer, Ind, Row, Col),
	        add_n_beans_board(NBeans, Row, Col, AuxBoard, NBoard),
	        PassOrNot is 1
	    )
    ).

% Se nenhum lado do tabuleiro está vazio, pode continuar
pass_beans(Board, _, 1, Board).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Recoher os feijões quando não houver mais jogadas.
 * (Usado nos ficheiros "minimas & alpha_beta")
 *
 * remove_beans_line(+Board, +Row, -CollectedBeans, -NBoard).
 */
remove_beans_line(Board, Row, CollectedBeans, NBoard) :-
    remove_beans_line(Board, Row, 5, 0, CollectedBeans, NBoard ).

/**
 * remove_beans_line(+Board, +Row, +Col, +CollectedBeans, -CollectedBeans, -NBoard).
 */
remove_beans_line( Board, _, -1, CollectedBeans, CollectedBeans, Board ) :- !.
remove_beans_line( Board, Row, Col, Beans, CollectedBeans, ResBoard ) :-
    remove_beans( Board, Row, Col, ResBoard, NBeans),
    C1 is Col - 1,
    AuxBeans is Beans + NBeans,
    remove_beans_line(ResBoard, Row, C1, AuxBeans, CollectedBeans, ResBoard).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/**
 * Imprime o tabuleiro
 *
 * print_board( +Board ).
 */
print_board(game( [P0, P1], [Line0, Line1] )) :-
    writeln("\n  Tabuleiro"),
    writeln(Line0),
    writeln(Line1),
    writef("Jogador 0: '%w' pontos\n",   [P0]),
    writef("Jogador 1: '%w' pontos\n\n", [P1]).
