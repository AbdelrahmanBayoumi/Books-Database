package homepage;

import static add.AddViewController.UpdateFlag;
import static homepage.HomepageController.BorderPane_Static;
import static homepage.HomepageController.root_ShowView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class DefaultScreenController implements Initializable {

    private static final String CLASS_NAME = DefaultScreenController.class.getName();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void goToAddBooks(ActionEvent event) {
        BorderPane_Static.setCenter(HomepageController.root_AddView);
    }

    @FXML
    private void goToShowBooks(ActionEvent event) {
        try {
            if (UpdateFlag == 1) {
                root_ShowView = FXMLLoader.load(getClass().getResource("/show/ShowView.fxml"));
                UpdateFlag = 0;
            }
            BorderPane_Static.setCenter(root_ShowView);
        } catch (IOException ex) {
            utilities.Logger.log(utilities.Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".goToShowBooks()");
        }
    }

}
