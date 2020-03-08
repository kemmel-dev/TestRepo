package practicumopdracht.controllers;

import javafx.scene.control.Alert;
import practicumopdracht.MainApplication;
import practicumopdracht.views.MenuView;
import practicumopdracht.views.View;

public class MenuController extends Controller {
    private MenuView view;

    public MenuController()
    {
        this.view = new MenuView();
        initMenuHandling();
    }

    public void onSwitchView()
    {

    }

    public void initMenuHandling()
    {
        view.getAchievementViewItem().setOnAction(e -> switchViewToAchievement());
        view.getGameViewItem().setOnAction(e -> switchViewToGame());
    }

    public void switchViewToAchievement()
    {
        if (MainApplication.currentController instanceof AchievementController)
        {
            return;
        }
        MainApplication.switchController(MainApplication.achievementController);
    }

    public void switchViewToGame()
    {
        if (MainApplication.currentController instanceof GameController)
        {
            return;
        }
        MainApplication.switchController(MainApplication.gameController);
    }

    public View getView()
    {
        return view;
    }
}
