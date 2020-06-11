package add;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import datamodels.Book;
import datamodels.Field;
import dialog.AlertConfirmController;
import dialog.EditBookController;
import dialog.EditFieldController;
import homepage.HomepageController;
import static homepage.HomepageController.BorderPane_Static;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import utilities.DatabaseUtil;
import static utilities.DatabaseUtil.con;
import static utilities.DatabaseUtil.result;
import static utilities.DatabaseUtil.stat;
import utilities.Logger;

public class AddViewController implements Initializable {

    private static final String CLASS_NAME = AddViewController.class.getName();
    public static int UpdateFlag = 0;

    @FXML
    private JFXTextField FieldNameTextField;
    @FXML
    private TableView<Field> FieldsTable;
    public static ObservableList<Field> list_Field = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Field, Integer> FieldID_Col;
    @FXML
    private TableColumn<Field, String> FieldName_Col;
    @FXML
    private TableColumn<Field, JFXButton> FieldEdit_Col;
    @FXML
    private TableColumn<Field, JFXButton> FieldDelete_Col;
    @FXML
    private JFXTextField Title_TextFiled;
    @FXML
    private JFXTextField Author_TextFiled;
    @FXML
    private JFXTextField Publisher_TextFiled;
    @FXML
    private JFXTextField Description_TextFiled;
    @FXML
    private JFXTextField Location_TextFiled;
    @FXML
    private JFXComboBox<Field> Field_ComboBox;
    @FXML
    private TableView<Book> BooksTable;
    public static ObservableList<Book> list_Books = FXCollections.observableArrayList();
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
    private TableColumn<Book, String> BookLocation_Col;
    @FXML
    private TableColumn<Book, String> BookFiled_Col;
    @FXML
    private TableColumn<Book, JFXButton> BookEdit_Col;
    @FXML
    private TableColumn<Book, JFXButton> BookDelete_Col;
    @FXML
    private JFXTextField searchFieldBox;
    @FXML
    private JFXTextField SearchBookBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Field
        FieldID_Col.setCellValueFactory(new PropertyValueFactory("ID"));
        FieldID_Col.setStyle("-fx-alignment: center;");
        FieldName_Col.setCellValueFactory(new PropertyValueFactory("FieldName"));
        FieldName_Col.setStyle("-fx-alignment: center;");
        FieldEdit_Col.setCellValueFactory(new PropertyValueFactory("editButton"));
        FieldEdit_Col.setStyle("-fx-alignment: center;");
        FieldDelete_Col.setCellValueFactory(new PropertyValueFactory("deleteButton"));
        FieldDelete_Col.setStyle("-fx-alignment: center;");
        FieldsTable.setPlaceholder(new Label("No Fields to display"));
        list_Field.addAll(selectFields());
        FieldsTable.setItems(list_Field);
        // Book
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
        BookFiled_Col.setCellValueFactory(new PropertyValueFactory("field"));
        BookFiled_Col.setStyle("-fx-alignment: center;");
        BookEdit_Col.setCellValueFactory(new PropertyValueFactory("editButton"));
        BookEdit_Col.setStyle("-fx-alignment: center;");
        BookDelete_Col.setCellValueFactory(new PropertyValueFactory("deleteButton"));
        BookDelete_Col.setStyle("-fx-alignment: center;");
        FieldsTable.setPlaceholder(new Label("No Books to display"));
        list_Books.addAll(selectBooks());
        BooksTable.setItems(list_Books);
        Field_ComboBox.setItems(list_Field);
    }

    @FXML
    private void backAction(ActionEvent event) {
        BorderPane_Static.setCenter(HomepageController.root_DefaultScreen);
    }

    @FXML
    private void searchFieldAction(KeyEvent event) {
        try {
            if (!searchFieldBox.getText().trim().equals("")) {
                list_Field.clear();
                list_Field.addAll(selectFieldSearch("WHERE field_name like '%" + searchFieldBox.getText().trim() + "%'"));
            } else {
                list_Field.clear();
                list_Field.addAll(selectFields());
            }
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".selectFieldSearch()");
        }
    }

    @FXML
    private void addField(ActionEvent event) {
        String text = FieldNameTextField.getText().trim();
        if(text.equals(""))
            return;
        DatabaseUtil.AddField(text);
        // Update Table
        list_Field.clear();
        list_Field.addAll(selectFields());
        UpdateFlag = 1;
        FieldNameTextField.setText("");
    }

    public ObservableList<Field> selectFieldSearch(String sql) {
        ObservableList<Field> list = FXCollections.observableArrayList();
        try {
            stat = con.prepareStatement("SELECT * FROM Field " + sql);
            result = stat.executeQuery();
            Field f;
            while (result.next()) {
                f = new Field(result.getInt(1), result.getString(2));
                f.getDeleteButton().setOnAction(DeleteFieldAction(f));
                f.getEditButton().setOnAction(EditFieldAction(f));
                list.add(f);
            }
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".selectFieldSearch()");
        }
        return list;
    }

    private ObservableList<Field> selectFields() {
        ObservableList<Field> list = FXCollections.observableArrayList();
        try {
            stat = con.prepareStatement("SELECT * FROM Field");
            result = stat.executeQuery();
            Field f;
            while (result.next()) {
                f = new Field(result.getInt(1), result.getString(2));
                f.getDeleteButton().setOnAction(DeleteFieldAction(f));
                f.getEditButton().setOnAction(EditFieldAction(f));
                list.add(f);
            }
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".selectFields()");
        }
        return list;
    }

    private EventHandler<ActionEvent> EditFieldAction(Field f) {
        EventHandler<ActionEvent> ev = (ActionEvent event) -> {
            try {
                HomepageController.EditField(f.getFieldName());
                if (EditFieldController.flag == 1) {
                    String sqlString = "Update Field set Field_name = '"
                            + EditFieldController.FieldNameTextField_static.getText()
                            + "' where field_id =" + f.getID();
                    PreparedStatement prepareStatement = con.prepareStatement(sqlString);
                    prepareStatement.executeUpdate();
                    list_Field.clear();
                    list_Field.addAll(selectFields());
                    FieldsTable.requestFocus();
                    UpdateFlag = 1;
                }
            } catch (Exception ex) {
                Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".EditFieldAction(Field" + f + ", FieldNameTextField_static: " + EditFieldController.FieldNameTextField_static.getText() + ")");
            }
        };
        return ev;
    }

    private EventHandler<ActionEvent> DeleteFieldAction(Field f) {
        EventHandler<ActionEvent> ev = (ActionEvent event) -> {
            try {
                HomepageController.AlertConfirm("Are you sure you want to delete?");
                if (AlertConfirmController.flag == 1) {
                    String sqlString = "DELETE FROM Field WHERE  Field_id =" + f.getID();
                    PreparedStatement prepareStatement = con.prepareStatement(sqlString);
                    prepareStatement.executeUpdate();
                }
                list_Field.clear();
                list_Field.addAll(selectFields());
                FieldsTable.requestFocus();
                UpdateFlag = 1;
            } catch (Exception ex) {
                Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".DeleteFieldAction(Field" + f + ")");
            }
        };
        return ev;
    }

    // ======================================
    // ================ Book ================
    // ======================================
    @FXML
    private void addBookAction(ActionEvent event) {
        if ("".equals(Title_TextFiled.getText().trim()) || Field_ComboBox.getValue() == null) {
            return;
        }
        Book b = new Book(-1, Title_TextFiled.getText().trim(), Author_TextFiled.getText().trim(),
                Publisher_TextFiled.getText().trim(), Description_TextFiled.getText().trim(),
                Location_TextFiled.getText(), Field_ComboBox.getValue());
        int Add_Book_flag = Add_Book(b);
        if (Add_Book_flag != -1) {
            HomepageController.AlertInfo("Book ID is: " + Add_Book_flag);
            b.setID(Add_Book_flag);
            list_Books.clear();
            list_Books.addAll(selectBooks());
            UpdateFlag = 1;
        } else {
            HomepageController.AlertInfo("Error in Adding Book " + b.getTitle() + "");
        }
        Title_TextFiled.setText("");
        Author_TextFiled.setText("");
        Publisher_TextFiled.setText("");
        Description_TextFiled.setText("");
        Location_TextFiled.setText("");
        Field_ComboBox.setValue(null);
    }

    public int Add_Book(Book book) {
        try {
            String sqlString = "INSERT INTO Book (title,Author,publisher,Description,Location,Book_Field_id) values (?,?,?,?,?,?)";
            PreparedStatement prepareStatement = con.prepareStatement(sqlString);
            prepareStatement.setString(1, book.getTitle());
            prepareStatement.setString(2, book.getAuthor());
            prepareStatement.setString(3, book.getPublisher());
            prepareStatement.setString(4, book.getDescription());
            prepareStatement.setString(5, book.getLocation());
            prepareStatement.setInt(6, book.getField().getID());
            prepareStatement.execute();
            return getBookID(book.getTitle());
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".Add_Book(Book: " + book + ")");
            return -1;
        }
    }

    private int getBookID(String title) {
        try {
            stat = con.prepareStatement("SELECT book_id FROM Book where title ='" + title + "'");
            result = stat.executeQuery();
            return result.getInt(1);
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".getBookID(title:" + title + ")");
            return -1;
        }
    }

    private ObservableList<Book> selectBooks() {
        ObservableList<Book> list = FXCollections.observableArrayList();
        try {
            stat = con.prepareStatement("SELECT * FROM Book");
            result = stat.executeQuery();
            Book b;
            while (result.next()) {

                b = new Book(result.getInt(1), result.getString(2),
                        result.getString(3), result.getString(4),
                        result.getString(5), result.getString(6),
                        getFiledbyID(result.getInt(7)));
                b.getDeleteButton().setOnAction(DeleteBookAction(b));
                b.getEditButton().setOnAction(EditBookAction(b));
                list.add(b);
            }
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".selectBooks()");
        }
        return list;
    }

    public static Field getFiledbyID(int id) {
        ResultSet res;
        PreparedStatement statement;
        try {
            statement = con.prepareStatement("SELECT * FROM Field where field_id =" + id);
            res = statement.executeQuery();
            return new Field(res.getInt(1), res.getString(2));
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".getFiledbyID(id:" + id + ")");
            return null;
        }
    }

    private EventHandler<ActionEvent> EditBookAction(Book b) {
        EventHandler<ActionEvent> ev = (ActionEvent event) -> {
            try {
                HomepageController.EditBook(b);
                if (EditBookController.flag == 1) {
                    String sqlString = "Update Book set title= ? , Author = ? , "
                            + "publisher = ? , Description= ? , Location= ? "
                            + ", Book_Field_id= ? where book_id = ?";
                    PreparedStatement prepareStatement = con.prepareStatement(sqlString);
                    prepareStatement.setString(1, EditBookController.Title_TextFiled_Static.getText());
                    prepareStatement.setString(2, EditBookController.Author_TextFiled_Static.getText());
                    prepareStatement.setString(3, EditBookController.Publisher_TextFiled_Static.getText());
                    prepareStatement.setString(4, EditBookController.Description_TextFiled_Static.getText());
                    prepareStatement.setString(5, EditBookController.Location_TextFiled_Static.getText());
                    prepareStatement.setInt(6, EditBookController.Field_ComboBox_Static.getValue().getID());
                    prepareStatement.setInt(7, b.getID());
                    prepareStatement.execute();
                    list_Books.clear();
                    list_Books.addAll(selectBooks());
                    BooksTable.requestFocus();
                    UpdateFlag = 1;
                }
            } catch (Exception ex) {
                Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".EditBookAction(Book: " + b + ")");
                HomepageController.AlertInfo("Error in Edit Book");
            }
        };
        return ev;
    }

    private EventHandler<ActionEvent> DeleteBookAction(Book b) {
        EventHandler<ActionEvent> ev = (ActionEvent event) -> {
            try {
                HomepageController.AlertConfirm("Are you sure you want to delete?");
                if (AlertConfirmController.flag == 1) {
                    String sqlString = "DELETE FROM Book WHERE  book_id =" + b.getID();
                    PreparedStatement prepareStatement = con.prepareStatement(sqlString);
                    prepareStatement.executeUpdate();
                }
                list_Books.clear();
                list_Books.addAll(selectBooks());
                BooksTable.requestFocus();
                UpdateFlag = 1;
            } catch (Exception ex) {
                Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".DeleteBookAction(Book" + b + ")");
                HomepageController.AlertInfo("Error in Delete Book");
            }
        };
        return ev;
    }

    @FXML
    private void SearchBookAction(KeyEvent event) {
        try {
            if (!SearchBookBox.getText().trim().equals("")) {
                list_Books.clear();
                list_Books.addAll(selectBookSearch("WHERE title like '%" + SearchBookBox.getText().trim() + "%'"));
            } else {
                list_Books.clear();
                list_Books.addAll(selectBooks());
            }
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".selectFieldSearch()");
        }
    }

    public ObservableList<Book> selectBookSearch(String sql) {
        ObservableList<Book> list = FXCollections.observableArrayList();
        try {
            stat = con.prepareStatement("SELECT * FROM Book " + sql);
            result = stat.executeQuery();
            Book b;
            while (result.next()) {

                b = new Book(result.getInt(1), result.getString(2),
                        result.getString(3), result.getString(4),
                        result.getString(5), result.getString(6),
                         getFiledbyID(result.getInt(7)));
                b.getDeleteButton().setOnAction(DeleteBookAction(b));
                b.getEditButton().setOnAction(EditBookAction(b));
                list.add(b);
            }
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".selectBookSearch()");
        }
        return list;
    }
}
