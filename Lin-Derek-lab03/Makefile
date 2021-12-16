CC = g++
DEBUG = -g
CFLAGS = -Wall -pedantic-errors -c $(DEBUG)
LFLAGS = -Wall -pedantic-errors $(DEBUG)

compile: main.o arrayutil.o
	$(CC) $(LFLAGS) -o arrayutil main.o arrayutil.o

main.o: main.cpp arrayutil.h
	$(CC) $(CFLAGS) main.cpp

arrayutil.o: arrayutil.cpp arrayutil.h
	$(CC) $(CFLAGS) arrayutil.cpp

run: compile
	./arrayutil

clean:
	rm -f arrayutil
	rm -f *.o
