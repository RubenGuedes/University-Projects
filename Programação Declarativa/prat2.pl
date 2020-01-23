% 						Portugal_Continental
distrito(viana_do_castelo).
distrito(braga).
distrito(vila_real).
distrito(braganca).
distrito(porto).
distrito(aveiro).
distrito(viseu).
distrito(guarda).
distrito(coimbra).
distrito(castelo_branco).
distrito(leiria).
distrito(santarem).
distrito(portalegre).
distrito(lisboa).
distrito(setubal).
distrito(evora).
distrito(beja).
distrito(faro).

% 						Regioes_Autonomas
distrito(madeira).
distrito(acores).

% 					Capitais_de_Portugal_Continental
capital(viana_do_castelo, viana_do_castelo).
capital(braga, braga).
capital(vila_real, vila_real).
capital(braganca, braganca).
capital(porto, porto).
capital(aveiro, aveiro).
capital(viseu, viseu).
capital(guarda, guarda).
capital(coimbra, coimbra).
capital(castelo_branco, castelo_branco).
capital(leiria, leiria).
capital(santarem, santarem).
capital(portalegre, portalegre).
capital(lisboa, lisboa).
capital(setubal, setubal).
capital(evora, evora).
capital(beja, beja).
capital(faro, faro).

%					Capitais_das_regioes_autonomas
capital(funchal, madeira).
capital(ponta_delgada, acores).

% 							Adjacentes
% 								faro
a(faro, beja).
% 								beja
a(beja, setubal).
a(beja, evora).
%								 setubal
a(setubal, evora).
a(setubal, santarem).
% 								evora
a(evora, santarem).
a(evora, portalegre).
% 								portalegre
a(portalegre, santarem).
a(portalegre, castelo_branco).
% 								santarem
a(santarem, lisboa).
a(santarem, leiria).
a(santarem, castelo_branco).
% 								lisboa
a(lisboa, leiria).
% 								leiria.
a(leiria, coimbra).
a(leiria, castelo_branco).
% 							castelo branco
a(castelo_branco, coimbra).
a(castelo_branco, guarda).
% 								coimbra
a(coimbra, guarda).
a(coimbra, viseu).
a(coimbra, aveiro).
% 								aveiro
a(aveiro, viseu).
a(aveiro, porto).
% 								viseu
a(viseu, guarda).
a(viseu, braganca).
a(viseu, vila_real).
a(viseu, porto).
% 								guarda
a(guarda, braganca).
%								braganca
a(braganca, vila_real).
% 								vila real
a(vila_real, porto).
a(vila_real, braga).
% 								porto
a(porto, braga).
% 								braga
a(braga, viana_do_castelo).

%							Linhas
l(valenca, viana_do_castelo, 25).
l(viana_do_castelo, barcelos, 14).
l(barcelos, braga, 20).
l(barcelos, guimaraes, 40).
l(barcelos, porto, 40).
l(braga, porto, 30).
l(guimaraes, porto, 25).
l(porto, caide, 41).
l(caide, regua, 20).
l(regua, tua, 15).
l(tua, pocinho, 5).
l(porto, espinho, 10).


% 							Regras
adjacente(A, B) :- a(A, B) ; a(B, A).
linha(X, Y, A) :- l(X, Y, A) ; l(Y, X, A).