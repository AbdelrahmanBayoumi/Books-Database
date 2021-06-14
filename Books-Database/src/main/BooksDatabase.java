package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utilities.DatabaseUtil;
import utilities.Logger;

public class BooksDatabase extends Application {

    private static final String CLASS_NAME = BooksDatabase.class.getName();

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/homepage/Homepage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.png")));
            stage.show();
            stage.setOnCloseRequest((event) -> {
                Logger.log(Logger.Level.INFO, "BooksDatabase Closed");
            });
        } catch (Exception e) {
            Logger.log(Logger.Level.ERROR, "Exception[" + e + "] in " + CLASS_NAME + ".start(Stage:" + stage + ")");
        }
    }

    public static void main(String[] args) {
        try {
            // init logger
            Logger.init();
            // init Database
            DatabaseUtil.init();
            // Log StartTime
            Logger.log(Logger.Level.INFO, "BooksDatabase launched");
            // JavaFX
            launch(args);
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".main()");
        }
    }

}
