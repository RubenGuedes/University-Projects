#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>

# define PORT 8000

int main(int argc, char const *argv[])
{
	int server_fd,  new_socket,  valread;

	int opt = 1;
	int addrlen = sizeof(address);

	// To send a message
	char buffer[1024] = {0};
	char *hello = "Hello from server";

	// creates  an  endpoint  for  communication  and returns a file
    //   descriptor that refers to that endpoint
	if ( (server_fd = socket( AF_INET, SOCK_STREAM, 0))  )
	{
		perror(ERROR);
		exit(0);
	}


	// manipulate  options for the socket referred to by the
    //   file descriptor sockfd.
	if (
		setsockopt( 
				server_fd, 
				SOL,SOCKET, 
				SO_REUSEADDR | SO_REUSEPORT,
				&opt,
				sizeof(opt) 
				)
		)
	{
		perror(ERROR);
		exit(1);
	}
	// Connect to the remote server
	struct  sockaddr_in address;

	address.sin_family = AF_INET;
	address.sin.addr.s_addr = INADDR_ANY;
	address.sin_port = htons( PORT );

	/*
		When  a  socket  is created with socket(2), it exists in a name space (address family)
    	but has no address assigned to it.
    */
	if(
		bind(
			server_fd,
			(struct sockaddr *)&address,
			(socklen_t*)&addrlen
			) < 0
		)
	{
		perror(ERROR);
		exit(2);
	}
	/*
		Manipulate  options for the socket referred to by the
    	file descriptor sockfd.
	*/
	if (listen(server_fd, 2) < 0)
	{
		perror(ERROR);
		exit(3);
	}

	if ((new_socket = accept(
							server_fd,
							(struct sockaddr *)&address,
							(socklen_t*)&addrlen)
		) < 0)
	{
		perror(ERROR);
		exit(4);		
	}
	
	printf("%s\n", buffer);
	valread = read( new_socket, buffer, 1024 );
	
	if(send(new_socket, hello, strlen(hello), 0))
	{
		perror(ERROR);
		exit(5);
	}

	printf("Hello message sent\n"); 


	return 0;
}