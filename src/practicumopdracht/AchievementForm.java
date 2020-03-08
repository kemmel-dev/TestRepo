package practicumopdracht;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AchievementForm {

    private TextField titleField;
    private TextArea descriptionArea;
    private CheckBox achievedCheckBox, trackedCheckBox;
    private DatePicker achievedOnDatePicker;
    private ComboBox<Integer> priorityComboBox;
    private Button addButton, clearButton;
    private VBox formContainer;
    private int width;

    public AchievementForm(int width, FormType formType)
    {
        this.width = width;
        formContainer = new VBox();
        formContainer.setMinHeight(0);

        // left form
        GridPane leftForm = new GridPane(), rightForm = new GridPane();
        formatGridPane(leftForm);
        formatGridPane(rightForm);

        Label label = new Label("Title");
        titleField = new TextField();
        leftForm.add(label, 0, 0);
        leftForm.add(titleField, 1, 0);

        label = new Label("Description");
        descriptionArea = new TextArea();
        descriptionArea.setMaxWidth(width / 5);
        leftForm.add(label, 0, 1);
        leftForm.add(descriptionArea, 1, 1);

        // right form
        int yIndex = 0;
        label = new Label("Achieved");
        achievedCheckBox = new CheckBox();
        rightForm.add(label, 0, yIndex);
        rightForm.add(achievedCheckBox, 1, yIndex++);

        label = new Label("Achieved On");
        achievedOnDatePicker = new DatePicker();
        rightForm.add(label, 0, yIndex);
        rightForm.add(achievedOnDatePicker, 1, yIndex++);

        label = new Label("Tracking");
        trackedCheckBox = new CheckBox();
        rightForm.add(label, 0, yIndex);
        rightForm.add(trackedCheckBox, 1, yIndex++);

        label = new Label("Priority");
        ObservableList<Integer> comboList = FXCollections.observableArrayList(1, 2, 3, 4, 5);
        priorityComboBox = new ComboBox(comboList);
        priorityComboBox.setValue(1);
        rightForm.add(label, 0, yIndex);
        rightForm.add(priorityComboBox, 1, yIndex++);

        HBox leftAndRightFormContainer = new HBox(0);
        leftAndRightFormContainer.setAlignment(Pos.CENTER);
        leftAndRightFormContainer.getChildren().addAll(leftForm, rightForm);
        formContainer.getChildren().addAll(leftAndRightFormContainer, createButtonBox(formType));
    }

    public HBox createButtonBox(FormType formType)
    {
        HBox buttonBox = new HBox(width / 20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 25, 10, 25));

        clearButton = new Button("Clear");
        buttonBox.getChildren().add(clearButton);
        if (formType == FormType.VIEW)
        {
            addButton = new Button("Add");
            buttonBox.getChildren().add(addButton);
            return buttonBox;
        }
        return buttonBox;
    }

    public void formatGridPane(GridPane gridPane) {
        double insetSize = width / 32;
        gridPane.setPadding(new Insets(insetSize, insetSize, insetSize, insetSize));
        gridPane.setHgap(width / 25);
        gridPane.setVgap(width / 25);
    }

    public Parent getRoot()
    {
        return formContainer;
    }

    public TextField getTitleField() {
        return titleField;
    }

    public TextArea getDescriptionArea() {
        return descriptionArea;
    }

    public CheckBox getAchievedCheckBox() {
        return achievedCheckBox;
    }

    public CheckBox getTrackedCheckBox() {
        return trackedCheckBox;
    }

    public DatePicker getAchievedOnDatePicker() {
        return achievedOnDatePicker;
    }

    public ComboBox<Integer> getPriorityComboBox() {
        return priorityComboBox;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getClearButton() {
        return clearButton;
    }
}
