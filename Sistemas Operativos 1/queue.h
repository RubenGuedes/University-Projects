#include <stdio.h>
#include <malloc.h>
#include <stdbool.h>

typedef struct Pcb
{
     int  pc,    // Program Counter
          pid,   // Process ID
          state, // Estado Atual
          next_state,    // Estado Seguinte
          arrive_time,   // Tempo de chegada
          local_prog[2], // Onde o programa esta na memória principal
         *instructions;  // Array com as instruções
} Pcb;

typedef struct Queue 
{
     Pcb *structure;
     short head, tail, size;
} Queue;

Pcb createProc();
Pcb createProcess(int pid, int arrive, int *instructions,int state, int next_state, int size);
Queue createQueue( short size );

bool emptyQueue(Queue *queue);
bool fullQueue( Queue *queue);

void enqueue( Queue *queue, Pcb proc );
Pcb dequeue(  Queue *queue);
Pcb *top_queue(Queue *queue);