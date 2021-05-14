package cs1302.gallery;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.lang.Thread;
import java.lang.Runnable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.net.URL;
import java.net.URLEncoder;
import java.io.InputStreamReader;
import com.google.gson.*;
import java.net.MalformedURLException;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.UnsupportedEncodingException;
import javafx.scene.text.TextFlow;
import javafx.application.Platform;
import java.util.Scanner;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.Node;
import java.util.Random;
import javafx.event.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.lang.ArrayIndexOutOfBoundsException;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

/**
 * Represents an iTunes GalleryApp.
 *
 */
public class GalleryApp extends Application {

    /** Declaring class variables. **/
    private Stage stage;
    private TextField urlBar;
    private Button searchButton;
    private TilePane tile;
    private Boolean firstTilePane = true;
    private String[] cleanArray = new String[0];
    private ProgressBar progressBar;
    private Button ppButton;
    private Timeline timeline;
    private KeyFrame keyFrame;
    private int cleanArraySize = 0;

    /**
     * This method intializes many of the instance variables and sets the scene and stage.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        VBox pane = new VBox();
        Scene scene = new Scene(pane);
        MenuBar menuBar1 = createMenuBar();
        HBox controlBox = createControlBox();
        tile = new TilePane();
        progressBar = new ProgressBar(0);
        Platform.runLater(() -> progressBar.setProgress(0));
        runNow(() -> {
            updateTilePane();
            Runnable r = () -> {
                tile.getChildren().clear();
            };
            Platform.runLater(r);
            Platform.runLater(() -> {
                sceneUpdate(cleanArray);
            });
        });
        pane.getChildren().addAll(menuBar1, controlBox, tile, progressBar);

        stage.setMaxWidth(1280);
        stage.setMaxHeight(720);
        stage.setTitle("GalleryApp!");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        tileFlipper();
    } // start

    /**
     * This method runs the target on a new thread.
     *
     * @param target the runnable instance.
     */
    public static void runNow(Runnable target) {
        Thread t = new Thread(target);
        t.setDaemon(true);
        t.start();
    }

    /**
     * This method creates the menu.
     *
     * @return MenuBar this creates the file tab.
     */
    public MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        final Menu menu1 = new Menu("File");
        menuBar.getMenus().add(menu1);
        MenuItem menu12 = new MenuItem("Exit");
        menu12.setOnAction(e -> this.stage.close());
        menu1.getItems().add(menu12);

        return menuBar;
    }

    /**
     * This method creates the control box where the user interacts with the program.
     *
     * @return HBox the runnable instance.
     */
    public HBox createControlBox() {
        HBox controlBox = new HBox(5);
        ppButton = new Button("Pause");
        ppButton.setOnAction(e -> {
            if (ppButton.getText().equals("Pause")) {
                ppButton.setText("Play");
                pressTileFlipper();
            } else {
                ppButton.setText("Pause");
                pressTileFlipper();
            }
        });
        Text searchQuery = new Text("Search Query:");
        urlBar = new TextField("rock");
        searchButton = new Button("Update Images");
        searchButton.setOnAction(e -> {
            searchButton.setDisable(true);
            Platform.runLater(() -> progressBar.setProgress(0));
            runNow(() -> {
                updateTilePane();
                Runnable r = () -> {
                    tile.getChildren().clear();
                };
                Platform.runLater(r);
                Platform.runLater(() -> {
                    sceneUpdate(cleanArray);
                });
            });
            searchButton.setDisable(false);
        });
        controlBox.setAlignment(Pos.CENTER_LEFT); // Fixing Formatting
        controlBox.setPadding(new Insets(7));
        HBox.setHgrow(urlBar, Priority.ALWAYS);
        controlBox.getChildren().addAll(ppButton, searchQuery, urlBar, searchButton);

        return controlBox;
    }

    /**
     * This method downloads the images of the new search and stores them.
     *
     */
    public void updateTilePane() {
        try {
            String sUrl = ("https://" + "itunes.apple.com/search?term=" +
                           URLEncoder.encode(urlBar.getText(), "UTF-8") + "&limit=200");
            URL url = new URL(sUrl);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            JsonElement je = JsonParser.parseReader(reader);
            JsonObject root = je.getAsJsonObject();
            JsonArray results = root.getAsJsonArray("results");          // "results" array
            int numResults = results.size();                             // "results" array size
            tile.setPrefColumns(5);
            String[] cleanArrayTester = new String[numResults];
            int cleanArrayTesterSize = 0;
            for (int i = 0; i < numResults; i++) {
                boolean isDuplicate = false;
                JsonObject result = results.get(i).getAsJsonObject();
                JsonElement artworkUrl100 = result.get("artworkUrl100"); // artworkUrl100 member
                if (artworkUrl100 != null) {
                    for (int j = 0; j <= i; j++) {
                        if (artworkUrl100.getAsString().equals(cleanArrayTester[j])) {
                            isDuplicate = true;
                            j = i;
                        }
                    }
                    if (!isDuplicate) {
                        for (int k = 0; k < numResults; k++) {
                            if (cleanArrayTester[k] == null) {
                                cleanArrayTester[k] = artworkUrl100.getAsString();
                                cleanArrayTesterSize += 1;
                                k = 200;
                            }
                        }
                    }
                }
                setProgress((double) (i) / numResults);
            }
            if (cleanArrayTesterSize < 21) { //Errors
                Platform.runLater(() -> {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setResizable(true);
                    a.onShownProperty().addListener(e -> a.setResizable(false));
                    a.setTitle("Not Enough Matches");
                    a.setContentText("Less than 21 non duplicate images found");
                    a.setHeaderText("Not Enough Matches");
                    a.showAndWait();
                });
            } else {
                cleanArray = new String[numResults];
                cleanArraySize = cleanArrayTesterSize;
                for (int i = 0; i < cleanArrayTesterSize; i++) {
                    cleanArray[i] = cleanArrayTester[i];
                }
                setProgress(1);
            }
        } catch (MalformedURLException mul) {
            System.err.println(mul.getMessage());
        } catch (IOException io) {
            System.err.println(io.getMessage());
        } // catch (InterruptedException ie) {
    }

    /**
     * This method places the new searches images in the gallery.
     *
     * @param cleanArray this is the array that this added to the scene.
     */
    public void sceneUpdate(String[] cleanArray) {
        for (int i = 0; i < 20; i++) {
            if (cleanArray[i] != null) {
                ImageView tempImage = new ImageView();
                tempImage.setImage(new Image(cleanArray[i]));
                tile.getChildren().add(tempImage);
            }
        }
        stage.sizeToScene();
    }

    /**
     * This method updates the progress bar.
     *
     * @param progress This is the amount that the bar is set to.
     */
    private void setProgress(final double progress) {
        Platform.runLater(() -> {
            progressBar.setProgress(progress);
        });
    } // setProgress

    /**
     * This method controls when the tiles are flipped.
     *
     */
    private void pressTileFlipper() {
        if (ppButton.getText().equalsIgnoreCase("Pause") && cleanArraySize >= 20) {
            timeline.play();
        } else {
            timeline.stop();
        }
    }

    /**
     * This method is how the program flips the panels.
     *
     */
    private void tileFlipper() {
        keyFrame = new KeyFrame(Duration.seconds(2), e -> {
            runNow(() -> {
                int g = (int) (Math.random() * (20));
                int l = (int) (Math.random() * (cleanArraySize - 21) + 21);
                String gString = cleanArray[g];
                String lString = cleanArray[l];
                cleanArray[g] = lString;
                cleanArray[l] = gString;
            });
            Platform.runLater(() -> tile.getChildren().clear());
            Platform.runLater(() -> {
                sceneUpdate(cleanArray);
            });
        }); //
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);
        pressTileFlipper();
    }

} // GalleryApp
