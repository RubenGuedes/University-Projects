function enter(c, dx, dy, sx, sy, r, fs){
	c.save();
	c.translate(dx, dy);
	c.rotate(r);
	c.scale(sx, sy);
	c.fillStyle = fs;
}
function leave(c){
	c.fill();
	c.restore();
}
// Função Main
function main() {
	var ctx = document.getElementById("myCanvas").getContext("2d");
	imagem(ctx);
}
// ################################
// 		Função que une os objetos
function imagem(cont){
	// Fundo		// done
	let fundo_x = myCanvas.width;
	let fundo_y = myCanvas.height;

	fundo(cont, fundo_x, fundo_y);
	// ----------------------------------------------
	// Lua			// done
	let lua_x = myCanvas.width/2+15;
	let lua_y = myCanvas.height*(51/100);
	let raio = myCanvas.width/2;

	lua(cont, lua_x, lua_y, raio, "#ffffe6");   	
	// ----------------------------------------------

	vedacao(cont, 0, 730, 42, fundo_x, "#403326"); 	// done	

	chao(  cont, "#403326"); 	// done

	arvore(cont, "#403326");	// done

	corvo( cont, 370,  9, 9.3,  9.2, "#403326"); // done

	colocar_folhas(cont, "#403326"); // done

	rapariga( cont,   6, 398,  35,   33, "#403326"); // done
	

	escritora(cont, 195, 695, "#403326");	// done	
}
// ################################
// 			FUNDO DA IMAGEM
function fundo(c, fundo_x, fundo_y){
	c.beginPath();
	c.save();
	c.scale(fundo_x, fundo_y);

	fundo_estrutura(c); 	

	c.restore();
	c.closePath();
}
function fundo_estrutura(c){
	// Gradiente
	var gradient = c.createLinearGradient(0, 0, 0, 1);
	gradient.addColorStop(1   , "#f9f9d2"); // castanho claro
	gradient.addColorStop(0.45, "#f9f9d2"); // castanho claro
	gradient.addColorStop(0   , "#4d3900"); // castanho escuro
	c.fillStyle = gradient; 
	c.fillRect( 0, 0, 1, 1);
}
// ################################
// 				LUA
function lua(c, x, y, raio, color){
	enter( c, x, y, raio, raio, 0, color); 
	lua_estrutura(c);   	
	leave(c);
}

function lua_estrutura(c){
	c.beginPath();
	c.arc( 0, 0, 1, 0, 2*Math.PI);
	c.closePath();
}
// ################################
// 				VEDAÇÃO
// n = numero de pés
function vedacao(c, coord_x, coord_y, quantidade, comprimento, color){

	n_pes_vedacao(c, coord_y, quantidade, comprimento, color);

	enter(c, 0, coord_y+4, comprimento, 25, 0, color);
	tabua_uniao(c);
	leave(c);
}
// cria n pes
function n_pes_vedacao(c, coord_y, n, comprimento, color){
	for (var i = 0; i <= n; i++) {
		enter(c, i*(comprimento/n), coord_y, 30, 130, 0, color);
		pe_vedacao(c);
		leave(c);
	}
}
// cria um pe
function pe_vedacao(c){
	let largura = 0.2;
	let altura  = 0.4;

	c.beginPath();
	c.rect( -0.1, 0, largura, altura);
	c.arc(     0, 0, largura/2 , 0, Math.PI, true);
	c.closePath();
}
// tabua que une os pes da vedação
function tabua_uniao(c){
	c.beginPath();
	c.fillRect( 0, -0.1, 1, 0.2);
	c.closePath();
}
// ################################
//			   CHÃO
function chao(c, color){
	enter(c, 240, 770, 780, 80, 0, color);
	chao_estrutura(c);
	leave(c);
}
function chao_estrutura(c){
	c.beginPath();
	c.moveTo( -0.32, -0.11); // a
	c.quadraticCurveTo( -0.24, -0.09, -0.24, -0.15); // b
	c.quadraticCurveTo( -0.14, -0.03, -0.06, -0.11); // c
	c.lineTo(  0.09, -0.14); // d
	c.lineTo(  0.18, -0.13); // e
	c.quadraticCurveTo(  0.25, -0.02,  0.32, -0.13); // f
	c.lineTo(  0.33, -0.10); // g
	c.lineTo(  0.33,  0.40); // h
	c.lineTo( -0.32,  0.40); // i
	c.closePath();
}
// ################################
// 			  ÁRVORE
function arvore(c, color){
	// tronco
	enter( c, 0 , 140, 200, 790, 0, color);
	tronco(c);
	leave(c);
	// ramo_1
	enter( c, 260, 420, 270, 300, 0, color);
	ramo_1(c)
	leave(c);
	// ramo_2()
	enter( c, 230, 280, 290, 270, 0, color);
	ramo_2(c)
	leave(c);
	// ramo_3()
	enter( c, 242, 200, 290, 330, 0, color);
	ramo_3(c)
	leave(c);
	// ramo_4()
	enter( c, 230, 125, 250, 210, 0, color);
	ramo_4(c)
	leave(c);
	// ramo_5()
	enter( c, 74, 115, 177, 220, 0, color);
	ramo_5(c)
	leave(c);
	// ramo_6()
	enter( c, 20, 120, 200, 200, Math.PI*(335/180), color);
	ramo_5(c)
	leave(c);
}
function tronco(c){
	c.beginPath();
	c.moveTo( 0, 0.05); // a
	c.lineTo( 0.07, 0.1) // b
	c.lineTo( 0.4, 0.1) // c
	c.quadraticCurveTo( 0.23, 0.17, 0.24, 0.22) // d
	c.lineTo( 0.26, 0.24); // e
	c.quadraticCurveTo( -0.02, 0.50, 0.24, 0.80); // f
	c.lineTo( 0, 0.8) // g 
	c.lineTo( 0, 0.05) // a´ 
	c.closePath();
}
function ramo_1(c){
	c.beginPath();
	c.moveTo( -0.81, -0.37);                         // a
	c.quadraticCurveTo( -0.71, -0.35, -0.62, -0.34); // b
	c.quadraticCurveTo( -0.50, -0.36,  0.08,  0.01); // c
	c.quadraticCurveTo(  0.47,  0.30,  0.77,  0.17); // d
	c.lineTo(  0.78,  0.175);                         // e
	c.quadraticCurveTo(  0.72,  0.20,  0.70,  0.21); // f
	c.quadraticCurveTo(  0.75,  0.19,  0.81,  0.19); // g
	c.lineTo(  0.81,  0.20);                         // h
	c.quadraticCurveTo(  0.73,  0.21,  0.72,  0.23); // i
	c.lineTo(  0.79,  0.24);                         // j
	c.lineTo(  0.78,  0.25);                         // k
	c.quadraticCurveTo(  0.74,  0.25,  0.68,  0.24); // l
	c.quadraticCurveTo(  0.40,  0.27,  0.00,  0.01); // m
	c.quadraticCurveTo( -0.30, -0.18, -0.48, -0.24); // n
	c.quadraticCurveTo( -0.60, -0.26, -0.70, -0.26); // o
	c.quadraticCurveTo( -0.65, -0.20, -0.63, -0.19); // p
	c.lineTo( -0.64, -0.18);                         // q
	c.lineTo( -0.70, -0.22);                         // r
	c.quadraticCurveTo( -0.68, -0.12, -0.65, -0.12); // s
	c.lineTo( -0.66, -0.10);                         // t
	c.quadraticCurveTo( -0.72, -0.15, -0.72, -0.17); // u
	c.quadraticCurveTo( -0.72, -0.20, -0.75, -0.24); // v
	c.quadraticCurveTo( -0.80, -0.23, -0.81, -0.20); // w
	c.lineTo( -0.81, -0.37);                         // a´ 
	c.closePath();
}
function ramo_2(c){
	c.beginPath();
	c.moveTo( -0.72, -0.20); 						 // a
	c.quadraticCurveTo( -0.60, -0.23, -0.50, -0.24); // b
	c.quadraticCurveTo( -0.20, -0.25,  0.10,  0.08); // c
	c.quadraticCurveTo(  0.50,  0.30,  0.84,  0.10); // d
	c.quadraticCurveTo(  0.75,  0.21,  0.60,  0.22); // e
	c.quadraticCurveTo(  0.30,  0.24,  0.10,  0.12); // f
	c.quadraticCurveTo(  0.10,  0.10,  0.20,  0.20); // g							 // g
	c.lineTo( 0.18, 0.2005); 						 // h
	c.lineTo( 0.12, 0.15);                           // i
	c.quadraticCurveTo(  0.13,  0.20,  0.20,  0.25); // j
	c.lineTo( 0.195, 0.26); 						 // k
	c.quadraticCurveTo(  0.10,  0.20,  0.08,  0.13); // l
	c.quadraticCurveTo(  0.05,  0.15,  0.10,  0.23); // m
	c.lineTo( 0.095, 0.235); 						 // n
	c.quadraticCurveTo(  0.06,  0.22,  0.05,  0.17); // o
	c.lineTo( 0.015, 0.20); 						 // p
	c.lineTo( 0.013, 0.19); 						 // q
	c.quadraticCurveTo(  0.07,  0.12,  0.03,  0.09); // r
	c.quadraticCurveTo( -0.08, -0.07, -0.20, -0.10); // s
	c.quadraticCurveTo( -0.20, -0.05, -0.17, -0.01); // t
	c.lineTo( -0.20, -0.05);           				 // u
	c.quadraticCurveTo( -0.20, -0.06, -0.21,  0.00); // v
	c.quadraticCurveTo( -0.20, -0.10, -0.25, -0.15); // w
	c.quadraticCurveTo( -0.50, -0.25, -0.70, -0.10); // x
	c.lineTo( -0.72, -0.20); // a´
	c.closePath();
}
function ramo_3(c){
	c.beginPath();
	c.moveTo( -0.70, 0.10); 						 // a
	c.quadraticCurveTo( -0.45, -0.20, -0.10, -0.15); // b
	c.quadraticCurveTo(  0.00, -0.18,  0.10, -0.17); // c
	c.lineTo( 0.11, -0.16); 						 // d
	c.quadraticCurveTo( 0.05, -0.165, -0.04, -0.14); // e
	c.quadraticCurveTo( 0.05, -0.08 ,  0.16, -0.10); // f
	c.quadraticCurveTo( 0.05, -0.07 , -0.05, -0.12); // g
	c.quadraticCurveTo( -0.10, -0.13, -0.19, -0.14); // h
	c.quadraticCurveTo( -0.30, -0.12, -0.40, -0.10); // i
	c.quadraticCurveTo( -0.15, -0.09,  0.02, -0.02); // j
	c.quadraticCurveTo(  0.15, -0.045, 0.29, -0.04); // k
	c.quadraticCurveTo(  0.40, -0.10,  0.50, -0.10); // l
	c.lineTo(                          0.53, -0.09); // m
	c.quadraticCurveTo(  0.40, -0.10,  0.32, -0.02); // n
	c.lineTo( 						   0.09,  0.00); // o
	c.quadraticCurveTo(  0.30,  0.08,  0.38,  0.07); // p
	c.quadraticCurveTo(  0.60, 0.005,  0.70,  0.00); // q
	c.lineTo( 						   0.85, -0.09); // r
	c.lineTo( 						   0.73,  0.01); // s
	c.lineTo( 						   0.50,  0.06); // t
	c.quadraticCurveTo(  0.70,  0.08,  0.80,  0.10); // u
	c.lineTo( 						   0.66,  0.09); // v
	c.lineTo( 						   0.79,  0.12); // w
	c.quadraticCurveTo(  0.62,  0.09,  0.44,  0.08); // x
	c.quadraticCurveTo(  0.50,  0.13,  0.53,  0.12); // y
	c.quadraticCurveTo(  0.55,  0.15,  0.64,  0.18); // z
	c.lineTo(  0.63, 0.19); // extra
	c.quadraticCurveTo(  0.50,  0.14,  0.42,  0.10); // aa
	c.lineTo( 0.43, 0.12);                           // ab
	c.quadraticCurveTo(  0.10,  0.05, -0.09, -0.02); // ac
	c.quadraticCurveTo( -0.35, -0.06, -0.40, -0.07); // ad
	c.quadraticCurveTo( -0.60,  0.00, -0.60,  0.10); // ae
	c.lineTo( -0.70,  0.10);                         // a´
	
	c.closePath();
}
function ramo_4(c){
	c.beginPath();
	c.moveTo( -0.75, 0.52); // a
	c.quadraticCurveTo( -0.80,  0.35, -0.60,  0.19); // b
	c.quadraticCurveTo( -0.47,  0.20, -0.41,  -0.41); // c
	c.quadraticCurveTo( -0.45, -0.45, -0.46, -0.51); // d
	c.quadraticCurveTo( -0.41, -0.42, -0.40, -0.42); // e
	c.lineTo( -0.38, -0.44); // f
	c.quadraticCurveTo( -0.40, -0.40, -0.39, -0.37); // g
	c.quadraticCurveTo( -0.35, -0.40, -0.35, -0.47); // h
	c.quadraticCurveTo( -0.38, -0.46, -0.40, -0.53); // i
	c.lineTo( -0.38, -0.51); // j
	c.lineTo( -0.39, -0.54); // k
	c.lineTo( -0.35, -0.50); // l
	c.lineTo( -0.34, -0.60); // m
	c.lineTo( -0.32, -0.60); // n
	c.quadraticCurveTo( -0.32, -0.49, -0.31, -0.47); // o
	c.lineTo( -0.23, -0.60); // p
	c.lineTo( -0.20, -0.60); // q
	c.quadraticCurveTo( -0.25, -0.50, -0.30, -0.40); // r
	c.lineTo( -0.16, -0.50); // s
	c.quadraticCurveTo( -0.16, -0.53, -0.15, -0.55); // t
	c.lineTo( -0.16, -0.60); // u
	c.lineTo( -0.14, -0.60); // v
	c.lineTo( -0.14, -0.57); // w
	c.lineTo( -0.12, -0.60); // x
	c.quadraticCurveTo( -0.12, -0.55, -0.14, -0.51); // y
	c.quadraticCurveTo( -0.11, -0.55, -0.09, -0.60); // z
	c.lineTo( -0.07, -0.60); // aa
	c.lineTo( -0.10, -0.55); // ab
	c.quadraticCurveTo( -0.05, -0.57, -0.04, -0.58); // ac
	c.lineTo( -0.05, -0.57); // ad
	c.lineTo(  0.00, -0.57); // ae
	c.quadraticCurveTo( -0.05, -0.55, -0.08, -0.54); // af
	c.quadraticCurveTo( -0.15, -0.50, -0.18, -0.46); // ag
	c.quadraticCurveTo( -0.10, -0.46, -0.05, -0.52); // ah
	c.lineTo( -0.06, -0.50); // ai
	c.lineTo( -0.03, -0.51); // aj
	c.quadraticCurveTo( -0.07, -0.47, -0.10, -0.45); // ak
	c.quadraticCurveTo( -0.01, -0.45,  0.06, -0.40); // al
	c.lineTo( -0.04, -0.40); // am
	c.quadraticCurveTo( -0.01, -0.38,  0.01, -0.37); // an
	c.quadraticCurveTo( -0.05, -0.37, -0.09, -0.42); // ao
	c.quadraticCurveTo( -0.15, -0.44, -0.20, -0.43); // ap
	c.quadraticCurveTo( -0.39, -0.30, -0.39, -0.20); // aq
	c.quadraticCurveTo( -0.27, -0.25, -0.19, -0.40); // ar
	c.quadraticCurveTo( -0.20, -0.30, -0.27, -0.24); // as
	c.lineTo( -0.20, -0.29); // at
	c.lineTo( -0.21, -0.26); // au
	c.quadraticCurveTo( -0.40, -0.17, -0.45,  0.04); // av
	c.quadraticCurveTo( -0.37, -0.08, -0.35, -0.09); // aw
	c.lineTo( -0.32, -0.18); // ax
	c.lineTo( -0.32, -0.16); // ay
	c.quadraticCurveTo( -0.30, -0.15, -0.28, -0.17); // az
	c.lineTo( -0.26, -0.16); // ba
	c.lineTo( -0.24, -0.19); // bb
	c.lineTo( -0.23, -0.17); // bc
	c.lineTo( -0.26, -0.12); // bd
	c.lineTo( -0.22, -0.13); // be
	c.lineTo( -0.23, -0.11); // bf
	c.quadraticCurveTo( -0.35, -0.10, -0.41,  0.04); // bg
	c.quadraticCurveTo( -0.39,  0.01, -0.35,  0.00); // bh
	c.lineTo( -0.34, 0.01); // bi
	c.quadraticCurveTo( -0.45,  0.06, -0.50,  0.14); // bj
	c.quadraticCurveTo( -0.45,  0.13, -0.38,  0.08); // bk
	c.lineTo( -0.40, 0.10); // bl
	c.quadraticCurveTo( -0.16, -0.12, -0.17, -0.24); // bm
	c.lineTo( -0.16, -0.22); // bn
	c.lineTo( -0.12, -0.29); // bo
	c.lineTo( -0.12, -0.27); // bp
	c.lineTo( -0.09, -0.29); // bq
	c.quadraticCurveTo( -0.15, -0.20, -0.14, -0.17); // br
	c.quadraticCurveTo(  0.05, -0.30,  0.09, -0.37); // bs
	c.lineTo(  0.30, -0.60); // bt
	c.lineTo(  0.33, -0.60); // bu
	c.quadraticCurveTo(  0.26,  -0.50,  0.14, -0.39); // bv
	c.quadraticCurveTo(  0.40,  -0.53,  0.58, -0.60); // bw
	c.lineTo( 0.62, -0.60); // bx
	c.quadraticCurveTo( 0.35,  -0.48, 0.10, -0.30); // by
	c.quadraticCurveTo( 0.60, -0.22,  1.00, -0.60); // bz
	c.lineTo( 1.00, -0.58);
	c.quadraticCurveTo( 0.70, -0.25, 0.48, -0.30); // ca
	c.quadraticCurveTo( 0.80, -0.25, 0.93, -0.29); // cb
	c.quadraticCurveTo( 0.85, -0.21, 0.65, -0.25); // cc
	c.lineTo( 0.99, -0.20); // cd
	c.lineTo( 0.93, -0.18); // ce
	c.lineTo( 0.80, -0.20); // cf
	c.quadraticCurveTo( 0.60,  -0.22,  0.42, -0.25); // cg
	c.quadraticCurveTo( 0.25,  -0.28,  0.06, -0.25); // ch
	c.quadraticCurveTo( -0.10, -0.20, -0.19, -0.08); // ci
	c.quadraticCurveTo( -0.28,  0.10, -0.45,  0.18); // cj
	c.quadraticCurveTo( -0.70, 0.30,  -0.70, 0.52); // ck
	c.lineTo( -0.75, 0.52);

	c.closePath();
}
function ramo_5(c){
	c.beginPath();
	c.moveTo( -0.27, 0.50); // a
	c.quadraticCurveTo( -0.01, -0.10, -0.03, -0.26); // b
	c.quadraticCurveTo( -0.11, -0.30, -0.10, -0.36); // c
	c.quadraticCurveTo( -0.09, -0.35, -0.06, -0.32); // d
	c.lineTo( -0.07, -0.38); // e
	c.lineTo(-0.075, -0.38); // extra 0
	c.lineTo( -0.07, -0.33); // f
	c.lineTo( -0.04, -0.36); // g
	c.quadraticCurveTo( -0.02, -0.32, -0.01, -0.29); // h
	c.quadraticCurveTo(  0.00, -0.40, -0.04, -0.42); // i
	c.quadraticCurveTo( -0.01, -0.41, -0.02, -0.40); // j
	c.lineTo( 0.04, -0.58); // k
	c.lineTo( 0.12, -0.58); // l
	c.quadraticCurveTo(  0.05, -0.50,  0.05, -0.45); // m
	c.quadraticCurveTo(  0.08, -0.48,  0.08, -0.49); // n
	c.quadraticCurveTo(  0.12, -0.47,  0.06, -0.44); // o
	c.quadraticCurveTo(  0.03, -0.32,  0.01, -0.20); // p
	c.quadraticCurveTo(  0.06, -0.25,  0.10, -0.30); // extra 1 
	c.quadraticCurveTo(  0.10, -0.40,  0.14, -0.56); // q
	c.quadraticCurveTo(  0.14, -0.45,  0.15, -0.40); // r
	c.quadraticCurveTo(  0.18, -0.52,  0.23, -0.58); // s
	c.lineTo( 0.31, -0.58); // t
	c.lineTo( 0.23, -0.50); // u
	c.quadraticCurveTo(  0.18, -0.35,  0.07, -0.10); // v
	c.quadraticCurveTo(  0.13, -0.13,  0.15, -0.19); // w
	c.lineTo( 0.14, -0.15); // x
	c.quadraticCurveTo(  0.20, -0.21,  0.21, -0.30); // y
	c.lineTo( 0.23, -0.30); // z
	c.quadraticCurveTo(  0.21, -0.25,  0.22, -0.22); // aa
	c.quadraticCurveTo(  0.26, -0.29,  0.28, -0.30); // ab
	c.quadraticCurveTo(  0.25, -0.25,  0.20, -0.16); // ac
	c.lineTo( 0.23, -0.15); // ad
	c.quadraticCurveTo(  0.17, -0.19,  0.06, -0.04); // ae
	c.quadraticCurveTo(  0.08, -0.02,  0.10, -0.03); // af
	c.quadraticCurveTo(  0.09, -0.01,  0.08, -0.02); // ag
	c.quadraticCurveTo(  0.08, -0.01,  0.07,  0.00); // ah
	c.quadraticCurveTo(  0.04, -0.03,  0.01,  0.00); // ai
	c.quadraticCurveTo(  0.00,  0.06, -0.06,  0.20); // aj
	c.quadraticCurveTo(  0.06,  0.10,  0.06,  0.06); // extra 2
	c.quadraticCurveTo(  0.13, -0.07,  0.19, -0.09); // ak
	c.quadraticCurveTo(  0.16, -0.07,  0.12,  0.00); // al
	c.quadraticCurveTo( 0.165, -0.02,  0.17, -0.03); // am
	c.quadraticCurveTo(  0.15,  0.01,  0.10,  0.05); // an
	c.quadraticCurveTo(  0.12,  0.10,  0.08,  0.13); // ao
	c.quadraticCurveTo(  0.11,  0.11,  0.13,  0.11); // ap
	c.lineTo( 0.12, 0.12); // aq
	c.lineTo( 0.10, 0.14); // ar
	c.quadraticCurveTo(  0.05,  0.14,  0.04,  0.22); // as
	c.quadraticCurveTo(  0.00,  0.23, -0.10,  0.30); // at
	c.quadraticCurveTo( -0.15,  0.31, -0.20,  0.50); // au
	c.lineTo( -0.27, 0.50); // a´
	c.closePath();
}
function ramo_6(c){
	c.beginPath();
	c.moveTo( -0.04,  0.45); // a
	c.quadraticCurveTo( -0.07,  0.30, -0.10,  0.20); // b
	c.quadraticCurveTo( -0.13,  0.00, -0.10, -0.21); // c
	c.quadraticCurveTo(  0.11, -0.25, -0.12, -0.30); // extra 1
	c.quadraticCurveTo( -0.12, -0.36, -0.13, -0.37); // d
	c.lineTo( -0.12, -0.36); // e
	c.quadraticCurveTo( -0.12, -0.42, -0.11, -0.49); // f
	c.lineTo( -0.10, -0.59); // g
	c.quadraticCurveTo( -0.10, -0.35, -0.02, -0.26); // h
	c.quadraticCurveTo( -0.07, -0.30, -0.07, -0.35); // i
	c.quadraticCurveTo( -0.08, -0.40, -0.06, -0.49); // j
	c.lineTo( -0.02, -0.49); // k
	c.lineTo( -0.02, -0.44); // l
	c.lineTo(  0.00, -0.49); // m
	c.lineTo(  0.04, -0.49); // n
	c.lineTo( -0.03, -0.40); // o
	c.lineTo( -0.01, -0.31); // p
	c.quadraticCurveTo(  0.05, -0.40,  0.09, -0.49); // q
	c.lineTo(  0.11, -0.49); // r
	c.quadraticCurveTo(  0.11, -0.46,  0.09, -0.45); // s
	c.lineTo(  0.08, -0.40); // t
	c.quadraticCurveTo(  0.19, -0.49,  0.20, -0.49); // u
	c.lineTo(  0.24, -0.49); // v
	c.quadraticCurveTo(  0.19, -0.45,  0.18, -0.43); // w
	c.quadraticCurveTo(  0.21, -0.42,  0.30, -0.48); // x
	c.quadraticCurveTo(  0.20, -0.39,  0.12, -0.40); // y
	c.lineTo(  0.08, -0.39); // z
	c.quadraticCurveTo(  0.05, -0.35,  0.02, -0.32); // aa
	c.lineTo(  0.01, -0.30); // ab
	c.lineTo(  0.07, -0.32); // ac
	c.quadraticCurveTo(  0.03, -0.28,  0.00, -0.27); // ad
	c.quadraticCurveTo( -0.04, -0.16, -0.05, -0.02); // ae
	c.lineTo(  0.01,  0.06); // af
	c.quadraticCurveTo(  0.01, -0.09,  0.00, -0.12); // ag
	c.lineTo(  0.04, -0.14); // ah
	c.lineTo(  0.04, -0.09); // ai
	c.lineTo(  0.12, -0.15); // aj
	c.quadraticCurveTo(  0.13, -0.20,  0.15, -0.25); // ak
	c.quadraticCurveTo(  0.18, -0.37,  0.20, -0.38); // al
	c.quadraticCurveTo(  0.19, -0.25,  0.18,  0.18); // am
	c.quadraticCurveTo(  0.20, -0.19,  0.22, -0.22); // an
	c.lineTo(  0.23, -0.21); // ao
	c.quadraticCurveTo(  0.21, -0.19,  0.14, -0.14); // ap
	c.lineTo(  0.15, -0.13); // aq
	c.quadraticCurveTo(  0.12, -0.09,  0.24, -0.12); // ar
	c.quadraticCurveTo(  0.20, -0.08,  0.06, -0.05); // as
	c.lineTo( -0.04, 0.08); // at 
	c.lineTo(  0.01, 0.20); // au
	c.quadraticCurveTo(  0.06,  0.14,  0.12,  0.10); // av
	c.quadraticCurveTo(  0.15,  0.05,  0.20,  0.00); // aw 
	c.lineTo(  0.22,  0.01); // ax
	c.quadraticCurveTo(  0.20,  0.05,  0.12,  0.11); // ay
	c.lineTo( 0.13, 0.10); // az
	c.lineTo( 0.21, 0.09); // ba
	c.lineTo( 0.22, 0.11); // bb
	c.quadraticCurveTo(  0.15,  0.16,  0.10,  0.19); // bc
	c.quadraticCurveTo(  0.04,  0.23,  0.12,  0.45); // bd
	c.lineTo( -0.04,  0.45); // a´
	c.closePath();
}
// ################################
// 			  RAPARIGA
function rapariga(cta, px, py, sx, sy, color){
	
	// cabeca_rapariga
	enter(cta, px+10   , py+10, sx+45, sy+46, 0, color);
	cabeca_rapariga(cta);
	leave(cta);
	
	// rabo cavalo
	enter(cta, px     , py+4   , sx+50, sy+50, 0, color);
	rabo_cavalo(cta);
	leave(cta);

	// cabelitos
	enter(cta, px+10   , py+9  , sx+50, sy+50, 0 , color);
	cabelitos(cta);
	leave(cta);
	
	// pescoco
	enter(cta, px+64  , py+80,  sx+38, sy+52, 0 , color);
	pescoco_rapariga(cta);
	leave(cta);

	// corpo_rapariga
	enter(cta, px+22  , py+88, sx+115, sy+109, 0 , color);
	corpo_rapariga(cta);
	leave(cta);

	// braço direito
	enter(cta, px+31  , py+144,  sx+17, sy+14, 0 , color);
	braco_direito(cta);
	leave(cta);

	// braço esquerdo
	enter(cta, px+122 , py+159,  sx+63, sy+66, Math.PI*(15/180), color);
	braco_esquerdo(cta);
	leave(cta);

	// mao
	enter(cta, px+164 , py+162,   sx+3,  sy+9, Math.PI*(10/180), color);
	mao(cta);
	leave(cta);

	// perna direita
	enter(cta, px+50  , py+220, sx+130, sy+140, 0, color);
	perna_direita(cta);
	leave(cta);

	// perna esquerda
	enter(cta, px+80  , py+210, sx+104, sy+128, 0, color);
	perna_esquerda(cta);
	leave(cta);

	// cintura
	enter(cta, px+53  , py+190,  sx+60, sy+50, 0, color);
	cintura(cta);
	leave(cta);

	// flor
	enter(cta, px+172, py+150, sx+3   , sy+3, 0, color);
	flor(cta);
	leave(cta);
}
function cabeca_rapariga(ci){
	ci.beginPath();
	ci.moveTo( 0.70, 0.98);
	ci.quadraticCurveTo( 0.45, 1.00, 0.40, 0.95); // parte de baixo
	ci.lineTo( 0.40, 0.92);
	ci.quadraticCurveTo( 0.15, 0.49, 0.50, 0.32); // parte de tras da cabeca
	ci.quadraticCurveTo( 0.60, 0.35, 0.70, 0.27); // parte de cima curva p/ baixo 
	ci.quadraticCurveTo( 0.90, 0.20, 0.95, 0.40); // franja exterior
	ci.quadraticCurveTo( 0.90, 0.30, 0.90, 0.35); // franja interior
	ci.quadraticCurveTo( 0.93, 0.45, 0.94, 0.50); // testa
	ci.quadraticCurveTo( 0.98, 0.50, 0.97, 0.58); // olho
	ci.quadraticCurveTo( 1.10, 0.55, 1.02, 0.68); // nariz
	ci.quadraticCurveTo( 1.08, 0.66, 1.05, 0.72); // labio superior
	ci.quadraticCurveTo( 1.10, 0.70, 1.08, 0.78); // labio inferior
	ci.quadraticCurveTo( 1.20, 0.88, 0.70, 0.98); // queixo
	ci.lineTo( 0.70, 0.98); 
	ci.closePath();
}
function rabo_cavalo(c){
	c.beginPath();
	c.moveTo( 0.68, 0.98);
	c.lineTo( 0.66, 1.02);
	c.lineTo( 0.63, 1.02);
	c.lineTo( 0.60, 1.04);
	c.lineTo( 0.56, 1.05);
	c.lineTo( 0.61, 1.00);
	c.lineTo( 0.61, 0.98);
	c.quadraticCurveTo( 0.61, 0.94, 0.57, 0.97);
	c.lineTo( 0.54, 1.02);
	c.lineTo( 0.50, 1.04);
	c.lineTo( 0.48, 1.04);
	c.lineTo( 0.53, 0.96);
	c.lineTo( 0.53, 0.90);
	c.lineTo( 0.68, 0.90);
	c.lineTo( 0.68, 0.98);
	c.closePath();
}
function cabelitos(c){
	c.beginPath();
	c.rect( 0.54 , 0.30, 0.07, 0.03);
	c.moveTo( 0.58, 0.30);
	c.quadraticCurveTo( 0.60, 0.26, 0.68, 0.24);
	c.quadraticCurveTo( 0.58, 0.26, 0.58, 0.30);
	c.moveTo( 0.57, 0.30);
	c.quadraticCurveTo( 0.58, 0.20, 0.59, 0.22);
	c.quadraticCurveTo( 0.56, 0.25, 0.57, 0.30);
	c.moveTo( 0.55, 0.30);
	c.quadraticCurveTo( 0.54, 0.26, 0.56, 0.24);
	c.quadraticCurveTo( 0.52, 0.26, 0.55, 0.30);
	c.moveTo( 0.54, 0.32);
	c.quadraticCurveTo( 0.53, 0.29, 0.52, 0.28);
	c.quadraticCurveTo( 0.50, 0.29, 0.54, 0.32);
	c.closePath();
}
function pescoco_rapariga(c){
	c.beginPath();
	c.rect( 0, 0.2, 0.25, 0.1);
	c.moveTo(    0, 0.2);
	c.lineTo(    0,   0);
	c.lineTo( 0.35,   0);
	c.quadraticCurveTo( 0.30, 0, 0.25, 0.20);
	c.closePath();
}
function corpo_rapariga(c){
	c.beginPath();
	c.moveTo(0.21, 0.06); // a 
	c.quadraticCurveTo( 0.31, 0.01, 0.41, 0.02); // b
	c.quadraticCurveTo( 0.48, 0.03, 0.52, 0.025); // c
	c.lineTo( 0.50, 0.07); // d
	c.lineTo( 0.56, 0.07); // e
	c.quadraticCurveTo( 0.70, 0.02, 0.67, 0.26); // f
	c.quadraticCurveTo( 0.64, 0.28, 0.66, 0.32); // g
	c.lineTo( 0.65, 0.34); // h
	c.lineTo( 0.67, 0.42); // i
	c.lineTo( 0.69, 0.41); // j
	c.quadraticCurveTo( 0.69, 0.49, 0.70, 0.52); // k
	c.lineTo( 0.68, 0.52); // l
	c.quadraticCurveTo( 0.74, 0.7, 0.73, 0.85); // m
	c.lineTo( 0.28, 0.85); // n
	c.quadraticCurveTo( 0.30, 0.5, 0.22, 0.4); // o
	c.lineTo( 0.25, 0.38); // p
	c.lineTo( 0.22, 0.32); // q
	c.lineTo( 0.18, 0.35); // r
	c.lineTo( 0.17, 0.41); // s
	c.quadraticCurveTo( 0.13, 0.40, 0.07, 0.39); // t
	c.lineTo( 0.078, 0.32); // u
	c.quadraticCurveTo( 0.03, 0.2, 0.12, 0.13); // v
	c.quadraticCurveTo( 0.18, 0.15, 0.22, 0.1); // w
	c.lineTo(0.21, 0.06); // a´
	c.closePath();
}
function braco_direito(c){
	c.beginPath();
	c.moveTo( 0.05, 0);
	c.quadraticCurveTo( 0, 0.2, 0, 0.5); // b
	c.quadraticCurveTo( 0.3, 0.65, 0.7, 0.52); // c
	c.lineTo( 0.68, 0.35); // d
	c.quadraticCurveTo( 0.36, 0.35, 0.28, 0.32); // e
	c.lineTo( 0.3, 0); // f
	c.lineTo( 0.05, 0); // a´
	c.closePath();
}
function braco_esquerdo(c){
	c.beginPath();
	c.lineTo(   0, 0.12);
	c.quadraticCurveTo( 0.25, 0.15, 0.5, 0.10 );
	c.lineTo( 0.5, 0.03);
	c.lineTo(   0,    0);
	c.closePath();
}
function mao(c){
	c.beginPath();
	c.moveTo( 0.1, 0.27); // a
	c.lineTo( 0.1, 0.47); // b
	c.quadraticCurveTo( 0.25, 0.53, 0.50, 0.52); // c
	c.quadraticCurveTo( 0.57, 0.48, 0.38, 0.48); // d
	c.arc( 0.35, 0.38, 0.11, Math.PI*(90/180), Math.PI*( 270/180), true ); // e
	c.lineTo( 0.3, 0.28); // f
	c.quadraticCurveTo( 0.28, 0.28, 0.34, 0.24); // g
	c.quadraticCurveTo( 0.40, 0.24, 0.43, 0.22); // h
	c.quadraticCurveTo( 0.42, 0.16, 0.41, 0.18); // i
	c.quadraticCurveTo( 0.30, 0.19, 0.28, 0.19); // j
	c.quadraticCurveTo( 0.16, 0.25, 0.10, 0.27); // k
	c.closePath();
}
function cintura(c){
	c.beginPath();
	c.moveTo( 0.15, 0.1);
	c.quadraticCurveTo( 0.1, 0.12, 0.13, 0.26);
	c.quadraticCurveTo( 0.07, 0.35, 0.16, 0.4);
	c.lineTo( 0.83, 0.4);
	c.quadraticCurveTo( 0.83, 0.4, 0.82, 0.1);
	c.lineTo( 0.15, 0.1);
	
	c.closePath();
}
function perna_direita(c){
	c.beginPath();
	c.moveTo( 0.1, 0); // a
	c.quadraticCurveTo( 0.05, 0.15, 0.09, 0.3); //b
	c.lineTo( 0.08, 0.3); // c
	c.quadraticCurveTo( 0.06, 0.34, 0.08, 0.38); // d
	c.quadraticCurveTo( 0.08, 0.46, 0.12, 0.43); // e
	c.quadraticCurveTo( 0.1, 0.58, 0.18, 0.8);   // f
	c.quadraticCurveTo( 0.17, 0.85, 0.18, 0.90); // g
	c.lineTo( 0.3, 0.90); // h
	c.quadraticCurveTo( 0.40, 0.9, 0.38, 0.88);  // i
	c.quadraticCurveTo( 0.35, 0.84, 0.27, 0.84); // j
	c.quadraticCurveTo( 0.24, 0.6, 0.26, 0.42);  // k
	c.lineTo( 0.28, 0.42); // l
	c.quadraticCurveTo( 0.3, 0.39, 0.28, 0.30); // m
	c.lineTo( 0.27, 0.3); // n
	c.quadraticCurveTo( 0.28, 0.15, 0.28, 0); // o
	c.lineTo( 0.1, 0);
	c.closePath();
}
function perna_esquerda(c){
	c.beginPath();
	c.moveTo( 0.1, 0.08); // a
	c.lineTo( 0.1, 0.12);  // b
	c.quadraticCurveTo( 0.15, 0.12, 0.15, 0.20); // c 
	c.quadraticCurveTo( 0.14, 0.24, 0.18, 0.31); // d
	c.quadraticCurveTo( 0.19, 0.38, 0.20, 0.39); // e
	c.lineTo( 0.20, 0.40); // f
	c.lineTo( 0.18, 0.40); // g
	c.quadraticCurveTo( 0.20, 0.48, 0.21, 0.52); // h
	c.lineTo( 0.25, 0.518); // i
	c.quadraticCurveTo( 0.26, 0.68, 0.35, 0.80); // j
	c.quadraticCurveTo( 0.40, 0.90, 0.40, 0.95); // k
	c.quadraticCurveTo( 0.40, 1.00, 0.50, 0.98); // l
	c.quadraticCurveTo( 0.62, 0.96, 0.68, 0.92); // m
	c.arc( 0.66, 0.91, 0.02, Math.PI*(45/180), Math.PI*(260/180), true); // n
	c.quadraticCurveTo( 0.48, 0.94, 0.48, 0.82); // o
	c.lineTo( 0.41, 0.52); // p
	c.lineTo( 0.45, 0.50); // q
	c.lineTo( 0.45, 0.38)  // r
	c.lineTo( 0.43, 0.38); // s
	c.quadraticCurveTo( 0.43, 0.30, 0.42, 0.25); // t
	c.quadraticCurveTo( 0.41, 0.13, 0.40, 0.09); // u
	c.lineTo( 0.38, 0.085); // v
	c.lineTo( 0.36, 0.08);  // x
	c.lineTo(    1, 0.08);  // a`
	c.closePath();
}
function flor(c){
	c.beginPath();
	c.moveTo(0.21, 0.25); // A
	c.quadraticCurveTo( 0.21, 0.23, 0.22, 0.19);// B
	c.quadraticCurveTo( 0.20, 0.10, 0.28, 0.13) // C
	c.quadraticCurveTo( 0.35, 0.10, 0.34, 0.26) // D
	c.quadraticCurveTo( 0.40, 0.21, 0.40, 0.20) // E
	c.quadraticCurveTo( 0.45, 0.30, 0.37, 0.36) // F
	c.quadraticCurveTo( 0.42, 0.37, 0.45, 0.35) // G
	c.quadraticCurveTo( 0.47, 0.40, 0.38, 0.41) // H
	c.quadraticCurveTo( 0.37, 0.45, 0.36, 0.50) // I
	c.quadraticCurveTo( 0.30, 0.50, 0.25, 0.40) // J
	c.quadraticCurveTo( 0.20, 0.45, 0.16, 0.47) // K
	c.quadraticCurveTo( 0.10, 0.40, 0.19, 0.36) // L
	c.quadraticCurveTo( 0.11, 0.32, 0.10, 0.27) // M
	c.quadraticCurveTo( 0.13, 0.20, 0.21, 0.25) // A´
	c.moveTo( 0.25, 0.33); // N
	c.lineTo( 0.30, 0.33); // O
	c.quadraticCurveTo( 0.20, 0.55, 0.20, 0.80); // P
	c.lineTo( 0.15, 0.80); // Q
	c.quadraticCurveTo( 0.13, 0.55, 0.25, 0.33) // N´
	c.closePath();
}
// ################################
//			FOLHAS
function colocar_folhas(c, color){
	enter(c, 280, 360, 13, 15, 201, color);
	folha(c);
	leave(c);

	enter(c, 440, 520, 14, 17, Math.PI*( 350/180), color);
	folha(c);
	leave(c);

	enter(c, 460, 525, 13, 17, Math.PI*( 90/180), color);
	folha(c);
	leave(c);

	enter(c, 420, 530, 12, 16, Math.PI*( 90/180), color);
	folha(c);
	leave(c);

	enter(c, 448, 550, 14, 16, Math.PI*( 335/180), color);
	folha(c);
	leave(c);

	enter(c, 460, 570, 15, 17, Math.PI*( 130/180), color);
	folha(c);
	leave(c);
}
function folha(c){
	c.beginPath();
	c.arc( 0, 0, 0.4, 0, Math.PI);
	c.quadraticCurveTo( -0.40, -0.50, 0.20, -1.00);
	c.quadraticCurveTo(  0.20,  -0.85, 0.26, -0.65);
	c.quadraticCurveTo(  0.43, -0.40, 0.40,  0.00);
	c.closePath();
}
// ################################
// 			   CORVO
function corvo(ct, px, py, sx, sy, color){
	// bico
	enter(ct, px+0, py+7, sx, sy, Math.PI*(350/180), color);// translate, scale, rotation, fillStyle
	bico(ct);
	leave(ct);
	// cabeca_corvo
	enter(ct, px+17, py+5, sx, sy, 0, color);
	cabeca_corvo(ct);
	leave(ct);
	// corpo_corvo
	enter(ct, px+35, py+22, sx+13, sy+50, Math.PI/4, color);
	corpo_corvo(ct);
	leave(ct);
	// pescoco_corvo
	enter(ct, px+21,  py, sx+60, sy+20, Math.PI/4, color);
	pescoco_corvo(ct);
	leave(ct);
	// perna_corvo
	enter(ct, px+29,  py+31, sx, sy, Math.PI*(10/18), color);
	perna_corvo(ct);
	leave(ct);
	// cauda()
	enter(ct, px+45,  py+26, sx+90, sy+90, Math.PI*(24/180), color);
	cauda(ct);
	leave(ct);
}
function bico(c){
	c.beginPath();
	c.moveTo(0 , 0);
	c.lineTo(1 ,-0.3);
	c.lineTo(1 , 0.3);
	c.lineTo(0 , 0);
	c.closePath();
}
function cabeca_corvo(c){
	c.beginPath();
	c.ellipse( 0, 0, 1, 0.68, 0, 0, 2*Math.PI);
	c.closePath();
}
function corpo_corvo(c){
	c.beginPath();
	c.ellipse( 0, 0, 0.9, 0.2 , 0, 0, 2*Math.PI);
	c.closePath();
}
function pescoco_corvo(c){
	c.beginPath();
	c.rect(0, 0, 0.4, 0.3);
	c.closePath();
}
function perna_corvo(c){
	c.beginPath();
	c.rect(0, 0, 1, 0.1);
	c.closePath();
}
function cauda(c){
	c.beginPath();
	c.moveTo( 0, 0);
	c.arcTo(  0.5, 0, 0.52,   0.1, 0.1);
	c.arcTo(0.52, 0.1, 0.4, 0.2, 0.1); 
	c.arcTo(  0.4, 0.2, 0.3,   0.3, 0.1); 
	c.lineTo( 0.3, 0.2);
	c.lineTo(   0, 0.1);
	c.lineTo(   0,   0);
	c.closePath();
}
// ################################
//           ESCRITORA
function escritora(c, x, y, color){
	enter(c, x,    y, 1, 1.35, 0, color);
	letra(c, "H");
	leave(c);
	enter(c, x+25, y, 1, 1.35, 0, color);
	letra(c, "A");
	leave(c);
	enter(c, x+50, y, 1, 1.35, 0, color);
	letra(c, "R");
	leave(c);
	enter(c, x+75, y, 1, 1.35, 0, color);
	letra(c, "P");
	leave(c);
	enter(c, x+100, y, 1, 1.35, 0, color);
	letra(c, "E");
	leave(c);
	enter(c, x+125, y, 1, 1.35, 0, color);
	letra(c, "R");
	leave(c);
	enter(c, x+170, y, 1, 1.35, 0, color);
	letra(c, "L");
	leave(c);
	enter(c, x+195, y, 1, 1.35, 0, color);
	letra(c, "E");
	leave(c);
	enter(c, x+220, y, 1, 1.35, 0, color);
	letra(c, "E");
	leave(c);
}
function letra(c, letra){
	c.beginPath();
	c.font = '600 57px Cardenio Modern'; 
	c.fillText(letra, 0, 0);
	c.closePath();
}