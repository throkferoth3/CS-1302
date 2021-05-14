package cs1302.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.TilePane;
import cs1302.gui.ImageLoader;

/**
 * A basic JavaFX 8 program which takes a user specified URL and loads it.
 * into an {@code ImageView}.
 *
 */
public class ImageApp extends Application {

    Stage stage;
    Scene scene;

    /** The container for the url textfield and the load image button. */
    TilePane tilePane;

    /** A default image which loads when the application starts. */
    private static final String DEFAULT_IMG =
        "http://cobweb.cs.uga.edu/~mec/cs1302/gui/default.png";

    /** Default height and width for Images. */
    private static final int DEF_HEIGHT = 500;
    private static final int DEF_WIDTH = 500;

    /**
     * The entry point for our image viewer application.
     *
     * @param stage A reference to the stage object (window) created by the system.
     */
    public void start(Stage stage) {
        this.stage = stage;

        tilePane = new TilePane(10, 0);
        tilePane.setPrefColumns(2);

        for (int i = 0; i < 8; i++) {
            tilePane.getChildren().add(new ImageLoader());
        }

        scene = new Scene(tilePane);

        stage.setScene(scene);
        stage.setTitle("1302 Image Viewer!");
        stage.sizeToScene();
        stage.show();

    } // start

} // ImageApp
