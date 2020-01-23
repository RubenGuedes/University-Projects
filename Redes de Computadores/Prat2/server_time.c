#include <unistd.h>
#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#include <time.h>

#define PORT 1300

// metodo para colocar a data no buffer
void strdate(char *buffer, int len)
{
    time_t now = time(NULL);
    struct tm *ptm = localtime(&now);

    if(ptm == NULL)
        puts("The localtime() function failed");

    strftime(buffer, len, "%c\n", ptm);
}

// main
int main(int argc, char const *argv[])
{
    int server_fd, new_socket;
    struct sockaddr_in address;

    int opt = 1; // for setsockopt() SO_REUSEADDR, below
    int addrlen = sizeof(address);
    
    char buffer[256];
    strdate(buffer, 256);

    // Creating socket file descriptor
        // AF_INET = IPV4   
        // Tcp = Sock_stream
        // INADDR_ANY = escutar ligaçoes em qualquer endereço ip
    server_fd = socket(AF_INET, SOCK_STREAM, 0)
    if( server_fd == 0)
    {   
        perror("socket failed");
        exit(EXIT_FAILURE);
    }

    // Forcefully attaching socket to the port 1300
    if( setsockopt( server_fd   ,   
                    SOL_SOCKET, 
                    SO_REUSEADDR | SO_REUSEPORT, 
                    &opt, 
                    sizeof(opt) )
        )
    {
        perror("setsockopt failed");
        exit(EXIT_FAILURE);
    }

    address.sin_family      = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port        = htons( PORT );

    // Bind the socket to the network address and port
    if( bind(server_fd, (struct sockaddr *) &address, sizeof(address)) < 0)
    {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }

    if( listen(server_fd , 3) < 0)
    {
        perror("listen failed");
        exit(EXIT_FAILURE);
    }

    // Wait for a connection
    if( (new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0 )
    {
        perror("accept failed");
        exit(EXIT_FAILURE);
    }

    printf("Client connected.\n");
    send(new_socket, buffer, strlen(buffer), 0);
    printf("Date sent to client\n");
    close(new_socket);
    return 0;
}