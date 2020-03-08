package practicumopdracht.views;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

public abstract class TemplateView extends View {

    public Menu fileMenu, viewMenu;
    public MenuItem saveMenuItem, closeMenuItem, achievementMenuItem, gameMenuItem;

    protected MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        fileMenu = new Menu("File");
        fileMenu.setDisable(false);
        saveMenuItem = new MenuItem("Save");
        saveMenuItem.setDisable(false);
        closeMenuItem = new MenuItem("Close");
        fileMenu.getItems().addAll(saveMenuItem, closeMenuItem);

        viewMenu = new Menu("View");
        achievementMenuItem = new MenuItem("Achievement");
        gameMenuItem = new MenuItem("Game");
        viewMenu.getItems().addAll(achievementMenuItem, gameMenuItem);


        menuBar.getMenus().addAll(fileMenu, viewMenu);
        menuBar.setPadding(new Insets(0, 0, 0, 0));
        return menuBar;
    }

    public Menu getFileMenu() {
        return fileMenu;
    }

    public Menu getViewMenu() {
        return viewMenu;
    }

    public MenuItem getSaveMenuItem() {
        return saveMenuItem;
    }

    public MenuItem getCloseMenuItem() {
        return closeMenuItem;
    }

    public MenuItem getAchievementMenuItem() {
        return achievementMenuItem;
    }

    public MenuItem getGameMenuItem() {
        return gameMenuItem;
    }
}
