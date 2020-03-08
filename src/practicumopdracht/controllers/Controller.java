package practicumopdracht.controllers;
import practicumopdracht.views.View;

public abstract class Controller {
    public abstract View getView();

    public abstract void onSwitchView();

}
