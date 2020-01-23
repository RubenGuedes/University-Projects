#!/bin/bash

DIAGRAM="$1"

if [ $DIAGRAM = "-h" ]; then
    echo "Arguments:"
    echo "0 : To collect data"
    echo "1 : To show diagram (WARNING-> NEEDS SUDO PERMISSIONS)"
else
    if [ "0" = $DIAGRAM ]; then
        # Ciclo que vai calcular e guardar as posições 
        # onde os caracteres estão corretos e errados
        for i in {1..100}
        do
            STRING="unknown-"
            LEN=8
            echo $STRING | ./../canal-linux64 $LEN | ./modelo_canal.py $DIAGRAM $STRING
        done
    else
        sudo ./modelo_canal.py $DIAGRAM
    fi
fi