package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class MenuView extends View {

    private VBox menuBarContainer;
    private MenuBar menuBar;

    private Menu fileMenu;
    private Menu viewMenu;

    private MenuItem saveItem, closeItem, achievementViewItem, gameViewItem;

    public MenuView()
    {
        menuBarContainer = new VBox();

        fileMenu = new Menu("File");
        saveItem = new MenuItem("Save");
        closeItem = new MenuItem("Close");
        fileMenu.getItems().addAll(saveItem, closeItem);

        viewMenu = new Menu("View");
        achievementViewItem = new MenuItem("Achievement");
        gameViewItem = new MenuItem("Game");
        viewMenu.getItems().addAll(achievementViewItem, gameViewItem);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, viewMenu);

        menuBarContainer.getChildren().add(menuBar);
        menuBar.setPadding(new Insets(0, 0, 0, 0));
    }

    public Parent getRoot()
    {
        return menuBarContainer;
    }

    public MenuItem getSaveItem() {
        return saveItem;
    }

    public MenuItem getCloseItem() {
        return closeItem;
    }

    public MenuItem getAchievementViewItem() {
        return achievementViewItem;
    }

    public MenuItem getGameViewItem() {
        return gameViewItem;
    }
}
