#include <unistd.h>
#include <string.h>

/**
 * This function prints the environment variables.
 *
 * @param argc The number of args.
 * @param argv The args.
 * @param envp The environment variables.
 * @return The exit status.
 */
int main (int argc, char* argv[], char* envp[]) {
    int i = 0;
	for (i = 0; envp[i] != NULL; i++) {
        write(STDOUT_FILENO, envp[i], strlen(envp[i]));
        write(STDOUT_FILENO, "\n", 1);
    }
}
