CC = gcc
OBJS = nicecmp.o
LABEL = nicecmp
EXEC = nicecmp
DEBUG = -g
COMP = -std=c99 -Wall -Werror $(DEBUG)

all : $(LABEL)

nicecmp: nicecmp.c
	$(CC) -o nicecmp nicecmp.c

clean: 
	rm -rf $(OBJS) $(EXEC)
