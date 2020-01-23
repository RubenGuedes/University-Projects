#include <math.h>
#include <string.h>
#include "linked_list.h"

// CONFIGURAÇÕES INICIAIS
#define QUANTUM  4
#define MEM_SIZE 300

// TAMANHO DO BUFFER DOS ESTADOS
#define BUFFER 20
#define RUN_SIZE 1
#define ARRAY_STATES 11

// ESTADOS
#define NEW     0
#define READY   1
#define RUN     2
#define BLOCKED 3
#define EXIT    4

void pass_content(int *instructions, int *positions, bool in_mem, int *mem);

/**
 * MEMÓRIA GLOBAL
 */
int memoria[MEM_SIZE];


void print_memory(int *mem, int len)
{
     int val;
     for (int i = 0; i < len; i++)
     {
          printf(" %2d ", mem[i]);
          val = i + 1;
          if (val%30 == 0 )
               printf("\n");
     }
     printf("\n");
}
////////////////////////////////////////////////////////////////////
/**
 *   Funções para atualizar um processo
 */
void updateArriveTime(Pcb *proc, int add)
{
     proc->arrive_time += add;
}
void updateState(Pcb *proc, int state)
{
     proc->state = state;
}
void updateNextState(Pcb *proc, int state)
{
     proc->next_state = state;
}
void add1Arrive(Queue *state)
{
     for (int i = 0; i < state->size; i++)
     {
          if (state->structure[i].pid >= 0)
               updateArriveTime(&state->structure[i], 1);
     }
}
/**
 *   Funções para o fluxo dos processos
 */
// Verifica se o programa pode terminar
bool stop(Queue *q1,  Queue *q2,  Queue *q3,  Queue *q4, Queue *q5)
{
     if (emptyQueue(q1) && emptyQueue(q2) && emptyQueue(q3) && emptyQueue(q4) && emptyQueue(q5))
          return true;
     return false;
}
void remove_exit(Queue *s_exit, LinkedList *free_mem)
{
     bool empty_exit = emptyQueue(s_exit);

     if (!empty_exit)
     {
          Pcb proc = *top_queue(s_exit);

          // atualizar a Linked List
          remove_interval_linked_list(free_mem, proc.local_prog);
          join_fragments(free_mem);

          if (proc.arrive_time >= 1)
               dequeue(s_exit);
     }
}
void new_ready(Queue *s_new, Queue *s_ready)
{
     bool empty_new  = emptyQueue(s_new),
          full_ready = fullQueue(s_ready);
     
     if (!empty_new && !full_ready )
     {
          int  begin = s_new->head,
               end   = s_new->tail + 1;
          for (int i = begin; i < end; i++)
          {
               Pcb *proc = &s_new->structure[i];
               if ( proc->pid >= 0 && proc->arrive_time >= 1 )
               {
                    proc->state = READY;
                    proc->arrive_time = 0;
                    enqueue( s_ready, dequeue(s_new) );
               }
          }
     }
}
void ready_run(Queue *s_ready, Queue *s_run)
{
     bool empty_ready = emptyQueue(s_ready),
          full_run    = fullQueue(s_run);

     if ( !empty_ready && !full_run )
     {
          Pcb *proc = top_queue(s_ready);
          if (proc->pid >= 0 && proc->arrive_time >= 1)
          {
               proc->arrive_time = 0;
               proc->state = RUN;
               proc->next_state = -1;
               enqueue(s_run, dequeue(s_ready));
          }
     }
}
void run_blocked(Queue *s_run, Queue *s_blocked)
{
     bool empty_run  = emptyQueue(s_run),
          full_block = fullQueue(s_blocked);
     Pcb *proc = top_queue(s_run); 

     if ( !empty_run && !full_block && proc->next_state == BLOCKED)
     {
          proc->arrive_time = 0;
          updateState(proc, BLOCKED);
          updateNextState(proc, READY);
          enqueue(s_blocked, dequeue(s_run));
     }
}
void run_exit(Queue *s_run, Queue *s_exit)
{
     bool empty_run = emptyQueue(s_run);
     Pcb *proc = top_queue(s_run);

     if ( !empty_run && proc->next_state == EXIT  )
     {
          proc->arrive_time = 0;
          updateState(proc, EXIT);
          updateNextState(proc, EXIT);
          enqueue(s_exit, dequeue(s_run));
     }
}
void run_ready(Queue *s_run, Queue *s_ready)
{
     bool empty_run  = emptyQueue(s_run),
          full_ready = fullQueue(s_ready);
     Pcb *proc = top_queue(s_run);
     int  /* next_state  = proc->next_state, */
          *arrive_time = &proc->arrive_time;
     if ( !empty_run && !full_ready && *arrive_time == QUANTUM /* && next_state == READY  */ )
     {
          *arrive_time = 0;
          updateState(proc, READY);
          updateNextState(proc, RUN);
          enqueue(s_ready, dequeue(s_run));
     }
     
}
void blocked_ready(Queue *s_blocked, Queue *s_ready)
{
     bool empty_blocked = emptyQueue(s_blocked),
          full_ready    = fullQueue(s_ready);
     
     if ( !empty_blocked && !full_ready )
     {
          int  begin = s_blocked->head,
               end   = s_blocked->tail + 1;
          for (int i = begin; i < end; i++)
          {
               Pcb *proc = &s_blocked->structure[i];
               if (proc->arrive_time == 3)
               {
                    proc->arrive_time = 0;
                    updateState(proc, READY);
                    updateNextState(proc, RUN);
                    enqueue(s_ready, dequeue(s_blocked));
               }
               else 
                    break;
          }
          
     }
     
}
void new_run(Queue *s_new, Queue *s_ready, Queue *s_run)
{
     bool empty_new   = emptyQueue(s_new),
          empty_ready = emptyQueue(s_ready),
          full_run    = fullQueue( s_run);

     Pcb *proc = top_queue(s_new);

     if (proc->arrive_time == 1 && proc->pid >= 0 && !empty_new && empty_ready && !full_run)
     {
          proc->arrive_time = 0;
          proc->state = RUN;
          proc->next_state = -1;
          enqueue(s_run, dequeue(s_new));
     }
}
/**
 *   Funções para trabalhar com arrays
 */
void arrset_val(int size, int *arr, int val)
{
     for (int i = 0; i < size; i++)
          arr[i] = val;
}
int string_size(char *line)
{
     int  index = 0;
     while ( line[index] != ('\0' && '\n') )
          index++;
     return index-1;
}
int arr_int_size(int *arr)
{
     int  i = 0;
     while (arr[i] >= 0 )
          i++;
     return i;
}
int *subarr_int(int begin, int end, int *arr)
{
     int  position = 0,
          result   = end - begin,
         *arr_splited = (int*) malloc( sizeof(int) * result + 1);

     for (int i = begin; i < end; i++)
          arr_splited[position++] = arr[i];

     arr_splited[position] = -1;
     return arr_splited;
}
int *split(int tam, char linha[])
{
     int *arr_splited = malloc( sizeof(int) * tam + 1), 
          pos = 0, var_aux = 0, 
          multiplicador = 1,
          index;
     
     if ( (linha[tam-2] == '\r' && linha[tam-1] == '\n') || linha[tam-1] == '\n')
     {
          tam = tam - 1;
          linha[tam] = '\0';
     }

     for(index = 0 ; index < tam; index++)
     {
          char valor = linha[index];
          
          if (valor == '\n' || valor == '\0' || valor == '\r')
          {
               arr_splited[pos++] = var_aux;
               multiplicador = 1;
               var_aux = 0;
               break;
          }
          else if ( valor != ' ' ) 
          {
               var_aux = (var_aux * multiplicador) + (valor - '0');
               if (multiplicador == 1) 
                    multiplicador *= 10;               
          }
          else
          {
               arr_splited[pos++] = var_aux;
               multiplicador = 1;
               var_aux = 0;
          }
     }
     arr_splited[pos] = -1;
     return arr_splited;
}
void mem_set(int begin, int end, int *mem, int val)
{
     for (int i = begin; i < end; i++)
          mem[i] = val; 
}
// REMOVER INTRUÇÕES NA MEMÓRIA
void remove_inst(Pcb *proc, int *mem)
{
     int  begin = proc->local_prog[0],
          end   = proc->local_prog[1];
     
     mem_set(begin, end, mem, -1);
}
////////////////////////////////////////////////////////////////////
/**
 *   Increase execution time
 */
void increase_exe_time(unsigned short *exe_time)
{
     *exe_time = (*exe_time + 1)  % 65535;
}
/**
 *   Int to State
 *   -> Converte um inteiro no respetivo estado
 */
char *int_state(int val)
{
     char *name;
     switch (val)
     {
          case 0: name = "new";
               break;
          case 1: name = "ready";
               break;
          case 2: name = "run";
               break;
          case 3: name = "blocked";
               break;
          case 4: name = "exit";
               break;
          default: name = "";
               break;
     }
     return name;
}
////////////////////////////////////////////////////
/**
 *   Função para trabalhar o ficheiro de texto
 * e extrair/criar os processos
 */
void workFile(char *filename, Pcb processes[], int size_buffer, int *last_id)
{
     FILE *ficheiro = fopen(filename, "r");
     int read;  
     size_t len;
     char *linha = malloc(sizeof(char*) * len);

     if(ficheiro == NULL)
          printf("Ficheiro não encontrado!\n");
     else
     {
          int processID = 0;

          // Cada linha vai ser partida pelos espaços
          while( (read =getline(&linha, &len, ficheiro)) != -1 ) 
          {
               int  len_line = string_size(linha),
                   
                    // Array com os números separados 
                    *line_split = split(len_line, linha),
                    line_split_size = arr_int_size(line_split),
                   
                    // tempo de chegada
                    arrive_time = line_split[0],
                   
                    // Array com as instruções
                   *sub_line = subarr_int(1, line_split_size, line_split);
               
               // Criar um novo processo
               Pcb new_process;
               new_process = createProcess(processID, arrive_time, sub_line, NEW, READY, line_split_size-1);
               
               // Adicionar o novo processo ao estado new
               processes[processID] = new_process;

               // Mudar o nome do process ID
               processID++;
          }
          
          *last_id = processID;

          for(int i = processID; i < size_buffer; i++)
               processes[i] = createProc();
     }
     fclose(ficheiro);
}
// Passa as variáveis
void pass_variables(int *mem, int from_var0, int from_var1, int to_var0)
{
     int var = to_var0;
     unsigned short distance = from_var1 - from_var0;

     for (int i = 0; i < distance; i++)
          mem[var++] = mem[i];
}
////////////////////////////////////////////////////////////////////
/**
 *   Instruções do Enunciado
 */
void set_x(int *prog_counter, int *var1, int *var2)
{
     *var1 = *var2;
     *prog_counter += 3;
}
void set_n(int *prog_counter, int *var1, int *var2)
{
     *var1 = *var2;
     *prog_counter += 3;
}
void inc_x(int *prog_counter, int *var1)
{
     *var1 = *var1 + 1;
     *prog_counter += 3;
}
void dec_x(int *prog_counter, int *var1)
{
     *var1 = *var1 - 1;
     *prog_counter += 3;
}
void back_n(int *prog_counter, int *var1, int min_limit, Pcb *proc, int *mem)
{
     int aux = *prog_counter - (*var1 * 3); 
     if ( aux > min_limit)
     {
          *prog_counter -= (*var1 * 3);
     }
     else
     {
          printf("MEMORY ACCESS VIOLATION");
          *prog_counter += 3;
          updateNextState(proc, EXIT);
          // Apagar conteúdo da mem
          remove_inst(proc, mem);
     }
}
void forw_n(int *prog_counter, int *var1, int max_limit, Pcb *proc, int *mem)
{
     int aux = *prog_counter + (*var1 * 3);
     if ( aux < max_limit)
     {
          *prog_counter += (*var1 * 3);
     }
     else
     {
          printf("MEMORY ACCESS VIOLATION");
          *prog_counter += 3;
          updateNextState(proc, EXIT);
          // Apagar conteúdo da mem
          remove_inst(proc, mem);
     }
}
void if_x_n(int *prog_counter, int *x, int *n)
{
     if (*x == 0)
          *prog_counter = *prog_counter + (*n * 3);
     else
          *prog_counter += 3;
}
short fork_x(int *prog_counter, int *mem, int *last_id, Queue *state_ready, Pcb *proc, LinkedList * free_mem) 
{ 
     *prog_counter += 3;
     if (!fullQueue(state_ready))
     {
          bool  can_add;
          int  *arr_instr  = subarr_int(proc->local_prog[0]+10, proc->local_prog[1], mem),
               *arr_pos, array_aux[3];
          short instr_size = arr_int_size(arr_instr),
                instr_size_plus_variables = instr_size + 10;

          // arr_pos[0 e 1] posições inicial e final disponiveis em memória, respetivamente
          arr_pos = can_insert_process( free_mem,  instr_size_plus_variables );
          array_aux[0] = arr_pos[0];
          array_aux[1] = arr_pos[1];
          array_aux[2] = arr_pos[2];

          // Se tiver espaço na memória para o novo processo
          can_add = arr_pos[0] != -1;
          if ( can_add )
          {
               /* A posição final é a soma da posição inicial em memória 
                  mais com o tamanho das instruções e o número de variáveis */ 
               array_aux[1] = array_aux[0] + instr_size_plus_variables;

               // Passar o conteúdo das variaveis para o outro processo
               pass_variables(mem, proc->local_prog[0], proc->local_prog[0]+10, array_aux[0]);

               // Passar as instruções para a memória
               pass_content(arr_instr, array_aux, can_add, mem);

               // Atualizar a linked list
               update_linkedlist(free_mem, array_aux);

               // Adiciona o processo ao estado READY
               Pcb new_proc =  createProcess(*last_id, 0, arr_instr, READY, RUN, (int) instr_size);
               new_proc.local_prog[0] = array_aux[0]; // coloca a posição inicial em memória
               new_proc.local_prog[1] = array_aux[1]; // coloca a posição final   em memória
               new_proc.pc = array_aux[0] + (*prog_counter - proc->local_prog[0]);
               enqueue(state_ready, new_proc);

               // Atualiza o nome do ID para um processo futuro
               *last_id += 1;

               return 1;
          }
          else
               return -1;
     }
     return -1;
}
void disk_save(int *prog_counter, Pcb *proc)
{
     updateNextState(proc, BLOCKED);
     *prog_counter += 3;
}
void disk_load(int *prog_counter, Pcb *proc)
{
     updateNextState(proc, BLOCKED);
     *prog_counter += 3;
}
void print_x(int *prog_counter, int *var1)
{
     *prog_counter += 3;
     printf("%7d", *var1);
}
void exit_11(int *prog_counter, Pcb *proc, int *mem)
{
     *prog_counter += 3;
     updateNextState(proc, EXIT);
     // Apagar conteúdo da mem
     remove_inst(proc, mem);
}
// EXECUÇÃO DAS INTRUÇÕES A CIMA (ENUNCIADO)
void instructions(int *prog_counter, int begin_vars, Pcb *proc, int *memoria, Queue *state_ready, int *last_id, LinkedList *free_mem)
{
     int   inst = memoria[*prog_counter],
           pos1 = 0,  pos2 = 0,
          *var1 = 0, *var2 = 0;

     switch (inst)
     {
          case 0:
               pos1 =  memoria[*prog_counter + 1];
               pos2 =  memoria[*prog_counter + 2];
               var1 = &memoria[ begin_vars + (pos1-1)];
               var2 = &memoria[ begin_vars + (pos2-1)];
               set_x(prog_counter, var1, var2);
               break;
          case 1:
               pos1 =  memoria[*prog_counter + 1];
               var1 = &memoria[ begin_vars + (pos1-1)]; 
               var2 = &memoria[*prog_counter + 2];
               set_n(prog_counter, var1, var2);
               break;
          case 2:
               pos1 =  memoria[*prog_counter+1];
               var1 = &memoria[begin_vars + (pos1-1)];
               inc_x(prog_counter, var1);
               break;
          case 3:
               pos1 =  memoria[*prog_counter+1];
               var1 = &memoria[begin_vars + (pos1-1)];
               dec_x(prog_counter, var1);
               break;
          case 4:
               var1 = &memoria[*prog_counter + 1];
               back_n(prog_counter, var1, proc->local_prog[0]+10, proc, memoria);
               break;
          case 5:
               var1 = &memoria[*prog_counter+1];
               forw_n(prog_counter, var1, proc->local_prog[1], proc, memoria);
               break;
          case 6:
               pos1 = memoria[*prog_counter+1];
               var1 = &memoria[begin_vars + (pos1-1)];
               var2 = &memoria[*prog_counter+2];
               if_x_n(prog_counter, var1, var2);
               break;
          case 7:
               pos1 = memoria[*prog_counter + 1];
               var1 = &memoria[ begin_vars + (pos1 -1)];
               // Colocar o id do pai nas variáveis
               *var1 = pos1;
               fork_x(prog_counter, memoria, last_id, state_ready, proc, free_mem);
               break;
          case 8:
               disk_save(prog_counter, proc);
               break;
          case 9:
               disk_load(prog_counter, proc);
               break;
          case 10:
               pos1 =  memoria[*prog_counter + 1];
               var1 = &memoria[ begin_vars + (pos1-1)];
               print_x(prog_counter, var1);
               break;
          case 11:
               exit_11(prog_counter, proc, memoria);
               updateNextState(proc, EXIT);
               break;
     }
}
////////////////////////////////////////////////////////////////////
/**
 *   Execução no estado run
 */
// Verifica se há espaço em memória para colocar novas instruções
bool space_in_mem(int len, Pcb *proc, int *mem)
{
     int  inter,
          inicio, 
          end = MEM_SIZE - len;
     bool can_occupy = false;

     for (inicio = 0; inicio < end; inicio++)
     {
          for (inter = inicio; inter < inicio+len; inter++)
          {
               if (mem[inter] != -1)
               {
                    can_occupy = false;
                    break;
               }
               else
                    can_occupy = true;
          }
          if (can_occupy)
          {
               proc->local_prog[0] = inicio;
               proc->local_prog[1] = inicio+len;
               break;    
          }
     }
     return can_occupy;
}
// Passa o conteúdo das instruções de um processo para a memória
void pass_content(int *instructions, int *positions, bool in_mem, int *mem)
{
     int  count = 0,
          begin = positions[0],
          begin10 = begin + 10, // Ignorar as variáveis
          end  = positions[1],
          size = end - begin10 + 1;

     if (in_mem)
     {
          // colocar as variaveis a zero
          for (int i = begin; i < begin10; i++)
               mem[i] = 0;
          // copiar o conteúdo do array instruções
          for (int i = begin10; i <= end; i++)
          {
               mem[i] = instructions[count];
               count++;
          }
          count = 0;
          // Colocar o conteúdo do array instruções a zeros
          for (int i = 0; i < size; i++)
               instructions[i] = -1;
     }
}
// FUNÇÃO ESSENCIAL PARA A EXECUÇÂO DO PROGRAMA
void execution(Queue *state_run, Queue *state_ready, int *mem, int *last_id, LinkedList *list)
{
     Pcb *proc = &state_run->structure[0];

     if ( !emptyQueue(state_run) )
     {
          int  var_pos = proc->local_prog[0],
               beg_pos = var_pos + 10,
               end_pos = proc->local_prog[1] + 1,
               prog_counter = (proc->pc == 0) ? beg_pos : proc->pc;

          // Apanhar as primeiras 3 instruções
          if (prog_counter < end_pos)
          {
               instructions(&prog_counter, var_pos, proc, mem, state_ready, last_id, list);
               proc->pc = prog_counter;
          }
     }
     printf("\n");
}
void arr_to_new(Pcb *arr, int *mem, int size, unsigned short *exe_time, Queue *new_state, LinkedList *free_mem)
{
     for(int i = 0; i < size; i++)
     {          
          Pcb proc = arr[i];
          int  *arrive_time = &proc.arrive_time,
               *pos_mem     = &proc.local_prog[0],
                arr_size, index;
          bool arr_in_memory = *pos_mem >= 0;

          if( *arrive_time <= *exe_time && *arrive_time >= 0)
          {
               // Ver tamanho do array de instruções + 10 variaveis
               arr_size = arr_int_size(proc.instructions) + 10;

               /* Procurar lugar disponivel  na memoria e atualiza o array 
                    "local_prog" com a posição inicial e final da memória.  */
               int *array_info = can_insert_process(free_mem, arr_size);
               array_info[1] = arr_size + array_info[0];
               proc.local_prog[0] = array_info[0];
               proc.local_prog[1] = array_info[0] + arr_size;
               proc.arrive_time = 0;

               // Indice do array na linked list
               index = array_info[2];

               arr_in_memory = index != -1;
               if (arr_in_memory && !fullQueue(new_state))
               {
                    // Atualizar a Linked List
                    update_linkedlist(free_mem, array_info);
                    
                    // Passar o conteúdo do array instruções para a memória
                    pass_content(proc.instructions, proc.local_prog, arr_in_memory, mem);

                    // Colocar a posição do array com valores negativos
                    arr[i] = createProc();
                    proc.arrive_time = 0;
                    enqueue(new_state, proc);
               }
          }
     }
}
////////////////////////////////////////////////////
// FILL PROCESSES
void fill_processes(int len, Pcb *procs)
{
     int *new_pid, pid, 
          i; 
     // Até onde está preenchido
     for (i = len-1; i > 0; i--)
     {
          pid = procs[i].pid;
          if(pid != -1)
               break;
     }
     for (int j = 0; j < i; j++)
     {
          new_pid = &procs[j].pid;
          if (*new_pid == -1)
               *new_pid = j;
     }
}
// PRINT SOBRE ONDE ESTÃO OS PROCESSOS
void print_situation(Queue *state_new, Queue *state_ready, Queue *state_block, Queue *state_run, Queue *state_exit, unsigned short *time)
{
     Queue arr_queue[] = {*state_new, *state_ready, *state_block, *state_run, *state_exit};
     int  len = 4 * ARRAY_STATES + RUN_SIZE;
     Pcb processes[len];

     for (int i = 0; i < len; i++)
          processes[i] = createProc();
     
     for (int i = 0; i < 5; i++)
     {
          Queue *q = &arr_queue[i];
          for (int j = 0; j < q->size; j++)
          {
               int pid = q->structure[j].pid;
               if (pid >= 0)
                    processes[pid] = q->structure[j];
          }
     }
     // Ver onde esta o ultimo processo e se tiver espaço vazio preencher com ID
     fill_processes(len, &processes[0]);

     printf("%4d |", *time);
     for (int i = 0; i < len; i++)
     {
          int pid = processes[i].pid;
          if ( pid >= 0)
          {
               int  state = processes[i].state;
               char *name = int_state(state);
               
               printf(" %7s |", name);
          }
     }
}
// APENAS UM PRINT
void printPID(Pcb *procs)
{
     printf("\n%4s |", "pid");
     for (int i = 0; i < BUFFER; i++)
     {
          Pcb *p = &procs[i];
          if ( p->pid >= 0 )
               printf(" %7d |", p->pid);
     } 
     printf("\n");
}
char *name_file()
{
     char file_pos[18] = "Inputs/input",
          option[1], terminal[] = ".txt",
          *pointer_pos = file_pos;

     printf("Indique o nome do ficheiro:\n");
     scanf(" %s", option);
     file_pos[0] = 'I';
     file_pos[12] = option[0];
     strcat(file_pos, terminal);

     return pointer_pos;
}
////////////////////////////////////////////////////
int main(void)
{
     // MEMORIA
     int  last_id,
         *mem_pointer = &memoria[0];
     
     // Tempo do programa
     unsigned short exe_time = 0;
     short arr_free_pos[2] = {0, MEM_SIZE};

     // Buffer
     Pcb processes[BUFFER];

     /* Linked List com as posições de memória livre
        + espaço disponível inicialmente [0, 300] */ 
     LinkedList *free_mem = new_LinkedList();
     add_list( free_mem, &arr_free_pos[0]);

     // Preencher a memória com valores negativos
     arrset_val(MEM_SIZE, &mem_pointer[0], -1);

     // Nome do ficheiro
     char *file_pos = name_file();

     // Processo New pronto
     workFile( file_pos, &processes[0], BUFFER, &last_id);

     // ESTADOS
     Queue state_new   = createQueue(ARRAY_STATES);
     Queue state_ready = createQueue(ARRAY_STATES);
     Queue state_run   = createQueue(RUN_SIZE);
     Queue state_block = createQueue(ARRAY_STATES);
     Queue state_exit  = createQueue(ARRAY_STATES);

     // Print dos ID dos processos
     printPID(&processes[0]);

     // Execução do programa
     while(true)
     {          
          // Passar o conteúdo do array/buffer para o estado NEW
          arr_to_new(&processes[0], mem_pointer, BUFFER, &exe_time, &state_new, free_mem);

          // Se algum processo quer passar de NEW para RUN directamente
          new_run(&state_new, &state_ready, &state_run);

          // Se algum processo precisa de entrar em EXIT
          run_exit(&state_run, &state_exit);

          /** Se algum processo precisa de entrar em READY (TER CUIDADO COM PRECEDÊNCIA):
           *     Blocked -> Ready (e/ou)
           *     Run -> Ready     (e/ou)
           *     New -> Ready                  */
          blocked_ready(&state_block, &state_ready);
          run_ready(&state_run, &state_ready);
          new_ready(&state_new, &state_ready);

          // Se algum processo precisa de entrar em BLOCKED
          run_blocked(&state_run, &state_block);

          // Se algum processo precisa de entrar em RUN
          ready_run(&state_ready, &state_run);

          // Remove os programas no estado EXIT
          remove_exit(&state_exit, free_mem);

          // Verifica se o programa termina
          if (stop(&state_new, &state_ready, &state_run, &state_block, &state_exit))  break;

          // Print dos conteudos (ENUNCIADO)
          print_situation(&state_new, &state_ready, &state_block, &state_run, &state_exit, &exe_time);

          // Execução do estado RUN
          execution(&state_run, &state_ready,&mem_pointer[0], &last_id, free_mem);

          /**
           * Print da memória a cada instante de tempo
           */
          // print_memory(mem_pointer, MEM_SIZE);
          
          // Atualiza o arrive time de todos os processos
          add1Arrive(&state_new);
          add1Arrive(&state_ready);
          add1Arrive(&state_run);
          add1Arrive(&state_block);
          add1Arrive(&state_exit);

          // Aumentar o tempo de execução
          increase_exe_time(&exe_time);
     }
     // Libertar a Linked List
     free(free_mem);
}