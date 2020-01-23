#include "queue.h"

Pcb createProcess(int pid, int arrive, int *instructions, int state, int next_state, int size) 
{
     Pcb *proc = malloc(sizeof(Pcb));
     proc->pc    =   0;
     proc->pid   = pid;
     proc->state = state;
     proc->next_state = next_state;
     proc->arrive_time   = arrive;
     proc->local_prog[0] = -1;
     proc->local_prog[1] = -1;
     proc->instructions = malloc( sizeof(int) * size + 1);
     int i;
     for ( i = 0; i < size; i++)
          proc->instructions[i] = instructions[i];
     proc->instructions[i] = -1;
     return *proc;
}
Pcb createProc()
{
     Pcb *proc = malloc(sizeof(Pcb));
     proc->pc    = -1;
     proc->pid   = -1;
     proc->state = -1;
     proc->next_state = -1;
     proc->arrive_time   = -1;
     proc->local_prog[0] = -1;
     proc->local_prog[1] = -1;
     proc->instructions  = NULL;
     return *proc;
}
Queue createQueue(short size) 
{
     Queue *queue = malloc(sizeof(Queue));

     queue->structure = malloc(sizeof(Queue) * size );

     for (int i = 0; i < size; i++)
          queue->structure[i] = createProc();
     
     queue->size = size;
     queue->head = 0;
     queue->tail = 0;

     return *queue;
}
bool emptyQueue(Queue *queue)
{
     int  size = queue->size;
     
     for (int i = 0; i < size; i++)
     {
          int pid = queue->structure[i].pid;
          if (pid >= 0)
               return false;
     }
     return true;
}
bool fullQueue(Queue *queue)
{
     int  size = queue->size,
          count = 0, pid;

     for (int i = queue->head; i < queue->tail+1; i++)
     {
          pid = queue->structure[i].pid;
          if (pid != -1)
               count++;
     }
     return (count == size) ? true : false;
}
void enqueue(Queue *queue, Pcb proc) 
{
     queue->structure[queue->tail] = proc;

     queue->tail = (queue->tail + 1) % queue->size;
}
Pcb dequeue(Queue *queue) 
{
     Pcb proc = queue->structure[queue->head];

     // Colocar o conteudo do processo anterior a zero
     queue->structure[queue->head] = createProc();

     queue->head = (queue->head + 1) % queue->size;

     return proc;
}
Pcb *top_queue(Queue *queue)
{
     return &queue->structure[queue->head];
}