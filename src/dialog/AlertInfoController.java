package dialog;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertInfoController implements Initializable {

    @FXML
    private Label Msg_Label;
    public static Label Msg_Label_static;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Msg_Label_static = Msg_Label;
    }

    @FXML
    private void confirmAction(ActionEvent event) {
        ((Stage) (((Node) (event.getSource())).getScene().getWindow())).close();
    }

}
