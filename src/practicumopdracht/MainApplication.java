package practicumopdracht;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Application;
import practicumopdracht.controllers.AchievementController;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.GameController;
import practicumopdracht.controllers.MenuController;
import practicumopdracht.data.AchievementDAO;
import practicumopdracht.data.GameDAO;
import practicumopdracht.models.Achievement;
import practicumopdracht.models.Game;
import practicumopdracht.views.AchievementView;
import practicumopdracht.views.GameView;

public class MainApplication extends Application {

    private static final String TITLE = "Practicumopdracht OOP2 - Kamiel de Visser";

    private static Stage stage;
    private static AchievementDAO achievementDAO = new AchievementDAO();
    private static GameDAO gameDAO = new GameDAO();
    private static BorderPane mainContainer;

    public static Controller menuController, gameController, achievementController, currentController;

    /**
     * Override of main entry point for all JavaFX applications.
     * Called after init method has been returned, and after
     * System is ready for the application to be running.
     * @param stage  the top level JavaFX container.
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle(TITLE);

        // Create new controllers for each view
        gameController = new GameController();
        achievementController = new AchievementController();
        menuController = new MenuController();

        // Create a container for a MenuView + GameView/AchievementView
        mainContainer = new BorderPane();
        mainContainer.setTop(menuController.getView().getRoot());
        // Set the scene to that container
        stage.setScene(new Scene(mainContainer));

        // Switch the controller to the start view.
        switchController(gameController);
    }

    public static void switchController(Controller mainViewController)
    {
        currentController = mainViewController;
        currentController.onSwitchView();
        // Set the center part of the mainContainer to the controller's GameView/AchievementView
        mainContainer.setCenter(mainViewController.getView().getRoot());

        stage.sizeToScene();
        stage.show();
    }

    public static AchievementDAO getAchievementDAO() {
        return achievementDAO;
    }

    public static GameDAO getGameDAO() {
        return gameDAO;
    }
}