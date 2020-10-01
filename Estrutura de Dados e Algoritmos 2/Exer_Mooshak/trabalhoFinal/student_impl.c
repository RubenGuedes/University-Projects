#include "student.h"

// -------------------------------------
int compare(char cmp[], char to_be_cmp[])
{
  int i = 0;
  while(cmp[i] == to_be_cmp[i] && cmp[i] != '\0')
    i++;

  if (cmp[i] == '\0')
    return 0;

  else if (cmp[i] > to_be_cmp[i])
    return -1;

  else
    return 1;
}

void mystrcopy(char *src, char *dest)
{
  int i = 0;
  while (src[i] != '\0')
    {
      dest[i] = src[i];
      i++;
    }

  dest[i] = '\0';
}

/**
 * Código baseado no site:
 * -> "https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#hashCode--"
 */
long hashCode(int size, char id[size])
{
  int hash = 101;
  long value = 0;
  int n = size - 1;

  for (int i = 0; i < n; i++)
    value += (id[i] * pow( hash, n-(i+1) ));
      
  return value;
}

Student *new_student()
{
  Student *p = malloc(sizeof(Student));
  return p;
}

// -------------------------------------
Student *new_student_args(bool dropped, bool completed, char country[COUNTRY], char id[GLOBAL_ID])
{
  Student *p = malloc(sizeof(Student));

  set_id(p, id);
  set_country(p, country);
  set_completed(p, completed);
  set_dropped_out(p, dropped);

  return p;
}

void destroy_student(Student *p)
{
  free(p);
}

// -------------------------------------
void set_id(Student *p, char id[GLOBAL_ID])
{
  mystrcopy(id, p->global_id);
}

char *get_id(Student *p)
{
  return p->global_id;
}

// -------------------------------------
void set_country(Student *p, char country[COUNTRY])
{
  mystrcopy(country, p->country);
}

char *get_country(Student *p)
{
  return p->country;
}

// -------------------------------------
void set_completed(Student *p, bool comp)
{
  p->completed = comp;
}

bool get_completed(Student *p)
{
  return p->completed;
}

// -------------------------------------
void set_dropped_out(Student *p, bool dropped)
{
  p->dropped_out = dropped;
}

bool get_dropped_out(Student *p)
{
  return p->dropped_out;
}

//--------------------------------------
bool get_active_student(Student *st)
{
  return !st->dropped_out && !st->completed;
}
