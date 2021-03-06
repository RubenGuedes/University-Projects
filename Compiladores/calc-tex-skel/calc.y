%{
#include <stdio.h>
#include "calctypes.h"
#include "latex.h"

int yylex (void);
void yyerror (char const *);
%}
			
%union {
    double val;
    char  *name;
    calc_t_exp exp;
    calc_t_seq seq;
}

/* Bison declarations.  */
%token	<val>	NUM
%token	<name>	ID
%token			NL /* newline */
			
%right			ASSIGN

%left			LESS EQUAL GREATER GEQUAL LEQUAL
%left			SUB ADD 
%left			MUL DIV
%left			NEG     /* negation--unary minus */
			
%token			LPAR RPAR
			
%type	<exp>  	exp
%type	<seq>  	seq
%%


input:  	seq  { 
				print_prologue();
				calc_seq_print($1);
				print_epilogue(); 
			}
;

seq:   	/* empty */ { $$ = calc_seq_new_empty(); }
	| 	exp NL seq  { $$ = calc_seq_new_exp($1, $3); }
;

exp:		NUM                	{ $$ = calc_exp_new_num($1); }
	|	ID                 	{ $$ = calc_exp_new_id($1); }
	| 	exp ADD exp        	{ $$ = $1 + $3;	}
     | 	exp SUB exp        	{ $$ = $1 - $3;	}
     | 	exp MUL exp        	{ $$ = $1 * $3;	}
     | 	exp DIV exp        	{ $$ = $1 / $3;	}
	| 	SUB exp %prec NEG 	{ $$ = (0 - $2);	}
	| 	LPAR exp RPAR      	{ $$ =  $2;		}
	| 	ID ASSIGN exp      	{ $$ =  $3;		}
	|	exp LESS    exp	{ $$ = $1 <  $3;	}
	|    exp GREATER exp	{ $$ = $1 >  $3;	}
	|    exp LEQUAL  exp	{ $$ = $1 <= $3;	}
	|	exp GEQUAL  exp 	{ $$ = $1 >= $3;	}
	|	exp AND	  exp	{ $$ = $1 && $3;	}
	|	exp OR 	  exp 	{ $$ = $1 || $3;	}
;
%%	
/* Called by yyparse on error.  */
void yyerror (char const *s)
{
    fprintf (stderr, "%s\n", s);
}
int main (void)
{
    return yyparse();
}
