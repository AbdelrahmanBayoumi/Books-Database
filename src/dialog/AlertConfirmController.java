package dialog;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertConfirmController implements Initializable {

    @FXML
    private Label Msg_Label;
    public static Label Msg_Label_static;
    public static int flag;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flag = 0;
        Msg_Label_static = Msg_Label;
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
