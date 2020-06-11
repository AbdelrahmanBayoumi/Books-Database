package datamodels;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.Cursor;
import javafx.scene.control.ContentDisplay;

public class Field {

    private int ID;
    private String FieldName;
    private JFXButton editButton;
    private JFXButton deleteButton;

    public Field(int ID, String FieldName) {
        this.ID = ID;
        this.FieldName = FieldName;
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

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String FieldName) {
        this.FieldName = FieldName;
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
        return FieldName;
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
