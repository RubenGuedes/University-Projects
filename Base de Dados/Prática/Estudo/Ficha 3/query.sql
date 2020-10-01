/*
Exercício 1

Select nomes,nbis
From socio
Where cidades = 'Beja'
*/

/*
Exercício 2
Select nomea
From autor
Where coda in (Select coda
               From autoria
               Where isbn in (Select isbn
                              From livro
                              Where titulo = 'Historia do Cerco de Lisboa'))
*/
