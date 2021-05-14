package cs1302.game;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * A utility class for printing lines from files using <code>Scanner</code> objects.
 */
public class Printer {

    /**
     * Prints lines using the given <code>Scanner</code> object.
     *
     * @param input  the given input scanner
     */
    public static void printLines(Scanner input) {
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (" ░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▀▀░░░░░░░░ SCORE:".equals(line)) { // bottom line of doge
                System.out.print(line);
            } else {
                System.out.println(line);
            }
        } // while
    } // printLines

    /**
     * Prints lines from the given file.
     *
     * @param file  the given file
     * @throws FileNotFoundException  if <code>file</code> is not found
     */
    public static void printFileLines(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        printLines(input);
    } // printFileLines

    /**
     * Prints lines from standard input.
     */
    public static void printStdInLines() {
        Scanner input = new Scanner(System.in);
        printLines(input);
    } // printStdInLines

} // Printer
