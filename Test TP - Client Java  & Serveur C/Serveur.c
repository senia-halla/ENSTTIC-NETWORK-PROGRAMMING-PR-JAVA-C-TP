#if defined (WIN32)
    #include <winsock2.h>
    typedef int socklen_t;
#elif defined (linux)
    #include <sys/types.h>
    #include <sys/socket.h>
    #include <netinet/in.h>
    #include <arpa/inet.h>
    #include <unistd.h>
    #define INVALID_SOCKET -1
    #define SOCKET_ERROR -1
    #define closesocket(s) close(s)
    typedef int SOCKET;
    typedef struct sockaddr_in SOCKADDR_IN;
    typedef struct sockaddr SOCKADDR;
#endif
 
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h> // for the random number 
#define BUFLEN 32
#define PORT 23
 
 
 
int main(void)
{
    #if defined (WIN32)
        WSADATA WSAData;
        int erreur = WSAStartup(MAKEWORD(2,2), &WSAData);
    #else
        int erreur = 0;
    #endif
 
    SOCKET sock;
    SOCKADDR_IN sin;
    SOCKET csock;
    SOCKADDR_IN csin;
    char case1[BUFLEN]="TOO HIGH !TRY AGAIN\n";  
    char case2[BUFLEN]="TOO LOW !TRY AGAIN\n";
    char case3[BUFLEN]="YOU WON ! Congrats\n";
    char greetings[BUFLEN] = "";//message a recevoir pour tester 
    char input[BUFLEN]="";//the client's guess
    srand(time(NULL)); //pour que la sequence random ne soit pas tjrs la meme
    

    
    socklen_t recsize = sizeof(csin);
    int sock_err;
 
    /* Si les sockets Windows fonctionnent */
    if(!erreur)
    {
        sock = socket(AF_INET, SOCK_STREAM, 0);//entrain de declarer et cree notre socket du serveur
 
        /* Si la socket est valide */
        if(sock != INVALID_SOCKET)
        {
            printf("La socket %d est maintenant ouverte en mode TCP/IP\n", sock);
 
            /* Configuration */
            sin.sin_addr.s_addr    = htonl(INADDR_ANY);   /* Adresse IP automatique */
            sin.sin_family         = AF_INET;             /* Protocole familial (IP) */
            sin.sin_port           = htons(PORT);         /* Listage du port */
            sock_err = bind(sock, (SOCKADDR*)&sin, sizeof(sin));
 
            /* Si la socket fonctionne */
            if(sock_err != SOCKET_ERROR)
            {
                /* Démarrage du listage (mode server) */
                sock_err = listen(sock, 5);// mettre notre socket en etat d'ecoute , 5 est le nombre maximal de connexions pouvant être mises en attente.
             
                printf("Listage du port %d...\n", PORT);
 
                /* Si la socket fonctionne */
                if(sock_err != SOCKET_ERROR)
                {
                    /* Attente pendant laquelle le client se connecte */
                    printf("Patientez pendant que le client se connecte sur le port %d...\n", PORT);        
 
                    csock = accept(sock, (SOCKADDR*)&csin, &recsize);// csocl socket du client et csin son contexte d addressage 
                    printf("Un client se connecte avec la socket %d de %s:%d\n", csock, inet_ntoa(csin.sin_addr), htons(csin.sin_port));
 

                    
                    //generates a number between 0 and 100
                    int random_number=(rand()%99)+1;

                    //envoyer un msg pour tester
                        if(recv(csock,greetings, BUFLEN,0)!=SOCKET_ERROR){
                            printf(" CLIENT JAVA  : %.*s\n",BUFLEN,greetings);

                            //repeter le meme message que le client a envoyer
                            sock_err = send(csock,greetings, BUFLEN, 0);
                            
                            // sock_err = send(csock," GUESSING GAME\n", BUFLEN, 0);
                            
                            printf("LE JEU VA COMMENCER \t le nombre a deviner est :%d\n",random_number);
                            sock_err = send(csock,"LET'S START!\n", 14, 0);

                        
                        while(recv(csock,input, BUFLEN,0)!=SOCKET_ERROR){

                            // printf("Client's Guess: %s\n",input);
                            int number=atoi(input);//conversion en format int 
                             printf("client's Guess:%d \n",number);
                            // fflush(stdout);
                            if(number>random_number) {
                                 sock_err = send(csock,case1, BUFLEN, 0);
                                  } 

                            else if (number<random_number)
                            {
                                
                                sock_err = send(csock,case2, BUFLEN, 0);
                                
                            } 
                            else {
                                
                                sock_err = send(csock,case3, BUFLEN, 0);
                                sock_err = send(csock,"LE JEU EST TERMINEE\n", BUFLEN, 0);
                                shutdown(csock, 2); 
                                printf("FIN DU JEU \n");
                            }
                            
                        }  
                        
                      } 
                    /* Il ne faut pas oublier de fermer la connexion (fermée dans les deux sens) */
                    // printf("FIN DU JEU \n");
                    // shutdown(csock, 2); 
                     

                }  
                /* Fermeture de la socket */
                    printf("Fermeture de la socket...\n");
                    closesocket(sock);
                    printf("Fermeture du serveur terminee\n");  
            }
 
            
        }
 
        #if defined (WIN32)
            WSACleanup();
        #endif
    }
 
    /* On attend que l'utilisateur tape sur une touche, puis on ferme */
    getchar();
 
    return EXIT_SUCCESS;
}