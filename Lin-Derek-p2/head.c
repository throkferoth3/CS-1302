#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>

#define MAXFILESIZE 1000000

/**
 * This function prints the first specified lines of
 * a file. If no file or "-" is specified, it
 * copies what is printed in standard output.
 *
 * @param argc The number of args.
 * @param argv The args.
 * @return The exit status.
 */
int main(int argc, char* argv[]) {
    setbuf(stdout, NULL);
    int argNum = 0;
    if (argc > 1) {
        if (strcmp(argv[1], "-n") == 0) { //if lines are specified
            argNum = strtol(argv[2], NULL, 10);
            int i;
            for (i = 4; i <= argc; i++) { //if printing stdin
                if (strcmp(argv[i - 1], "-") == 0) {
                    if (argc > 4) {
                        write(STDOUT_FILENO, "==> standard input <==",
                        strlen("==> standard input <=="));
                        write(STDOUT_FILENO, "\n", 1);
                    }
                    char buffer[MAXFILESIZE];
                    int n = 0;
                    while ((n = read(STDIN_FILENO, buffer, MAXFILESIZE)) > 0) {
                        write(STDOUT_FILENO, buffer, n);
                    }
                } else { //if files are specified
                    if (argc > 4) {
                        write(STDOUT_FILENO, "==> ", strlen("==> "));
                        write(STDOUT_FILENO, argv[i - 1], strlen(argv[i - 1]));
                        write(STDOUT_FILENO, " <==", strlen(" <=="));
                        write(STDOUT_FILENO, "\n", 1);
                    }

                    char* filename = argv[i - 1];
                    int fd = open(filename, O_RDONLY);
                    char buffer[MAXFILESIZE];
                    int j = 0;
                    while (j < argNum) {
                        int k = 0;
                        for (k = 0; k < MAXFILESIZE; k++) {
                            if (read(fd, buffer, 1) > 0) {
                                write(STDOUT_FILENO, buffer, 1);
                            } else {
                                k = MAXFILESIZE;
                                j = argNum;
                            }
                            if (strcmp(buffer, "\n") == 0) {
                                k = MAXFILESIZE;
                            }
                        }
                        j++;
                    }
                }
                if (i >= 4 && i != argc) {
                    write(STDOUT_FILENO, "\n", 1);
                }
            }
            if (argc == 3) { //if there are args but no files
                char buffer[MAXFILESIZE];
                int n = 0;
                while ((n = read(STDIN_FILENO, buffer, MAXFILESIZE)) > 0) {
                    if (strtol(argv[2], NULL, 10) >= 0) {
                        write(STDOUT_FILENO, buffer, n);
                    }
                }
            }
        } else if (strcmp(argv[1], "-c") == 0) {
            argNum = strtol(argv[2], NULL, 10);
            int i;
            for (i = 4; i <= argc; i++) {
                if (strcmp(argv[i - 1], "-") == 0) {
                    if (argc > 4) {
                        write(STDOUT_FILENO, "==> standard input <==",
                        strlen("==> standard input <=="));
                        write(STDOUT_FILENO, "\n", 1);
                    }
                    char buffer[MAXFILESIZE];
                    int n = 0;
                    while ((n = read(STDIN_FILENO, buffer, MAXFILESIZE)) > 0) { //reading -
                        write(STDOUT_FILENO, buffer, argNum);
                        memset(buffer, 0, 255);
                    }
                } else { //reading head char from files
                    if (argc > 4) {
                        write(STDOUT_FILENO, "==> ", strlen("==> "));
                        write(STDOUT_FILENO, argv[i - 1], strlen(argv[i - 1]));
                        write(STDOUT_FILENO, " <==", strlen(" <=="));
                        write(STDOUT_FILENO, "\n", 1);
                    }

                    char* filename = argv[i - 1];
                    int fd = open(filename, O_RDONLY);
                    char buffer[MAXFILESIZE];
                    int j = 0;
                    while (j < argNum) {
                        if (read(fd, buffer, 1) > 0) {
                            write(STDOUT_FILENO, buffer, 1);
                        } else {
                            j = argNum;
                        }
                        j++;
                    }
                }
                if (i >= 4 && i != argc) {
                    write(STDOUT_FILENO, "\n", 1);
                }
            }
            if (argc == 3) { //options but no file
                char buffer[MAXFILESIZE];
                int n = 0;
                while ((n = read(STDIN_FILENO, buffer, MAXFILESIZE)) > 0) {
                    write(STDOUT_FILENO, buffer, argNum);
                    memset(buffer, 0, 255);
                }
            }
        } else { // no options
            argNum = 10;
            int i = 0;
            for (i = 2; i <= argc; i++) {
                if (argc > 2) {
                    write(STDOUT_FILENO, "==> ", strlen("==> "));
                    write(STDOUT_FILENO, argv[i - 1], strlen(argv[i - 1]));
                    write(STDOUT_FILENO, " <==", strlen(" <=="));
                    write(STDOUT_FILENO, "\n", 1);
                }

                char* filename = argv[i - 1];
                int fd = open(filename, O_RDONLY);
                char buffer[MAXFILESIZE];
                int j = 0;
                while (j < argNum) {
                    int k = 0;
                    for (k = 0; k < MAXFILESIZE; k++) {
                        if (read(fd, buffer, 1) > 0) {
                            write(STDOUT_FILENO, buffer, 1);
                        } else {
                            k = 1025;
                        }
                        if (strcmp(buffer, "\n") == 0) {
                            k = 1025;
                        }
                    }
                    j++;
                }
                if (i >= 2 && i != argc) {
                    write(STDOUT_FILENO, "\n", 1);
                }
            }
        }
    } else { //no options or args
        argNum = 10;
        char buffer[MAXFILESIZE];
        int n = 0;
        while ((n = read(STDIN_FILENO, buffer, MAXFILESIZE)) > 0) {
            write(STDOUT_FILENO, buffer, n);
        }
    }
}
