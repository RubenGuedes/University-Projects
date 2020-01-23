#include <unistd.h>
#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#include <time.h>

#define PORT 1300

void strdate(char *buffer, int len)
{
    time_t now = time(NULL);
    struct tm *ptm = localtime(&now);

    if(ptm == NULL)
    {
        puts("The localtime() function failed");
        return;
    }
    strftime(buffer, len, "%c\n", ptm);
}
int main(int argc, char const *argv[])
{
    int server_fd, new_socket;
    struct sockaddr_in address;
    struct sockaddr client_udp,

    int opt = 1; // for setsocket() SO_REUSEADDR, below
    int addrlen = sizeof(address);
    
    char buffer[256];
    strdate(buffer, 256);

    // Creating socket file descriptor
        // AF_INET = IPV4   
        // Tcp = Sock_stream
        // INADDR_ANY = escutar ligaçoes em qualquer endereço ip
    if( (server_fd = socket(AF_INET, SOCK_DGRAM, 0)) == 0)
    {   
        perror("socket failed");
        exit(EXIT_FAILURE);
    }

    // Forcefully attaching socket to the port 1300
    if( setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt) ))
    {
        perror("setsockopt failed");
        exit(EXIT_FAILURE);
    }

    client_udp.sin_family       = AF_INET;
    client_udp.sin_addr.s_addr  = INADDR_ANY;
    client_udp.sint_port        = hton(PORT);

    // Bind the socket to the network address and port
    if( bind(server_fd, (struct sockaddr *) &client_udp, sizeof(client_udp)) < 0)
    {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }

    if( listen(server_fd , 3) < 0)
    {
        perror("listen failed");
        exit(EXIT_FAILURE);
    }
    recvfrom(server_fd, buffer, strlen(buffer), 0, (struct sockaddr *) &client_udp, sizeof(client_udp));

    printf("Client connected.\n");
    sendto(  server_fd, buffer, strlen(buffer), 0, NULL, 0);
    printf("Date sent to client\n");
    
    return 0;
}