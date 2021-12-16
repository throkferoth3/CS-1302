#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * This is a main method that finds the longest line
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
    int longestLength = 0;

    //finding longest line
    while (!feof(fp)) {
        fgets(buffer, 100, fp);
        if (strcspn(buffer, "\n") > longestLength) {
            longestLength = strcspn(buffer, "\n");
        }
    }

    printf("%d\n", longestLength);

	return EXIT_SUCCESS;
} // main
