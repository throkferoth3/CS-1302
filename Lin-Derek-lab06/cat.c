#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>

#define MAXFILESIZE 1000000

/**
 * This function prints the lines of
 * a file. If no file or "-" is specified, it
 * copies what is printed in standard output.
 *
 * @param argc The number of args.
 * @param argv The args.
 * @return The exit status.
 */
int main(int argc, char* argv[]) {
    setbuf(stdout, NULL);
    if (argc > 1) {        
        int i = 0;
        for (i = 2; i <= argc; i++) {
            if (strcmp(argv[i - 1], "-") == 0) {
                char buffer[MAXFILESIZE];
                int n = 0;
                while ((n = read(STDIN_FILENO, buffer, MAXFILESIZE)) > 0) { //reading -
                    write(STDOUT_FILENO, buffer, n);
                }
            } else { // reading files
                char* filename = argv[i - 1];
                int fd = open(filename, O_RDONLY);
                char buffer[MAXFILESIZE];
                while (read(fd, buffer, 1) > 0) {
                    write(STDOUT_FILENO, buffer, 1);
                }
            }
        }
    } else { // no files specified
        char buffer[MAXFILESIZE];
        int n = 0;
        while ((n = read(STDIN_FILENO, buffer, MAXFILESIZE)) > 0) {
            write(STDOUT_FILENO, buffer, n);
        }
    }
}
