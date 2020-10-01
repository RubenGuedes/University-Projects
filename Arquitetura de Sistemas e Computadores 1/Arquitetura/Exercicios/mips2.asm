.data
.word 3,1,-2,0,3,-10,-1,3

.text

main:

lui $t0, 0x0100
ori $t0, $t0, 0x0000

ler:
lw, $t2, 0($t0),comparar
nop
addi, $t0, $t0, 4
addi, $t2, $t2, -1
if $t0 != $zero, ler
else end
nop

comparar:
or $t3, $zero, $t2
srl $t3, 31
sll, $t3, 31
if $t3 == 0x80000000, converte_e_guarda
nop
else $t3,ler
nop

converte_e_guarda:
xor $t2,0xffffffff
add $t2, 1
sw 

end:
