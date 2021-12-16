CC = g++
LFLAGS = -Wall -pedantic-errors
CFLAGS = $(LFLAGS) -c

compile: p1.o Matrix.o
	$(CC) $(LFLAGS) -o p1 p1.o Matrix.o

run: compile
	./p1

clean:
	rm -f p1
	rm -f *.o
