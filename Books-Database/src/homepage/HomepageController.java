package homepage;

import static add.AddViewController.list_Field;
import datamodels.Book;
import dialog.AlertConfirmController;
import dialog.AlertInfoController;
import dialog.EditBookController;
import dialog.EditFieldController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utilities.Logger;

public class HomepageController implements Initializable {

    private static final String CLASS_NAME = HomepageController.class.getName();
    // ====== Stage ======
    public static Stage stage_EditBook;
    public static Stage stage_EditField;
    public static Stage stage_AlertConfirm;
    public static Stage stage_AlertInfo;
    // ====== Parent ======
    public static Parent root_DefaultScreen;
    public static Parent root_AddView;
    public static Parent root_ShowView;
    public static Parent root_ShowBooks;
    public static Parent root_EditField;
    public static Parent root_EditBook;
    public static Parent root_AlertConfirm;
    public static Parent root_AlertInfo;
    @FXML
    private BorderPane BorderPane_instance;
    public static BorderPane BorderPane_Static;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loodScreens();
        BorderPane_Static = BorderPane_instance;
        BorderPane_instance.setCenter(root_DefaultScreen);
    }

    private void loodScreens() {
        try {
            root_DefaultScreen = FXMLLoader.load(getClass().getResource("/homepage/DefaultScreen.fxml"));
            root_AddView = FXMLLoader.load(getClass().getResource("/add/AddView.fxml"));
            root_ShowView = FXMLLoader.load(getClass().getResource("/show/ShowView.fxml"));
            //===========
            Image info = new Image(getClass().getResourceAsStream("/resources/infoPNG.png"));
            //===========
            root_EditField = FXMLLoader.load(getClass().getResource("/dialog/EditField.fxml"));
            Scene scene_EditField = new Scene(root_EditField);
            stage_EditField = new Stage();
            stage_EditField.setScene(scene_EditField);
            stage_EditField.setResizable(false);
            stage_EditField.setTitle("Edit");
            stage_EditField.getIcons().add(info);
            stage_EditField.initModality(Modality.APPLICATION_MODAL);
            //===========
            root_EditBook = FXMLLoader.load(getClass().getResource("/dialog/EditBook.fxml"));
            Scene scene_EditBook = new Scene(root_EditBook);
            stage_EditBook = new Stage();
            stage_EditBook.setScene(scene_EditBook);
            stage_EditBook.setResizable(false);
            stage_EditBook.setTitle("Edit");
            stage_EditBook.getIcons().add(info);
            stage_EditBook.initModality(Modality.APPLICATION_MODAL);
            //===========
            root_AlertConfirm = FXMLLoader.load(getClass().getResource("/dialog/AlertConfirm.fxml"));
            Scene scene_AlertConfirm = new Scene(root_AlertConfirm);
            stage_AlertConfirm = new Stage();
            stage_AlertConfirm.setScene(scene_AlertConfirm);
            stage_AlertConfirm.setResizable(false);
            stage_AlertConfirm.setTitle("Confirm");
            stage_AlertConfirm.getIcons().add(info);
            stage_AlertConfirm.initModality(Modality.APPLICATION_MODAL);
            //===========
            root_AlertInfo = FXMLLoader.load(getClass().getResource("/dialog/AlertInfo.fxml"));
            Scene scene_AlertInfo = new Scene(root_AlertInfo);
            stage_AlertInfo = new Stage();
            stage_AlertInfo.setScene(scene_AlertInfo);
            stage_AlertInfo.setTitle("Info");
            stage_AlertInfo.getIcons().add(info);
            stage_AlertInfo.initModality(Modality.APPLICATION_MODAL);
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".loodScreens()");
            System.exit(0);
        }
    }

    public static void EditField(String field) {
        try {
            EditFieldController.FieldNameTextField_static.setText(field);
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            stage_EditField.setX(bounds.getWidth() / 2 - (540 / 2));
            stage_EditField.setY(bounds.getHeight() / 2 - (213 / 2));
            stage_EditField.showAndWait();
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".EditField()");
        }
    }

    public static void EditBook(Book b) {
        try {
            EditBookController.Title_TextFiled_Static.setText(b.getTitle());
            EditBookController.Author_TextFiled_Static.setText(b.getAuthor());
            EditBookController.Publisher_TextFiled_Static.setText(b.getPublisher());
            EditBookController.Description_TextFiled_Static.setText(b.getDescription());
            EditBookController.Location_TextFiled_Static.setText(b.getLocation());
            EditBookController.Field_ComboBox_Static.setValue(b.getField());
            EditBookController.Field_ComboBox_Static.setItems(list_Field);
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            stage_EditBook.setX(bounds.getWidth() / 2 - (706 / 2));
            stage_EditBook.setY(bounds.getHeight() / 2 - (307 / 2));
            stage_EditBook.showAndWait();
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".EditBook(book" + b + ")");
        }
    }

    public static void AlertConfirm(String txt) {
        try {
            AlertConfirmController.Msg_Label_static.setText(txt);
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            stage_AlertConfirm.setX(bounds.getWidth() / 2 - (460 / 2));
            stage_AlertConfirm.setY(bounds.getHeight() / 2 - (199 / 2));
            stage_AlertConfirm.showAndWait();
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".AlertConfirm()");
        }
    }

    public static void AlertInfo(String txt) {
        try {
            AlertInfoController.Msg_Label_static.setText(txt);
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            stage_AlertInfo.setX(bounds.getWidth() / 2 - (460 / 2));
            stage_AlertInfo.setY(bounds.getHeight() / 2 - (199 / 2));
            stage_AlertInfo.showAndWait();
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".AlertInfo()");
        }
    }

}
