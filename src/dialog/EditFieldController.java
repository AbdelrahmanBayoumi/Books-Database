package dialog;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

public class EditFieldController implements Initializable {

    @FXML
    private JFXTextField FieldNameTextField;
    public static JFXTextField FieldNameTextField_static;
    public static int flag;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flag = 0;
        FieldNameTextField_static = FieldNameTextField;
    }

    @FXML
    private void DiscardAction(ActionEvent event) {
        flag = 0;
        ((Stage) (((Node) (event.getSource())).getScene().getWindow())).close();
    }

    @FXML
    private void confirmAction(ActionEvent event) {
        flag = 1;
        ((Stage) (((Node) (event.getSource())).getScene().getWindow())).close();
    }

}
