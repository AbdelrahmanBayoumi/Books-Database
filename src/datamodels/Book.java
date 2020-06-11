package datamodels;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.Cursor;
import javafx.scene.control.ContentDisplay;

public class Book {

    private int ID;
    private String Title;
    private String Author;
    private String Publisher;
    private String Description;
    private String Location;
    private Field field;
    private JFXButton editButton;
    private JFXButton deleteButton;

    public Book(int ID, String Title, String Author, String Publisher, String Description, String Location, Field field) {
        this.ID = ID;
        this.Title = Title;
        this.Author = Author;
        this.Publisher = Publisher;
        this.Description = Description;
        this.Location = Location;
        this.field = field;
        this.editButton = new JFXButton();
        this.deleteButton = new JFXButton();
        btnDecoration();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String Publisher) {
        this.Publisher = Publisher;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Field getField() {
        return field;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public JFXButton getEditButton() {
        return editButton;
    }

    public void setEditButton(JFXButton editButton) {
        this.editButton = editButton;
    }

    public JFXButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JFXButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    @Override
    public String toString() {
        return "Book{" + "ID=" + ID + ", Title=" + Title + ", Author=" + Author + ", Publisher=" + Publisher + ", Description=" + Description + ", field=" + field + '}';
    }

    private void btnDecoration() {
        deleteButton.setStyle("-fx-background-color: transparent;-fx-background-radius:0;");
        deleteButton.setCursor(Cursor.HAND);
        FontAwesomeIconView TrashIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
        TrashIcon.setSize("17");
        TrashIcon.setStyle("-fx-fill:white; -fx-cursor: hand;");
        deleteButton.setGraphic(TrashIcon);
        deleteButton.setContentDisplay(ContentDisplay.TOP);

        editButton.setStyle("-fx-background-color: transparent;-fx-background-radius:0;");
        editButton.setCursor(Cursor.HAND);
        FontAwesomeIconView EditIcon = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
        EditIcon.setSize("17");
        EditIcon.setStyle("-fx-fill:white; -fx-cursor: hand;");
        editButton.setGraphic(EditIcon);
        editButton.setContentDisplay(ContentDisplay.TOP);
    }
}
