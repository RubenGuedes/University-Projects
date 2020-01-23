#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <netdb.h>
#include <netinet/in.h>

#include <string.h> 


int main(int argc, char const *argv[])
{
	int sockfd, portno, n;
	struct sockaddr_in serv_addr;
	struct hostent *server;
	char buffer[256];
	portno = 13;

	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if(sockfd < 0)
	{
		perror("ERROR operatinf socket");
		exit(1);
	}

	server = gethostbyname("time.nist.gov");
	if (server == NULL)
		fprintf(stderr, "Error, no such host\n" );

	// na area que procura coloca zeros(quanto o tamanho necessário) 
	bzero ((char *) &serv_addr, sizeof(serv_addr));

	// copia bytes de um sitio para o outro
	bcopy( 	(char *)server->  h_addr,    
			(char *)&serv_addr.sin_addr.s_addr,   
			server->h_length);
	// associar o IPV4 ao serv_addr
	serv_addr.sin_family = AF_INET;
	// porta , converter o numero de host para 
	serv_addr.sin_port = htons(portno);

	// now connect to the server
	if( connect(sockfd , (struct sockaddr*)&serv_addr , sizeof(serv_addr) ) < 0 )
	{
		perror("Error Connecting");
		exit(2);
	}
	/*
		Já está ligado
	 */
	 // colocar o buffer a zeros
	bzero(buffer, sizeof(buffer) );
	// Read para o buffer 
	n = read(sockfd, buffer, sizeof(buffer)-1);
	if (n < 0)
	{
		perror("Error reading socket");
		exit(3);
	}
	else
		printf("%s\n", buffer);
	return 0;
}