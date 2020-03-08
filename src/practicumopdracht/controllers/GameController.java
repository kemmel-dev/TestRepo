package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import practicumopdracht.MainApplication;
import practicumopdracht.data.GameDAO;
import practicumopdracht.models.Achievement;
import practicumopdracht.models.Game;
import practicumopdracht.views.AchievementView;
import practicumopdracht.views.GameView;
import practicumopdracht.views.View;

import java.util.ArrayList;

public class GameController extends Controller {

    private GameDAO gameDAO = MainApplication.getGameDAO();

    private GameView view;

    public GameController()
    {
        view = new GameView();
        initButtonHandling();
    }

    public void onSwitchView()
    {
        view.getListView().refresh();
    }

    private void addGame(Game game)
    {
        view.getListView().getItems().add(game);
    }

    public void initButtonHandling()
    {
        view.getClearButton().setOnAction(e -> handleClearButton());
        view.getAddButton().setOnAction(e -> handleAddButton());
        view.getOpenButton().setOnAction(e -> handleOpenButton());
    }

    private void refreshData() {
        //Maak een list tot een observable list
        ObservableList<Game> gameList = FXCollections.observableList(gameDAO.getAll());

        //Koppel de ListView aan de dataset die het repostitory object levert.
        view.getListView().setItems(gameList);
    }

    private void handleAddButton() {
        String title = view.getTitleTextField().getText().trim();
        String noOfAchievementsString = view.getNumAchievementsTextField().getText().trim();
        int noOfAchievements = -1;
        boolean tracked = view.getTrackingCheckBox().isSelected();

        StringBuilder missingBuffer = new StringBuilder();
        boolean filled = true;
        if (title == null || title.isEmpty())
        {
            missingBuffer.append("\t-\tTitle\n");
            filled = false;
        }
        if (noOfAchievementsString.isEmpty() || noOfAchievementsString == null)
        {
            missingBuffer.append("\t-\tNumber of Achievements\n");
            filled = false;
        }
        else {
            try {
                noOfAchievements = Integer.parseInt(noOfAchievementsString);
            } catch (NumberFormatException nfe) {
                missingBuffer.append("\t-\t Number of Achievements (must be an integer!)\n");
                filled = false;
            }
        }

        if (filled)
        {
            Game game = new Game(title, noOfAchievements, tracked);
            gameDAO.addOrUpdate(game);
            refreshData();
        }
        else {
            showMissingAlert(missingBuffer.toString());
        }
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

    private void handleClearButton() {
        view.getTitleTextField().setText(null);
        view.getNumAchievementsTextField().setText(null);
        view.getTrackingCheckBox().setSelected(false);
    }

    public GameDAO getGameDAO() {
        return gameDAO;
    }

    private void handleOpenButton()
    {
        Game gameToBeOpened = view.getListView().getSelectionModel().getSelectedItem();
        if (gameToBeOpened != null)
        {
            AchievementController achievementController = (AchievementController) MainApplication.achievementController;
            achievementController.setBelongsToGame(gameToBeOpened);
            MainApplication.switchController(MainApplication.achievementController);
            return;
        }
        showNotSelectedAlert();
    }

    //TODO alert
    private void showNotSelectedAlert() {
        System.out.println("not selected");
    }

    public View getView()
    {
        return view;
    }

}
