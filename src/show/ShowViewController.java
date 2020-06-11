package show;

import static add.AddViewController.list_Field;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import datamodels.Book;
import homepage.HomepageController;
import static homepage.HomepageController.BorderPane_Static;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import static utilities.DatabaseUtil.con;
import static utilities.DatabaseUtil.result;
import static utilities.DatabaseUtil.stat;
import datamodels.Field;
import javafx.event.Event;
import javafx.scene.control.RadioButton;
import javafx.scene.control.cell.PropertyValueFactory;
import utilities.DatabaseUtil;
import utilities.Logger;

public class ShowViewController implements Initializable {

    private static final String CLASS_NAME = ShowViewController.class.getName();
    @FXML
    private JFXTextField SearchBookBox;
    @FXML
    private ToggleGroup searchbyGroup;
    @FXML
    private TableView<Book> BooksTable;
    @FXML
    private TableColumn<Book, Integer> BookID_Col;
    @FXML
    private TableColumn<Book, String> BookName_Col;
    @FXML
    private TableColumn<Book, String> BookAuthor_Col;
    @FXML
    private TableColumn<Book, String> BookPublisher_Col;
    @FXML
    private TableColumn<Book, String> BookDescription_Col;
    @FXML
    private FlowPane flowPane_Instance;
    public static JFXButton Active;
    @FXML
    private TableColumn<?, ?> BookLocation_Col;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BookID_Col.setCellValueFactory(new PropertyValueFactory("ID"));
        BookID_Col.setStyle("-fx-alignment: center;");
        BookName_Col.setCellValueFactory(new PropertyValueFactory("Title"));
        BookName_Col.setStyle("-fx-alignment: center;");
        BookAuthor_Col.setCellValueFactory(new PropertyValueFactory("Author"));
        BookAuthor_Col.setStyle("-fx-alignment: center;");
        BookPublisher_Col.setCellValueFactory(new PropertyValueFactory("Publisher"));
        BookPublisher_Col.setStyle("-fx-alignment: center;");
        BookDescription_Col.setCellValueFactory(new PropertyValueFactory("Description"));
        BookDescription_Col.setStyle("-fx-alignment: center;");
        BookLocation_Col.setCellValueFactory(new PropertyValueFactory("Location"));
        BookLocation_Col.setStyle("-fx-alignment: center;");
        flowPane_Instance.getChildren().clear();
        flowPane_Instance.getChildren().addAll(makeFiledsBtns());
    }

    @FXML
    private void backAction(ActionEvent event) {
        BorderPane_Static.setCenter(HomepageController.root_DefaultScreen);
    }

    @FXML
    private void SearchBookAction(Event event) {
        String searchby = ((RadioButton) searchbyGroup.getSelectedToggle()).getText();
        if (!SearchBookBox.getText().trim().equals("")) {
            switch (searchby) {
                case "Title":
                    fillBooksTableSearch("And  Title like '%" + SearchBookBox.getText().trim() + "%'");
                    break;
                case "Author":
                    fillBooksTableSearch("And  Author like '%" + SearchBookBox.getText().trim() + "%'");
                    break;
                case "Publisher":
                    fillBooksTableSearch("And  Publisher like '%" + SearchBookBox.getText().trim() + "%'");
                    break;
                case "Description":
                    fillBooksTableSearch("And  Description like '%" + SearchBookBox.getText().trim() + "%'");
                    break;
                case "Location":
                    fillBooksTableSearch("And  Location like '%" + SearchBookBox.getText().trim() + "%'");
                    break;
                default:
                    System.out.println("default");
                    break;
            }
        } else {
            switch (searchby) {
                case "Title":
                    fillBooksTableSearch("");
                    break;
                case "Author":
                    fillBooksTableSearch("");
                    break;
                case "Publisher":
                    fillBooksTableSearch("");
                    break;
                case "Description":
                    fillBooksTableSearch("");
                    break;
                case "Location":
                    fillBooksTableSearch("");
                    break;
            }
        }
    }

    public ObservableList<JFXButton> makeFiledsBtns() {
        ObservableList<JFXButton> listOfButtons = FXCollections.observableArrayList();
        try {
            JFXButton b;
            for (int i = 0; i < list_Field.size(); i++) {
                b = new JFXButton(list_Field.get(i).getFieldName() + "");
                b.setOnAction(changeButtonFocus(b));
                b.setStyle("-fx-background-color:  -fx-secondary;"
                        + "-fx-text-fill: white;"
                        + "-fx-background-radius: 0;"
                        + "-fx-pref-width: 220;"
                        + "-fx-font-size:20;"
                        + "-fx-font-weight: BOLD;"
                        + "-fx-cursor: hand;"
                );
                listOfButtons.add(b);
            }
            if (listOfButtons.size() > 0) {
                Active = listOfButtons.get(0);
                putFocus(Active);
                fillBooksTable(Active.getText());
            }
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".makeFiledsBtns()");
        }
        return listOfButtons;
    }

    private void delFocus(JFXButton btn) {
        btn.setStyle("-fx-background-color:  -fx-secondary;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 0;"
                + "-fx-pref-width: 220;"
                + "-fx-font-size:20;"
                + "-fx-font-weight: BOLD;"
                + "-fx-cursor: hand;"
        );
    }

    private void putFocus(JFXButton btn) {
        btn.setStyle("-fx-background-color: -fx-blue;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 0;"
                + "-fx-pref-width: 220;"
                + "-fx-font-size:20;"
                + "-fx-font-weight: BOLD;"
                + "-fx-cursor: hand;"
        );
    }

    private void fillBooksTable(String FieldName) {

        try {
            ObservableList<Book> listOfBooks = FXCollections.observableArrayList();
            stat = con.prepareStatement("SELECT * FROM Book where Book_Field_id= " + DatabaseUtil.getFieldID(FieldName));
            result = stat.executeQuery();
            Book b;
            while (result.next()) {
                b = new Book(result.getInt(1), result.getString(2),
                        result.getString(3), result.getString(4),
                        result.getString(5), result.getString(6),
                         new Field(0, ""));
                listOfBooks.add(b);
            }
            BooksTable.setItems(listOfBooks);
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".fillBooksTable(FieldName" + FieldName + ")");
        }

    }

    private EventHandler<ActionEvent> changeButtonFocus(JFXButton b) {
        EventHandler<ActionEvent> ev = (ActionEvent event) -> {
            delFocus(Active);
            Active = b;
            putFocus(Active);
            fillBooksTable(Active.getText());
            SearchBookBox.setText("");
        };
        return ev;
    }

    private void fillBooksTableSearch(String sql) {
        try {
            ObservableList<Book> listOfBooks = FXCollections.observableArrayList();
            String s = "SELECT * FROM Book where Book_Field_id= "
                    + DatabaseUtil.getFieldID(Active.getText())
                    + " " + sql;
            stat = con.prepareStatement(s);
            result = stat.executeQuery();
            Book b;
            while (result.next()) {
                b = new Book(result.getInt(1), result.getString(2),
                        result.getString(3), result.getString(4),
                        result.getString(5), result.getString(6),
                         new Field(0, ""));
                listOfBooks.add(b);
            }
            BooksTable.setItems(listOfBooks);
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".fillBooksTableSearch()");
        }

    }
}
