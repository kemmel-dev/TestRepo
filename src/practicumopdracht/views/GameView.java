package practicumopdracht.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import practicumopdracht.models.Game;

public class GameView extends TemplateView {

    private VBox mainPane;

    private TextField titleTextField, numAchievementsTextField;
    private Button clearButton, addButton, deleteButton, openButton;
    private CheckBox trackingCheckBox;
    private ListView<Game> listView;

    public static final int HEIGHT = 640;
    public static final int WIDTH = 800;

    public GameView()
    {
        mainPane = new VBox();
        mainPane.setPrefHeight(HEIGHT);
        mainPane.setPrefWidth(WIDTH);
        //mainPane.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));
        initButtons();
        initTextFields();
        trackingCheckBox = new CheckBox();
        ObservableList<Node> nodeList = mainPane.getChildren();
        nodeList.add(createInputForm());
        nodeList.add(createListPart());
        nodeList.add(createListButtons());
    }

    private void initButtons()
    {
        clearButton = new Button("Clear");
        addButton = new Button("Add");
        clearButton.setMaxWidth(Double.MAX_VALUE);
        addButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(clearButton, Priority.ALWAYS);
        HBox.setHgrow(addButton, Priority.ALWAYS);

        deleteButton = new Button("Delete");
        openButton = new Button("Open");
    }

    private void initTextFields()
    {
        titleTextField = new TextField();
        titleTextField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(titleTextField, Priority.ALWAYS);
        numAchievementsTextField = new TextField();
        numAchievementsTextField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(numAchievementsTextField, Priority.ALWAYS);
    }

    private VBox createListPart()
    {
        VBox listPart = new VBox();
        ObservableList<Node> listPartNodes = listPart.getChildren();

        listView = new ListView<>(FXCollections.observableArrayList());
        double insetSize = 50;
        VBox.setMargin(listView, new Insets(insetSize / 2, insetSize, insetSize / 2, insetSize));
        listPartNodes.add(listView);
        return listPart;
    }

    private HBox createListButtons()
    {
        HBox hBox = new HBox();
        hBox.getChildren().add(deleteButton);
        hBox.getChildren().add(openButton);
        hBox.setAlignment(Pos.CENTER);
        double insetSize = 50;
        hBox.setPadding(new Insets(insetSize, insetSize, insetSize, insetSize));
        return hBox;
    }

    private VBox createInputForm()
    {
        VBox inputForm = new VBox(5);
        inputForm.setPadding(new Insets(25, 25, 25, 25));
        ObservableList<Node> nodeList = inputForm.getChildren();
        nodeList.add(createInputFormFields());
        nodeList.add(createInputFormButtons());
        return inputForm;
    }

    private GridPane createInputFormFields()
    {
        String[] labelContents = {"Title of game", "No. of Achievements", "Currently tracking?"};
        int labelIndex = 0;

        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        for (int yIndex = 0; yIndex < labelContents.length; yIndex++) {
            Label label = new Label(labelContents[labelIndex++]);
            gridPane.add(label, 0, yIndex);
        }

        gridPane.add(titleTextField, 1, 0);
        gridPane.add(numAchievementsTextField, 1 , 1);
        gridPane.add(trackingCheckBox, 1, 2);
        return gridPane;
    }

    private VBox createInputFormButtons()
    {
        VBox vBox = new VBox(5);
        vBox.getChildren().add(clearButton);
        vBox.getChildren().add(addButton);
        return vBox;
    }

    public Parent getRoot()
    {
        return mainPane;
    }

    public TextField getTitleTextField() {
        return titleTextField;
    }

    public TextField getNumAchievementsTextField() {
        return numAchievementsTextField;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public CheckBox getTrackingCheckBox() {
        return trackingCheckBox;
    }

    public ListView<Game> getListView() {
        return listView;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getOpenButton() {
        return openButton;
    }
}
