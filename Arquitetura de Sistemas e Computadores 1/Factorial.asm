#factorial
#fact(3) = 3 * fact(2) * fact(1)
#fact(2) = 2 * fact(1)
#fact(1) = 1 * fact(0)
#fact(0) = 1

li $a0, 5	# 001111 00000 00001 0000000000000000
                    # 001101 00004 00001 0000000000000101
jal fact           # 000011 00000100000000000000010010000111
nop               # 00000000000000000000000000000000
j END            # 000010 00000000010000000000000001001000
nop               # 00000000000000000000000000000000

fact:
addi $sp, $sp, -8  #001000 00029 00029 1111111111111000
sw $s0, 4($sp)     #101011 00029 00016 0000000000000100
sw $ra, 0($sp)     # 101011 00029 00031 0000000000000000
#vai sempre para aqui
beq $a0,$zero, factend 
li $v0, 1

move $s0, $a0
#guardar os endereços para depois voltar e saltar de volta
jal fact
add $a0, $a0, -1

#basicamente o que queremos fazer
mul $v0, $v0, $s0


factend:

#carregar os endereçõs 
lw $ra, 4($sp)
lw $s0, 0($sp)
jr $ra
addi $sp, $sp, 8

END:
