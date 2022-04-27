#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <unistd.h>
#include <sys/wait.h>

#define LINELEN (80)
#define WRITE_END (1)
#define READ_END (0)

char *mygets(char *buf, int len);
int mygeti();
/// matan bardugo - 207489774 ////////
int main(int argc, char *argv[])
{
	char *cmpstr[] = {"lexcmp", "lencmp"};
	int veclen = sizeof(cmpstr)/sizeof(char *);
	char str1[LINELEN + 1];
	char str2[LINELEN + 1];
	int index;
	int pfd1[2]; //lexcmp pipe	
	int pfd2[2]; //lencmp pipe
	int pfd3[2]; //lexcmp pipe	
	int pfd4[2]; //lencmp pipe
	int pid2,pid1;
	///////////////create pipes////////////
	if(pipe(pfd1)==-1){ printf("Pipe failed"); return -2;}
	if(pipe(pfd2)==-1){ printf("Pipe failed"); return -2;}
	if(pipe(pfd3)==-1){ printf("Pipe failed"); return -2;}
	if(pipe(pfd4)==-1){ printf("Pipe failed"); return -2;}
	///////////create child 1/////////////
	pid1 = fork();
	switch (pid1)
	{
	case -1:
		return -2;

	case 0:
		if(dup2(pfd1[READ_END],STDIN_FILENO) == -1) // input goes to read end of pipe1
				return -2;
		if(close(pfd1[WRITE_END]) == -1)//close write end of pipe1
			return -2;
		if(dup2(pfd3[WRITE_END],STDOUT_FILENO) == -1) // output goes to write end of pipe3
			return -2;
		if(close(pfd3[READ_END]) == -1)//close read end of pipe3
			return -2;
		if(execlp("./loopcmp","./loopcmp","lexcmp",(char*)NULL) < 0)	//execute loopcmp with lexcmp
			return -2;	
	default:
	//////////create child 2/////////////
		pid2=fork();
		switch(pid2){
		case -1:
			return -2;

		case 0:
			if(dup2(pfd2[READ_END],STDIN_FILENO) == -1) // input goes to read end of pipe2
				return -2;
			if(close(pfd2[WRITE_END]) == -1)//close write end of pipe2
				return -2;
			if(dup2(pfd4[WRITE_END],STDOUT_FILENO) == -1) // output goes to write end of pipe4
				return -2;
			if(close(pfd4[READ_END]) == -1)//close read end of pipe4
				return -2;
			if(execlp("./loopcmp","./loopcmp","lencmp",(char*)NULL) < 0) //execute loopcmp with lencmp
				return -2;
		default:
			if(close(pfd1[READ_END]) == -1)// close read end of pipe 1
				return -2;
			if(close(pfd2[READ_END]) == -1)// close read end of pipe 2
				return -2;
			if(close(pfd3[WRITE_END]) == -1)// close write end of pipe 3
				return -2;
			if(close(pfd4[WRITE_END]) == -1)// close write end of pipe 4
				return -2;
		}
	}	
	
	
	while (1)
	{
		printf("Please enter first string:\n");
		if (mygets(str1, LINELEN) == NULL)
			break;
		printf("Please enter second string:\n");
		if (mygets(str2, LINELEN) == NULL)
			break;
		do {
			printf("Please choose:\n");
			for (int i=0 ; i < veclen ; i++)
				printf("%d - %s\n", i, cmpstr[i]);
			index = mygeti();
		} while ((index < 0) || (index >= veclen));
		printf("%s(%s, %s) == ", cmpstr[index],str1, str2); // the result output
		strcat(str1,"\n");
		strcat(str2,"\n");
		fflush(stdout); //clean the stdout
		
		char res[LINELEN];
		if(index == 0){ //for child lexcmp
			if(write(pfd1[WRITE_END],str1,strlen(str1))<0){ // write string to pipe 1
					return -2;
			}
			if(write(pfd1[WRITE_END],str2,strlen(str2))<0){ // write string to pipe 1
					return -2;
			}
			if(read(pfd3[READ_END],res,LINELEN) == 0){ //read result
					return -2;
			}
		}
		else{
			if(write(pfd2[WRITE_END],str1,strlen(str1))<0){ // write string to pipe 2
					return -2;
			}
			if(write(pfd2[WRITE_END],str2,strlen(str2))<0){ // write string to pipe 2
					return -2;
			}
			if(read(pfd4[READ_END],res,LINELEN) == 0){//read result
				return -2;
			}
			
		}
		printf("%s",res);		// print result	
	}
			if(close(pfd1[WRITE_END]) == -1)// close read end of pipe 1
				return -2;
		if(close(pfd2[WRITE_END]) == -1)// close read end of pipe 2
				return -2;
		if(close(pfd3[READ_END]) == -1)// close write end of pipe 3
				return -2;
		if(close(pfd4[READ_END]) == -1)// close write end of pipe 4
				return -2;
		kill(pid1,SIGKILL);
		kill(pid2,SIGKILL);
	return 0;
}

char *mygets(char *buf, int len)
{
	char *retval;

	retval = fgets(buf, len, stdin);
	buf[len] = '\0';
	if (buf[strlen(buf) - 1] == 10) /* trim \r */
		buf[strlen(buf) - 1] = '\0';
	else if (retval) 
		while (getchar() != '\n'); /* get to eol */
	return retval;
}

int mygeti()
{
	int ch;
	int retval=0;

	while(isspace(ch=getchar()));
	while(isdigit(ch))
	{
		retval = retval * 10 + ch - '0';
		ch = getchar();
	}
	while (ch != '\n')
		ch = getchar();
	return retval;
}
