#factorial
#fact(3) = 3 * fact(2) * fact(1)
#fact(2) = 2 * fact(1)
#fact(1) = 1 * fact(0)
#fact(0) = 1

li $a0, 5
jal fact
nop
j END
nop

fact:

addi $sp, $sp, -8
sw $s0, 4($sp)
sw $ra, 0($sp)

#vai sempre para aqui
beq $a0,$zero, factend
li $v0, 1

move $s0, $a0
#guardar os enderecos para depois voltar e saltar de volta
jal fact
add $a0, $a0, -1

#basicamente o que queremos fazer
mul $v0, $v0, $s0


factend:

#carregar os endere��s 
lw $ra, 0($sp)
lw $s0, 4($sp)
jr $ra
addi $sp, $sp, 8

END:
