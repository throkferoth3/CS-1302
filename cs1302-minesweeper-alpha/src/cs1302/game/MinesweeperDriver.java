package cs1302.game;

import java.util.Scanner;

/**
 * Class that runs the contents of {@link cs1302.game.Minesweepergame}.
 */
public class MinesweeperDriver {

    /**
     * Gets the seed file from args and launches the minesweeper game.
     *
     * @param args the argument from the command line.
     */
    public static void main(String[] args) {

        String seedPath = args[0];
        if (args.length != 1) { // checks to make sure there is only 1 args
            System.err.println("");
            System.err.println("Usage: MinesweeperDriver SEED_FILE_PATH ");
            System.exit(1);
        }

        Scanner stdIn = new Scanner(System.in);
        MinesweeperGame game = new MinesweeperGame(stdIn, seedPath);
        game.play();

    }

}
