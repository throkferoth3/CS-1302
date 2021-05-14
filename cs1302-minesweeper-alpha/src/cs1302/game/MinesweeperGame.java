package cs1302.game;

import java.lang.ArrayIndexOutOfBoundsException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * A class that holds the information to run a modified version of <code>minesweeper</code>.
 */
public class MinesweeperGame {

    private final Scanner stdIn;
    private int row;
    private int col;
    private double score = 0;
    private int numMines;
    private String[][] visual;
    private int[][] actual;
    private int roundCounter = 0;
    private boolean loopRunning = true;
    private boolean noFogRan = false;
    private boolean endProgram = false;

    /**
     * Constructor for the class.
     * Interprets and intializes the values held in the seed file.
     *
     * @param stdIn the standard input
     * @param seedPath String object that represents the path to the seed file
     */
    public MinesweeperGame(Scanner stdIn, String seedPath) {
        this.stdIn = stdIn;
        int mineX;
        int mineY;

        try {
            File file = new File(seedPath);
            Scanner seedParse = new Scanner(file);
            row = (Integer.parseInt(seedParse.next().trim()));
            col = (Integer.parseInt(seedParse.next().trim()));
            visual = new String[row][col];
            actual = new int[row][col];

            if (row > 10 || col > 10) {
                System.err.println("");
                System.err.print("Seed File Malformed Error: Cannot create a mine");
                System.err.println(" field with that many rows and/or columns!");
                System.exit(3);
                endProgram = true;
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    visual[i][j] = (" ");
                }
            }

            for (int i = 0; i < row; i++) { // 0 means no bomb
                for (int j = 0; j < col; j++) {
                    actual[i][j] = 0;
                }
            }

            numMines = (Integer.parseInt(seedParse.next().trim()));

            for (int i = 0; i < numMines; i++) { // placing bombs
                mineX = (Integer.parseInt(seedParse.next().trim()));
                mineY = (Integer.parseInt(seedParse.next().trim()));
                actual[mineX][mineY] = 1;
            }

        } catch (FileNotFoundException fne) {
            System.err.println();
            System.err.println("Seed File Not Found Error: File Not Found");
            System.exit(2);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("");
            System.err.print("Seed File Malformed Error: Cannot create a mine");
            System.err.println(" field with that many rows and/or columns!");
            System.exit(3);
            endProgram = true;
        }

    }

    /**
     * Prints the welcome banner using {@link cs1302.game.Printer}.
     */
    public void printWelcome() {
        try {
            String filename = "src/cs1302/game/Welcome.txt";
            File file = new File(filename);
            Printer.printFileLines(file);
            System.out.println("");
        } catch (FileNotFoundException fne) {
            System.err.println();
            System.err.println("File Not Found");
        }
    }

    /**
     * Prints the minefield named {@code visual} that the player sees and interacts with.
     */
    public void printMineField() {

        for (int i = 0; i < row; i++) {
            System.out.print(" " + i + " |");
            for (int j = 0; j < col; j++) {
                System.out.print(" " + visual[i][j] + " " + "|");
            }
            System.out.println("");
        }
        System.out.print("     0"); // printing bottom row of numbers
        for (int k = 1; k < col; k++) {
            System.out.print("   " + (k));
        }
        System.out.println("");
        System.out.println("");
    }

    /**
     * Prompts the user for a command and interprets it.
     */
    public void promptUser() {
        System.out.print("minesweeper-alpha: ");
        String fullCommand = stdIn.nextLine();
        int tokenCounter = 0;
        Scanner commandScan = new Scanner(fullCommand);
        Scanner commandScanTester = new Scanner(fullCommand);
        while (commandScanTester.hasNext()) { //Checking if input has the correct number of tokens
            String command2 = commandScanTester.next();
            tokenCounter += 1;
        }
        if (tokenCounter <= 3) {
            try {
                String command = commandScan.next();
                System.out.println("");
                if (command.equals("quit") || command.equals("q")) {
                    System.out.println("Quitting the game...");
                    System.out.println("Bye!");
                    loopRunning = false;
                } else if (command.equals("reveal") || command.equals("r")) {
                    int command1 = (Integer.parseInt(commandScan.next().trim()));
                    int command2 = (Integer.parseInt(commandScan.next().trim()));
                    if (actual[command1][command2] == 1) {
                        this.printLoss();
                    } else {
                        this.detectMine(command1, command2);
                    }
                } else if (command.equals("mark") || command.equals("m")) {
                    int command1 = (Integer.parseInt(commandScan.next().trim()));
                    int command2 = (Integer.parseInt(commandScan.next().trim()));
                    this.mark(command1, command2);
                } else if (command.equals("guess") || command.equals("g")) {
                    int command1 = (Integer.parseInt(commandScan.next().trim()));
                    int command2 = (Integer.parseInt(commandScan.next().trim()));
                    this.guess(command1, command2);
                } else if (command.equals("nofog")) {
                    this.noFog();
                } else if (command.equals("help") || command.equals("h")) {
                    this.help();
                } else {
                    System.err.println("");
                    System.err.println("Invalid Command: Command not recognized!");
                    System.err.println("");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Invalid Command: " + e.getMessage());
                System.err.println("");
            }
        } else {
            System.err.println("");
            System.err.println("Invalid Command: Command not recognized!");
            System.err.println("");
        }
    }

    /**
     * Checks to see if the player meets the requirements to win.
     *
     * @return boolean returns true if the player won and false if otherwise.
     */
    public boolean isWon() {
        boolean won = true;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((actual[i][j] == 1) && (!visual[i][j].equals("F"))) { // Are bombs marked
                    won = false;
                }

                if ((actual[i][j] == 0) &&
                    (visual[i][j].equals("?") || visual[i][j].equals("F") ||
                    visual[i][j].equals(" "))) { //Are all other spaces revealed
                    won = false;
                }
            }
        }
        return won;
    }

    /**
     * Prints the dog image using {@link cs1302.game.Printer}.
     */
    public void printWin() {
        try {
            String filename = "src/cs1302/game/Doge.txt";
            File file = new File(filename);
            Printer.printFileLines(file);
        } catch (FileNotFoundException fne) {
            System.err.println("File Not Found");
        }
        loopRunning = false;
    }

    /**
     * Prints the game over image using {@link cs1302.game.Printer}.
     */
    public void printLoss() {
        try {
            String filename = "src/cs1302/game/GameOver.txt";
            File file = new File(filename);
            Printer.printFileLines(file);
            System.out.println();
        } catch (FileNotFoundException fne) {
            System.err.println();
            System.err.println("File Not Found");
        }
        loopRunning = false;
    }

    /**
     * Checks to see if the give coordinates exist.
     *
     * @return boolean returns true if in bounds and false if otherwise.
     * @param x  the row number.
     * @param y  the column number.
     */
    public boolean isInBounds(int x, int y) {
        try {
            visual[x][y] = visual[x][y];
            actual[x][y] = actual[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    /**
     * Detects the number of mines in the surrounding squares.
     *
     * @return returns the number of mines.
     * @param x the row number.
     * @param y the column number.
     */
    public int detectMine(int x, int y) {
        int minesDetected = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (this.isInBounds(x + i, y + j)) {
                    if (actual[x + i][y + j] == 1) {
                        minesDetected += 1;
                    }
                }
            }
        }
        roundCounter += 1;
        String minesDetectedString = "";
        minesDetectedString += minesDetected;
        visual[x][y] = minesDetectedString;
        return minesDetected;
    }

    /**
     * Marks the given coordinates on the visual grid with F.
     *
     * @param x the row number
     * @param y the column number
     */
    public void mark(int x, int y) {
        visual[x][y] = "F";
        roundCounter += 1;
    }

    /**
     * Marks the given coordinates on the visual grid with ?.
     *
     * @param x the row number.
     * @param y the column number.
     */
    public void guess(int x, int y) {
        visual[x][y] = "?";
        roundCounter += 1;
    }

    /**
     * Lists the potential actions the player may take excluding nofog.
     */
    public void help() {
        System.out.println("Commands Available...");
        System.out.println(" - Reveal: r/reveal row col");
        System.out.println(" -   Mark: m/mark   row col");
        System.out.println(" -  Guess: g/guess  row col");
        System.out.println(" -   Help: h/help");
        System.out.println(" -   Quit: q/quit");
        System.out.println();
        roundCounter += 1;
    }

    /**
     * Reveals the location of all mines.
     */
    public void noFog() {
        for (int i = 0; i < row; i++) {
            System.out.print(" " + i + " |");
            for (int j = 0; j < col; j++) {
                if (actual[i][j] == 1) {
                    System.out.print("<" + visual[i][j] + ">" + "|");
                } else {
                    System.out.print(" " + visual[i][j] + " " + "|");
                }
            }
            System.out.println("");
        }
        System.out.print("     0");
        for (int k = 1; k < col; k++) {
            System.out.print("   " + (k));
        }
        System.out.println("");
        roundCounter += 1;
        noFogRan = true;
    }

    /**
     * Calculates the players score.
     *
     * @return returns calculated score.
     */
    public double calculateScore() {
        double rowDouble = row;
        double colDouble = col;
        double roundCounterDouble = roundCounter;
        score = (100.0 * rowDouble * colDouble) / (roundCounterDouble);
        return score;
    }

    /**
     * Puts together the other methods of the class to run a the game loop
     * that stops once the player meets the requirements to stop it.
     */
    public void play() {
        if (endProgram == false) {
            this.printWelcome();
            while ((loopRunning == true) && (this.isWon() == false)) {
                System.out.println(" Rounds Completed: " + roundCounter);
                System.out.println("");
                if (noFogRan == true) { // since no fog prints a grid it skips the print
                    noFogRan = false;
                } else {
                    this.printMineField();
                }
                this.promptUser();
            }

            if (this.isWon() == true) {
                this.printWin();
                System.out.println(" " + String.format("%.2f", this.calculateScore()));
            }
        }
    }
}
