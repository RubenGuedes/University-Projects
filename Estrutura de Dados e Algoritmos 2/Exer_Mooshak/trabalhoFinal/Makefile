Clean = rm -f *.o bnodes_file index_file

clean:
	${Clean}

aux:
	${Clean}
	gcc -Wall -std=gnu99 --coverage -fprofile-arcs -ftest-coverage -g *.c -o final.o -lm

compile:
	${Clean}
	gcc -Wall -std=gnu99 -g *c -o final.o -lm

run:
	./final.o

A1:
	./final.o < testing/teste-A-1.in | diff testing/teste-A-1.out -

A2:
	./final.o < testing/teste-A-2.in | diff testing/teste-A-2.out -

B1:
	./final.o < testing/teste-B-1.in | diff testing/teste-B-1.out -

B2:
	./final.o < testing/teste-B-2.in | diff testing/teste-B-2.out -

B3:
	./final.o < testing/teste-B-3.in | diff testing/teste-B-3.out -

C1:
	./final.o < testing/teste-C-1.in | diff testing/teste-C-1.out -

C2:
	./final.o < testing/teste-C-2.in | diff testing/teste-C-2.out -

C3:
	./final.o < testing/teste-C-3.in | diff testing/teste-C-3.out -

D1:
	./final.o < testing/teste-D-1.in | diff testing/teste-D-1.out -

D2:
	./final.o < testing/teste-D-2.in | diff testing/teste-D-2.out -

D3:
	./final.o < testing/teste-D-3.in | diff testing/teste-D-3.out -

prof:
	gprof ./final.o < gmon.out > prof_out

debug:
	gdb ./final.o
