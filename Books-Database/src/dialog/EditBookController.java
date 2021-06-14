package dialog;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import datamodels.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

public class EditBookController implements Initializable {

    @FXML
    private JFXTextField Title_TextFiled;
    public static JFXTextField Title_TextFiled_Static;
    @FXML
    private JFXTextField Author_TextFiled;
    public static JFXTextField Author_TextFiled_Static;
    @FXML
    private JFXTextField Publisher_TextFiled;
    public static JFXTextField Publisher_TextFiled_Static;
    @FXML
    private JFXTextField Description_TextFiled;
    public static JFXTextField Description_TextFiled_Static;
    @FXML
    private JFXComboBox<Field> Field_ComboBox;
    public static JFXComboBox<Field> Field_ComboBox_Static;
    public static int flag;
    @FXML
    private JFXTextField Location_TextFiled;
    public static JFXTextField Location_TextFiled_Static;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flag = 0;
        Title_TextFiled_Static = Title_TextFiled;
        Author_TextFiled_Static = Author_TextFiled;
        Publisher_TextFiled_Static = Publisher_TextFiled;
        Description_TextFiled_Static = Description_TextFiled;
        Description_TextFiled_Static = Description_TextFiled;
        Location_TextFiled_Static = Location_TextFiled;
        Field_ComboBox_Static = Field_ComboBox;
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
