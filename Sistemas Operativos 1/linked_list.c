#include <stdlib.h>
#include "linked_list.h"

// GESTÃO DE MEMÓRIA
#define BESTFIT 0
#define NEXTFIT 1
#define OPTION NEXTFIT

LinkedList *new_LinkedList()
{
     LinkedList *list = malloc(sizeof(LinkedList));
     list->head = new_Node();
     list->tail = new_Node();
     list->size = 0;
     list->last_ocupied = 0;

     // Links
     struct node *head = list->head,
                 *tail = list->tail;
     
     head->right = tail;
     tail->left  = head;
     
     return list;
}
struct node *new_Node()
{
     struct node *node = malloc(sizeof(struct node));

     node->left  = NULL;
     node->right = NULL;
     node->posicoes[0] = -1;
     node->posicoes[1] = -1;

     return node;
}
void print_forward(LinkedList *list)
{
     struct node *no = list->head->right;
     short size = list->size, 
           index, 
           inicio, fim;

     printf("Forward -> ");
     for (index = 0; index < size; index++)
     {
          inicio = no->posicoes[0];
          fim    = no->posicoes[1];
          printf("[%2hd, %2hd] ", inicio, fim);
          no = no->right;
     }
     printf("\n");
}
void print_backward(LinkedList *list)
{
     struct node *no = list->tail->left;
     short size = list->size,
           index,
           inicio, fim;
          
     printf("Backward -> ");
     for (index = size-1; index >= 0; index--)
     {
          inicio = no->posicoes[0];
          fim    = no->posicoes[1];
          printf("[%2hd, %2hd] ", inicio, fim);
          no = no->left;   
     }
     printf("\n");
}
void add_list(LinkedList *list, short *pos)
{
     short *last_ocupied = &list->last_ocupied;
     struct node *footer = list->tail,
                 *before_footer = list->tail->left,
                 *no = new_Node();

     // ordem em que o no foi inserido            
     no->order = *last_ocupied;

     no->posicoes[0] = pos[0];
     no->posicoes[1] = pos[1];

     // Increment
     list->size += 1;
     
     // Links
     before_footer->right = no;
     no->left = before_footer;

     footer->left = no;
     no->right = footer;

     *last_ocupied += 1;
}
void remove_item(LinkedList *list, short index)
{
     struct node *no = list->head->right,
                 *prev_node, 
                 *next_node;
     short ind  = 0,
           size = list->size;

     if (index < size && index >= 0)
     {
          while( ind < index )
          {
               no = no->right;  
               ind++;
          }

          prev_node = no->left;
          next_node = no->right;

          // Links
          prev_node->right = next_node;
          next_node->left  = prev_node;

          list->size--;
     }
}
// Se tiver fragmentos de memória que se possa juntar, fá-lo
void join_fragments(LinkedList *list)
{
     struct node *atual_node = list->head->right,
                 *next_node;

     short size = list->size,
           num_times = ((float)size/2.0 != size/2) ? size/2 +1 : size/2,
           atual_val1, atual_val2,
          *next_val1,
           index_to_remove[size], arr_ind;
     
     for (short t = 0; t < num_times; t++)
     {
          arr_ind = 0;
          for (short i = 0; i < size; i++)
          {
               // Se é possível ir ao próximo nó
               if (atual_node->right != NULL)
               {
                    next_node  = atual_node->right;
                    atual_val1 = atual_node->posicoes[0];
                    atual_val2 = atual_node->posicoes[1];
                    next_val1  = &next_node->posicoes[0];

                    if (atual_val2 + 1 == *next_val1)
                    {
                         *next_val1 = atual_val1;
                         index_to_remove[arr_ind++] = i; 
                         atual_node = next_node;   
                    }               
               }
          }
          short val;
          // Remover os nós que não vão ser necessários.
          for (short i = arr_ind-1; i >= 0; i--)
          {
               val = index_to_remove[i];
               if (val != -1)
               {
                    remove_item(list, index_to_remove[i]);
                    list->size--;
                    index_to_remove[i] = -1;
               }
          }
     }
}
// Tamanho do espaço em memória
short fragment_size(int *arr_pos)
{
     short val1 = arr_pos[0],
           val2 = arr_pos[1];
     
     return val2 - val1;
}
/* Devolve o espaço onde se pode armazenar os conteúdos em 
   memória mais o repetivo índice                */
int *can_insert_process(LinkedList *list, short necessary_len)
{
     short count = list->size,
           space;
     int  arr_posi[3] = { -1, -1, -1},
          *pointer_arr = arr_posi; 

     if (OPTION == BESTFIT)
     {
          struct node *no = list->head->right;
          for (short i = 0; i < count; i++)
          {
               arr_posi[0] = no->posicoes[0];
               arr_posi[1] = no->posicoes[1];
               space = fragment_size(arr_posi);

               // Indica em que espaço da memória pode-se inserir o novo conteúdo.
               if (necessary_len <= space)
               {
                    arr_posi[2] = i;
                    return pointer_arr;
               }
               no = no->right;     
          }
     }
     else if ( OPTION == NEXTFIT )
     {
          struct node *no = list->tail->left;
          short size = list->size,
                last_ocupied = list->last_ocupied;

          while ( last_ocupied != 0 )
          {
               for (short i = 0; i < size; i++)
               {
                    if ( (last_ocupied-1) == no->order )
                    {
                         arr_posi[0] = no->posicoes[0];
                         arr_posi[1] = no->posicoes[1];
                         space = fragment_size(arr_posi);

                         // Indica em que espaço da memória pode-se inserir o novo conteúdo.
                         if (necessary_len <= space)
                         {
                              arr_posi[2] = i;
                              return pointer_arr;
                         }
                    }
                    no = no->right;
               }
               last_ocupied--;
          }
     }    
     // Se não é possivel inserir o conteúdo em memória.
     return pointer_arr;
}
/* Função que devolve uma posição de memória global a 
   partir de um certo indice da Linked List.        */
short *pos_mem_linked_list(LinkedList *list, short index)
{
     struct node *no = list->head->right;
     short *arr_pos;

     for (short i = 0; i < index; i++)
          no = no->right;

     arr_pos = &no->posicoes[0];

     return arr_pos; 
}
/* Função que tem como objetivo, dada um posição de memória, ir ao repetivo 
   indice da linked list e atualiza-a.
   
   Argumentos
        -> LinkedList com os espaços livres em memória
        -> Array de tamanho 3 
           º [ Pos_inicial_em_memória, Pos_final_em_memória, Indice_da_posicao_linked_list ] */ 
void update_linkedlist(LinkedList *list, int *arr)
{
     short index = (short) arr[2],
          // Apontador para o conteúdo que está na linked list -> array[2 posições]
          *arr_pos_mem = pos_mem_linked_list( list, index );
     int  arr_input0 = arr[0],
          arr_input1 = arr[1],
          arr_list0  = arr_pos_mem[0],
          arr_list1  = arr_pos_mem[1];
     
     /* Se as posições iniciais e finais forem as mesmas
     apagar o conteúdo da linked list                  */ 
     if (arr_input0 == arr_list0 && arr_input1 == arr_list1)
          remove_item(list, arr[2]);

     // Se não é para apagar tudo, atualiza apenas
     else
          arr_pos_mem[0] = arr_input1;
}
// Uma vez que um processo saia da memória é necessário atualizar a linked list
void remove_interval_linked_list(LinkedList *free_mem, int *interval)
{
     short size = free_mem->size;
     struct node *no = free_mem->head->right;
     bool added = false;
     
     for (short i = 0; i < size; i++)
     {
          if (interval[0] < no->posicoes[0])
          {
               struct node *prev = no->left,
                           *new_node = new_Node();

               new_node->posicoes[0] = interval[0];
               new_node->posicoes[1] = interval[1];
               
               // Ligações
               prev->right = new_node;
               new_node->right = no;

               new_node->left = prev;
               no->left = new_node;

               // Incrementar o tamanho
               free_mem->size++;

               added = true;
               break;
          }
          else if (interval[0] == no->posicoes[0])
          {
               added = true;
               break;
          }

          no = no->right;
     }
     // se não adicionou ate agora, adicionar no fim da linked list
     if ( !added )
     {
          add_list(free_mem, no->posicoes);
          free_mem->size++;
     }
}