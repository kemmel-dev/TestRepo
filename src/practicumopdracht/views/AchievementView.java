package practicumopdracht.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Achievement;
import practicumopdracht.models.Game;
import practicumopdracht.FormType;
import practicumopdracht.AchievementForm;

/**
 * The View class used to represent how the view of the
 * Achievement screen should look like.
 * @author Kamiel de Visser | 500838438
 */
public class AchievementView extends TemplateView {

    private VBox mainPane;

    public static final int HEIGHT = 640;
    public static final int WIDTH = 800;

    private TextField titleTextField;
    private TextArea descriptionTextArea;
    private CheckBox achievedCheckBox, trackedCheckBox;
    private DatePicker achievedOnDatePicker;
    private ComboBox<Integer> priorityComboBox;
    private TableView<Achievement> tableView;
    private ComboBox<Game> gamePicker;

    private Game belongsToGame;

    private Button removeButton, priorityButton, editButton, setAchievedButton, clearButton, addButton;

    /**
     * Instantiates an AchievementView object.
     */
    public AchievementView() {
        // User a vbox as the main pane
        mainPane = new VBox();
        mainPane.setPrefWidth(WIDTH);
        mainPane.setPrefHeight(HEIGHT);

        // Add the required nodes
        mainPane.getChildren().add(createGamePicker());
        mainPane.getChildren().add(createInputFormAndTablePart());
        mainPane.getChildren().add(createTableButtonBox());
    }

    private HBox createGamePicker() {
        HBox container = new HBox(WIDTH / 32);
        gamePicker = new ComboBox<Game>(FXCollections.observableArrayList());
        container.getChildren().addAll(new Label("Pick game:"), gamePicker);
        container.setAlignment(Pos.CENTER_LEFT);
        container.setPadding(new Insets(0,0,0,WIDTH / 64));
        return container;
    }

    protected HBox createTableButtonBox() {
        HBox hBox = new HBox(WIDTH / 20);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 25, 10, 25));

        removeButton = new Button("Remove");
        priorityButton = new Button("Set Priority");
        editButton = new Button("Edit Achievement");
        setAchievedButton = new Button("Set achieved");

        hBox.getChildren().addAll(removeButton, priorityButton, editButton, setAchievedButton);
        return hBox;
    }

    private SplitPane createInputFormAndTablePart()
    {
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);
        ObservableList<Node> splitPaneItemList = splitPane.getItems();

        AchievementForm inputForm = new AchievementForm(WIDTH, FormType.VIEW);
        titleTextField = inputForm.getTitleField();
        descriptionTextArea = inputForm.getDescriptionArea();
        trackedCheckBox = inputForm.getTrackedCheckBox();
        achievedCheckBox = inputForm.getAchievedCheckBox();
        achievedOnDatePicker = inputForm.getAchievedOnDatePicker();
        addButton = inputForm.getAddButton();
        clearButton = inputForm.getClearButton();
        priorityComboBox = inputForm.getPriorityComboBox();
        splitPaneItemList.add(inputForm.getRoot());
        splitPaneItemList.add(createTablePart());
        return splitPane;
    }

    /**
     * Creates the VBox containing the tableView which shows what Achievements have been
     * added.
     * @return the VBox containing the tableView
     */
    private VBox createTablePart() {
        VBox listPart = new VBox();
        listPart.getChildren().add(createAchievementTable());
        return listPart;
    }

    private TableView<Achievement> createAchievementTable()
    {
        tableView = new TableView<>();
        tableView.setEditable(true);
        tableView.setItems(FXCollections.observableArrayList());
        tableView.setPlaceholder(new Label("No rows to display"));

        TableColumn<Achievement, String> achievedColumn = new TableColumn<>("Achieved?");
        achievedColumn.setCellValueFactory(new PropertyValueFactory<>("achievedString"));
        achievedColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Achievement, String> priorityColumn = new TableColumn<>("Priority");
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));

        TableColumn<Achievement, String> gameColumn = new TableColumn<>("Belongs to");
        gameColumn.setCellValueFactory(new PropertyValueFactory<>("gameName"));
        gameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Achievement, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Achievement, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        achievedColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Achievement, String> dateAchievedColumn = new TableColumn<>("Date Achieved");
        dateAchievedColumn.setCellValueFactory(new PropertyValueFactory<>("dateAchievedString"));

        TableColumn<Achievement, String> trackedColumn = new TableColumn<>("Tracked?");
        trackedColumn.setCellValueFactory(new PropertyValueFactory<>("trackedString"));

        tableView.getColumns().addAll(trackedColumn, priorityColumn, gameColumn, achievedColumn, titleColumn, descriptionColumn, dateAchievedColumn);
        return tableView;
    }

    public Parent getRoot() {
        return mainPane;
    }

    public TextField getTitleTextField() {
        return titleTextField;
    }

    public TextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public CheckBox getAchievedCheckBox() {
        return achievedCheckBox;
    }

    public DatePicker getAchievedOnDatePicker() {
        return achievedOnDatePicker;
    }

    public ComboBox getPriorityComboBox() {
        return priorityComboBox;
    }

    public TableView<Achievement> getTableView() {
        return tableView;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public Button getPriorityButton() {
        return priorityButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    public Button getSetAchievedButton() {
        return setAchievedButton;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public Button getAddButton() {
        return addButton;
    }

    public CheckBox getTrackedCheckBox() {
        return trackedCheckBox;
    }

    public ComboBox<Game> getGamePicker() {
        return gamePicker;
    }

    public Game getGame()
    {
        return belongsToGame;
    }

    public void setBelongsToGame(Game belongsToGame) {
        this.belongsToGame = belongsToGame;
    }
}
