package cs1302.gui;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;

/**
 * Class that creates a custom component.
 *
 */
public class ImageLoader extends VBox {
    private static final String DEFAULT_IMG =
        "http://cobweb.cs.uga.edu/~mec/cs1302/gui/default.png";

    /** Default height and width for Images. */
    private static final int DEF_HEIGHT = 500;
    private static final int DEF_WIDTH = 500;

    //pieces of the scene graph
    protected VBox vBox;
    protected HBox urlLayer;
    protected ImageView imgView;
    protected Button loadImage;
    protected TextField urlField;

    /** Constructor that makes the component. */
    public ImageLoader() {
        super();
        vBox = new VBox();
        urlLayer = new HBox(10);
        Image img = new Image(DEFAULT_IMG, DEF_HEIGHT, DEF_WIDTH, false, false);
        imgView = new ImageView(img);
        urlField = new TextField("https://");
        loadImage = new Button("Load");

        imgView.setPreserveRatio(true);
        loadImage.setOnAction(this::loadImage);
        HBox.setHgrow(urlField, Priority.ALWAYS);

        this.getChildren().addAll(urlLayer, imgView);
        urlLayer.getChildren().addAll(urlField, loadImage);
    }

    /**
     * Method that loads the image supplied when the load button is clicked.
     *
     * @param e source event
     */
    private void loadImage(ActionEvent e) {

        try {
            Image newImg = new Image(urlField.getText(), DEF_HEIGHT, DEF_WIDTH, false, false);
            imgView.setImage(newImg);
        } catch (IllegalArgumentException iae) {
            System.out.println("The supplied URL is invalid");
        } // try

    } // loadImage
}
