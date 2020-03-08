package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import practicumopdracht.AchievementForm;
import practicumopdracht.FormType;
import practicumopdracht.MainApplication;
import practicumopdracht.data.AchievementDAO;
import practicumopdracht.models.Achievement;
import practicumopdracht.models.Game;
import practicumopdracht.views.AchievementView;
import practicumopdracht.views.GameView;
import practicumopdracht.views.View;

import java.util.List;
import java.util.Optional;

public class AchievementController extends Controller {

    private AchievementView view;
    private Game belongsToGame;
    private AchievementDAO achievementDAO;

    public AchievementController(Game belongsToGame) {
        this.view = new AchievementView();
        initDAO();
        this.belongsToGame = belongsToGame;
        view.getClearButton().setOnAction(e -> handleClearButton());
        view.getAddButton().setOnAction(e -> handleAddButton());
        view.getRemoveButton().setOnAction(e -> handleRemoveButton());
        view.getPriorityButton().setOnAction(e -> handleSetPriorityButton());
        view.getEditButton().setOnAction(e -> handleEditButton());
        view.getSetAchievedButton().setOnAction(e -> handleSetAchievedButton());
    }

    private void initDAO() {
        achievementDAO = MainApplication.getAchievementDAO();
    }

    private void refreshData() {
        //Maak een list tot een observable list
        List<Game> gameList = MainApplication.getGameDAO().getAll();
        ObservableList<Game> observableGameList = FXCollections.observableList(gameList);
        //TODO: Fix bug when adding new game from achievementview
        view.getGamePicker().setItems(observableGameList);

        //Koppel de ListView aan de dataset die het repostitory object levert.
        ObservableList<Achievement> achievementList = FXCollections.observableList(achievementDAO.getAllFor(belongsToGame));
        view.getTableView().setItems(achievementList);
    }

    public AchievementController()
    {
        this(null);
    }

    public void setBelongsToGame(Game belongsToGame)
    {
        this.belongsToGame = belongsToGame;
    }


    public void onSwitchView()
    {
        view.getGamePicker().getSelectionModel().select(belongsToGame);
        refreshData();
    }

    private boolean containsGame(Game game)
    {
        GameView gameView = (GameView) MainApplication.gameController.getView();
        ObservableList<Game> gameList = gameView.getListView().getItems();
        for (Game otherGame : gameList)
        {
            if (otherGame == game)
            {
                return true;
            }
        }
        return false;
    }

    public void handleClearButton() {
        view.getTitleTextField().clear();
        view.getDescriptionTextArea().clear();
        view.getAchievedCheckBox().setSelected(false);
        view.getAchievedCheckBox().setDisable(false);
        view.getTrackedCheckBox().setSelected(false);
        view.getTrackedCheckBox().setDisable(false);
        view.getAchievedOnDatePicker().getEditor().clear();
        view.getAchievedOnDatePicker().setValue(null);
        view.getAchievedOnDatePicker().setDisable(true);
        view.getPriorityComboBox().getSelectionModel().clearSelection();
        view.getPriorityComboBox().setValue(1);
        view.getPriorityComboBox().setDisable(true);
    }

    private boolean isFilled(TextField textField) {
        if (textField.getText() == null || textField.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean isFilled(TextArea textArea) {
        if (textArea.getText() == null || textArea.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean isFilled(DatePicker datePicker) {
        if (datePicker.getValue() == null) {
            return false;
        }
        return true;
    }

    public void handleAddButton() {
        Achievement achievement = attemptCreateAchievement(view.getTitleTextField(), view.getDescriptionTextArea(), view.getAchievedCheckBox(), view.getAchievedOnDatePicker(), view.getTrackedCheckBox(), view.getPriorityComboBox());
        if (achievement != null) {
            achievementDAO.addOrUpdate(achievement);
            refreshData();
            return;
        }
        genMissingAlert(view.getTitleTextField(), view.getDescriptionTextArea(), view.getAchievedCheckBox(), view.getTrackedCheckBox(), view.getAchievedOnDatePicker());
    }

    private void genMissingAlert(TextField titleField, TextArea descriptionField, CheckBox achievedCheckBox, CheckBox trackedCheckBox, DatePicker achievedOnDatePicker) {
        // If a new achievement hasn't been added at this point, values must be missing.
        StringBuilder missingBuffer = new StringBuilder();
        if (!isFilled(titleField))
        {
            missingBuffer.append("\t-\tTitle\n");
        }
        if (!isFilled(descriptionField))
        {
            missingBuffer.append("\t-\tDescription\n");
        }
        if (achievedCheckBox.isSelected())
        {
            if (!isFilled(achievedOnDatePicker))
            {
                missingBuffer.append("\t-\tAchieved On\n");
            }
            if (trackedCheckBox.isSelected())
            {
                missingBuffer.append("\t-\tTracked (cannot track if already achieved)\n");
            }
        }
        showMissingAlert(missingBuffer.toString());
    }

    private Achievement attemptCreateAchievement(TextField titleField, TextArea descriptionArea, CheckBox achievedCheckBox, DatePicker achievedOnDatePicker,  CheckBox trackedCheckBox, ComboBox<Integer> priorityComboBox) {
        Achievement achievement;
        Game parentGame = view.getGamePicker().getSelectionModel().getSelectedItem();

        boolean titleEmpty = titleField.getText().trim().isEmpty();
        boolean descriptionEmpty = descriptionArea.getText().trim().isEmpty();
        boolean achievedOnEmpty = achievedOnDatePicker.getValue() == null;

        if (titleEmpty || descriptionEmpty)
        {
            return null;
        }
        if (achievedCheckBox.isSelected())
        {
            if (achievedOnEmpty)
            {
                return null;
            }
            if (trackedCheckBox.isSelected())
            {
                return null;
            }
        }
        if (!achievedOnEmpty)
        {
            return null;
        }
        return new Achievement(
                -1,
                view.getGamePicker().getSelectionModel().getSelectedItem(),
                view.getTitleTextField().getText().trim(),
                view.getDescriptionTextArea().getText().trim(),
                view.getAchievedCheckBox().isSelected(),
                view.getAchievedOnDatePicker().getValue(),
                view.getTrackedCheckBox().isSelected(),
                (int) view.getPriorityComboBox().getSelectionModel().getSelectedItem()
        );
    }

    private void showMissingAlert(String missingValues) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Values are missing!");

        TextArea textArea = new TextArea();
        textArea.setPrefColumnCount(30);
        textArea.setPrefRowCount(10);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setText("Not every required field has been filled, please ensure you fill the following fields:\n" + missingValues);

        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }

    private void showRemoveAlert(Achievement achievementToBeRemoved) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation message");
        alert.setHeaderText("Removing achievement");
        alert.setContentText("Removing achievement with title: \n" + achievementToBeRemoved);
        alert.showAndWait();
    }

    private void showNullAlert(String nullType) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Null error");
        alert.setHeaderText(String.format("Value for " + nullType + " is missing!"));
        alert.setContentText("Make sure a value has been given for " + nullType + ".");
        alert.showAndWait();
    }

    public void handleRemoveButton() {
        Achievement achievementToBeRemoved = view.getTableView().getSelectionModel().getSelectedItem();
        if (achievementToBeRemoved != null)
        {
            showRemoveAlert(achievementToBeRemoved);
            view.getTableView().getItems().remove(achievementToBeRemoved);
        }
        else {
            showNullAlert("Achievement Selection");
        }
    }

    public void handleSetPriorityButton() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>(null, "None", "1", "2", "3", "4", "5");
        dialog.setTitle("Set priority");
        dialog.setHeaderText("Value needed");
        dialog.setContentText("What priority does this achievement have?");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (result.get() == "None")
            {
                view.getTableView().getSelectionModel().getSelectedItem().setPriority(0);
                view.getTableView().refresh();
            }
            else if (view.getTableView().getSelectionModel().getSelectedItem() != null) {
                view.getTableView().getSelectionModel().getSelectedItem().setPriority(Integer.parseInt(result.get()));
                view.getTableView().refresh();
            }
        }
    }

    public void handleEditButton() {
        Dialog<Achievement> dialog = new Dialog<>();
        dialog.setTitle("Edit dialog");
        dialog.setHeaderText("Editing Achievement");

        ButtonType confirmButtonType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType  = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        Achievement selectedAchievement = view.getTableView().getSelectionModel().getSelectedItem();

        Game game = selectedAchievement.belongsToGame();
        int masterId = selectedAchievement.getMasterId();

        AchievementForm inputForm = new AchievementForm(view.WIDTH, FormType.ALERT);
        TextField titleTextField = inputForm.getTitleField();
        titleTextField.setText(selectedAchievement.getTitle());
        TextArea descriptionTextArea = inputForm.getDescriptionArea();
        descriptionTextArea.setText(selectedAchievement.getTitle());
        CheckBox trackedCheckBox = inputForm.getTrackedCheckBox();
        trackedCheckBox.setSelected(selectedAchievement.getTracked());
        CheckBox achievedCheckBox = inputForm.getAchievedCheckBox();
        achievedCheckBox.setSelected(selectedAchievement.getAchieved());
        DatePicker achievedOnDatePicker = inputForm.getAchievedOnDatePicker();
        achievedOnDatePicker.setValue(selectedAchievement.getDateAchieved());
        ComboBox<Integer> priorityComboBox = inputForm.getPriorityComboBox();
        priorityComboBox.getSelectionModel().select(selectedAchievement.getPriority());

        dialog.getDialogPane().setContent(inputForm.getRoot());
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, cancelButtonType);

        Button okButton = (Button) dialog.getDialogPane().lookupButton(confirmButtonType);
        okButton.addEventFilter(ActionEvent.ACTION, e -> {
            // Check whether some conditions are fulfilled
            if (attemptCreateAchievement(titleTextField, descriptionTextArea, achievedCheckBox, achievedOnDatePicker, trackedCheckBox, priorityComboBox) == null) {
                // The conditions are not fulfilled so we consume the event
                // to prevent the dialog to close
                genMissingAlert(titleTextField, descriptionTextArea, achievedCheckBox, trackedCheckBox, achievedOnDatePicker);
                e.consume();
            }
        });

        //TODO: edit achievement
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButtonType) {
                Achievement editedAchievement = new Achievement(masterId, game, titleTextField.getText(), descriptionTextArea.getText(), achievedCheckBox.isSelected(), achievedOnDatePicker.getValue(), trackedCheckBox.isSelected(), priorityComboBox.getSelectionModel().getSelectedItem());
                editedAchievement.setId(selectedAchievement.getId());
                return editedAchievement;
            }
            return null;
        });

        Optional<Achievement> result = dialog.showAndWait();
        if (result.isPresent())
        {
            Achievement editedAchievement = result.get();
            achievementDAO.addOrUpdate(editedAchievement);
            refreshData();
        }

    }

    public void handleSetAchievedButton() {
        Achievement selectedAchievement = view.getTableView().getSelectionModel().getSelectedItem();
        if (selectedAchievement != null) {
            selectedAchievement.switchAchieved();
            view.getTableView().refresh();
        }
    }

    public void handleSaveMenuItem() {
        System.out.println("Save has been clicked");
    }

    public View getView() {
        return view;
    }

}
