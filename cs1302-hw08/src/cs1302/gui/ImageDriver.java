package cs1302.gui;

import javafx.application.Application;

/**
 * Driver class that runs the application.
 *
 */
public class ImageDriver {

    /**
     * Main method that launches the application.
     *
     * @param args Arguments from the command line.
     */
    public static void main(String[] args) {
        try {
            Application.launch(ImageApp.class, args);
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Likely due to X11 timeout. Logout and log back in...");
            System.exit(1);
        } // try
    } // main

} // ImageDriver
