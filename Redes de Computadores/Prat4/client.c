#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>

#define PORT 9034

int main(int argc, char const *argv[])
{
    struct sockaddr_in address;
    struct sockaddr_in serv_addr;
    int sock_fd = 0, valread;
    char *hello = "Hello from client";
    char buffer[1024] = {0};

    sock_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (sock_fd < 0) 
    {
        perror("Socket creation error!");
        exit(0);
    }
    // colocar zeros em serv_addr
    bzero(  (char *) &serv_addr, 
            sizeof(serv_addr) 
            );

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port   = htons(PORT); 
    
    
    if (inet_pton(AF_INET, "127.0.0.1", &serv_addr.sin_addr) <= 0 ) 
    {
        printf("\nInvalid address/ Address not supported \n"); 
        return -1;
    }
    
    
    if (    connect(    sock_fd,
                        (struct sockaddr*)&serv_addr,
                        sizeof(serv_addr)
                        )    
        ) 
    {
        printf("\nConnection Failed \n"); 
        return -1; 
    }
    send(sock_fd, hello, strlen(hello), 0);
    printf("Hello message sent\n"); 
    valread = read( sock , buffer, 1024); 
    printf("%s\n",buffer );
    
    return 0;
}
