#!/bin/bash

# 0 = Collect Data; 1 = Show Diagram
DIAGRAM="$1"

if [ $DIAGRAM = "-h" ]; then
    echo "Arguments:"
    echo "0 : To collect data"
    echo "1 : To show diagram (WARNING-> NEEDS SUDO PERMISSIONS)"
else
    # Collect values
    if [ 0 = $DIAGRAM ]; then
        for val in {20..100..20}
        do
            ./../fonte-linux64 $val | ./modelo_fonte.py $DIAGRAM
        done
    # Show Diagram
    else
        sudo ./modelo_fonte.py $DIAGRAM
    fi
fi