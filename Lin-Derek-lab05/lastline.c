#include <stdio.h>
#include <stdlib.h>

/**
 * This is a main method that finds the last line
 * in a text file and prints it.
 *
 * @param argc This is the number of args passed.
 * @param argv Array that points to each arg.
 * @return exit status.
 */
int main(int argc, char *argv[])
{
    //opening file
    FILE *fp;
	char* filename = argv[1];
	fp = fopen(filename, "r");
    if (fp == NULL) perror("open");

	char buffer[100];

    //getting the last line
    while (!feof(fp)) {
        fgets(buffer, 100, fp);
    }

    printf(buffer);

	return EXIT_SUCCESS;
} // main
