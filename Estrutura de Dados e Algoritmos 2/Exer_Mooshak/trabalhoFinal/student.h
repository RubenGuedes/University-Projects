#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define COUNTRY (2+1)
#define GLOBAL_ID (6+1)
#define STUDENT_SIZE sizeof(Student)

typedef struct student Student;

struct student
{
  bool completed,
       dropped_out;
  char country[COUNTRY],
       global_id[GLOBAL_ID];
};

/**
 * Compara duas arrays de char
 */
int compare(char [], char []);

/**
 * Converte uma string num valor long.
 * Este valor será usado na HashTable
 */
long hashCode(int size, char [size]);

/**
 * Copia o conteúdo de uma string para outra string
 */
void mystrcopy(char *, char *);

/**
 * Cria um novo estudante
 * Argumentos:
 * completed, bool dropped, bool active, char country[COUNTRY], char id[GLOBAL_ID]
 *   -> completed (Se o aluno completou o curso)
 *   -> dropped (Se o aluno desistiu do curso)
 *   -> country (País do estudante)
 *   -> id (Identificação global do estudante)
 * Return:
 *   -> Um novo estudante (Student)
 */
Student *new_student_args(bool, bool, char [COUNTRY], char [GLOBAL_ID]);
/**
 * Destroi a memória dedicada ao estudante
 */
void destroy_student(Student *);

/**
 * Getters e Setters
 */
// Id = Identificação global do estudante
void set_id(Student *, char [GLOBAL_ID]);
char *get_id(Student *);

// Country = País onde o estudante está a residir 
void set_country(Student *, char [COUNTRY]);
char *get_country(Student *);

// Completed = Se completou o curso
void set_completed(Student *, bool);
bool get_completed(Student *);

// Se abandonou o curso
void set_dropped_out(Student *, bool);
bool get_dropped_out(Student *);

// Se o aluno está ativo
bool get_active_student(Student *);