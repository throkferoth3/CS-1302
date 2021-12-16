CC = g++
LFLAGS = -Wall -pedantic-errors
CFLAGS = $(LFLAGS) -c

compile: Driver.o GradeBook.o
	$(CC) $(LFLAGS) -o gradebook Driver.o GradeBook.o

Driver.o: Driver.cpp GradeBook.h
	$(CC) $(CFLAGS) Driver.cpp

GradeBook.o: GradeBook.cpp GradeBook.h
	$(CC) $(CFLAGS) GradeBook.cpp

run: compile
	./gradebook

clean:
	rm -f gradebook
	rm -f *.o
