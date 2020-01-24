%option noyywrap
%{
     #include <stdio.h>
%}
ID    [a-z]+
SPACE [\t ]+
/* 
     Declaração da variavel
     Declaração da variavel com valor
     Multiplas declarações de variáveis
     Declaração de função sem argumentos
     Declaração de função com argumentos
     DEFINE (Declaração de novo tipo)
*/ 

%%
"or"   {  return OR                  ;}
"and"  {  return AND                 ;}
"mod"  {  return MODULE              ;}
"not"  {  return NOT                 ;}

"int"     { return INT               ;}
"float"   { return FLOAT             ;}
"string"  { return STRING            ;}
"bool"    { return BOOL              ;}
"void"    { return VOID              ;}

"define"  { return DEFINE            ;}

"if"      { return IF                ;}
"then"    { return THEN              ;}
"else"    { return ELSE              ;}
"while"   { return WHILE             ;}
"do"      { return DO                ;}
"return"  { return RETURN            ;}
"break"   { return BREAK             ;}
"next"    { return NEXT              ;}

"=="   {  return EQUAL               ;}
"!="   {  return NOT_EQUAL           ;}
"<"    {  return LESS_THAN           ;}
">"    {  return GREAT_THAN          ;}
"<="   {  return LESS_EQUAL_THAN     ;}
">="   {  return GREAT_EQUAL_THAN    ;}

"="    {  return ASSIGN              ;}
"^"    {  return EXPONENTIAL         ;}
"+"    {  return PLUS                ;}
"-"    {  return SUBTRACT            ;}
"/"    {  return DIVISION            ;}
"*"    {  return MULTIPLICATION      ;}

"("    {  return LEFT_PAR            ;}
")"    {  return RIGHT_PAR           ;}
"["    {  return LEFT_BRACKETS       ;}
"]"    {  return RIGHT_BRACKETS      ;}
":"    {  return ASSOCIATE           ;}
","    {  return COLON               ;}
";"    {  return SEMICOLON           ;}
\n     {  /*IGNORE*/                  }

{SPACE} {printf("Space ok\n");}
{ID}    {printf("ID ok\n");}
