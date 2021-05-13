package cs1302.hw09;

import java.util.Scanner;

/**
 * A Tic-Tac-Toe Solver class.
 */
public class TTTSolver {

    /**
     * The entry point for the program.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter an initial board state " +
                           "using 9 consecutive characters. Valid "  +
                           "characters are X, O, and -.");
        String board = promptBoard(input);
        System.out.println(TTTUtility.isCat(board));
        System.out.print("Count winning boards for which player (X or O)? ");
        char calculatePlayer = input.next().charAt(0);
        System.out.println("Ways " + calculatePlayer
                           + " can win: " + countAllWinningBoards(board, calculatePlayer));
//        printAllBoards(board);
    } // main

    /**
     * Prompt the user for a valid board configuration.
     * @param input an input scanner
     * @return the board configuration
     */
    public static String promptBoard(Scanner input) {
        String board = input.nextLine();
        while (!TTTUtility.validGame(board)) {
            System.out.println("Invalid board. Try again.");
            board = input.nextLine();
        } // while
        return board;
    } // promptBoard

    /**
     * Given an initial board state, this method prints
     * all board states that can be reached via valid
     * sequence of moves by each player. Therefore, the
     * printout includes both intermediate board states
     * as well as completed board states.
     *
     * @param board the game board
     */
    public static void printAllBoards(String board) {
        char nextLetter = TTTUtility.whoseTurn(board);
        System.out.println(board);
        for (int i = 0; i < 9; i++) {
            char[] ch = board.toCharArray();
            if (ch[i] == '-') {
                ch[i] = nextLetter;
                String str = new String(ch);
                if (TTTUtility.isCat(str) || TTTUtility.isWinner(str, 'X')
                    || TTTUtility.isWinner(str, 'O')) {
                    System.out.println(str);
                }
                if (!TTTUtility.isCat(str)) {
                    if (!TTTUtility.isWinner(str, 'X')) {
                        if (!TTTUtility.isWinner(str, 'O')) {
                            printAllBoards(str);
                        }
                    }
                }
            }
        }
        return;
    } // printAllBoards

    /**
     * This method calculates the number of winning boards for a player.
     *
     * @param board the starting board
     * @param player the player you want to calculate for
     * @return the number of winning boards
     */
    public static int countAllWinningBoards(String board, char player) {
        char nextLetter = TTTUtility.whoseTurn(board);
        int count = 0;
        if (TTTUtility.isWinner(board, player)) {
            return 1;
        }
        for (int i = 0; i < 9; i++) {
            char[] ch = board.toCharArray();
            if (ch[i] == '-') {
                ch[i] = nextLetter;
                String str = new String(ch);
                if (TTTUtility.isWinner(str, player)) {
                    count += 1;
                }
                if (!TTTUtility.isCat(str)) {
                    if (!TTTUtility.isWinner(str, 'X')) {
                        if (!TTTUtility.isWinner(str, 'O')) {
                            count = count + countAllWinningBoards(str, player);
                        }
                    }
                }
            }
        }
        return count;
    }

} // TTTSolver
