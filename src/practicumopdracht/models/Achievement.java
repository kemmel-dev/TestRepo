package practicumopdracht.models;

import java.time.LocalDate;

public class Achievement {
    private int id;
    private int masterId;
    private Game game;
    private int priority;
    private String title;
    private String description;
    private LocalDate dateAchieved;
    private boolean tracked;
    private boolean achieved;

    public Achievement(int masterId, Game game, String title, String description, boolean achieved, LocalDate dateAchieved, boolean tracked, int priority) {
        id = -1;
        this.masterId = masterId;
        this.game = game;
        this.priority = priority;
        this.title = title;
        this.description = description;
        this.dateAchieved = dateAchieved;
        this.tracked = tracked;
        this.achieved = achieved;
    }

    public Achievement(int masterId, String title, String description, LocalDate dateAchieved) {
        this(masterId, null, title, description, true, dateAchieved, false, 0);
    }

    public Achievement(int masterId, String title, String description, boolean tracked, int priority) {
        this(masterId, null, title, description, false, null, tracked, priority);
    }

    public Game belongsToGame() {
        return game;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDateAchievedString() {
        if (dateAchieved != null)
        {
            return dateAchieved.toString();
        }
        return "-";
    }

    public LocalDate getDateAchieved()
    {
        return dateAchieved;
    }

    public int getPriority()
    {
        return priority;
    }

    public String getTrackedString() {
        if (tracked)
        {
            return "X";
        }
        return "-";
    }

    public String getAchievedString() {
        if (achieved)
        {
            return "X";
        }
        return "-";
    }

    public boolean getAchieved()
    {
        return achieved;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void switchTracked() {
        tracked = !tracked;
    }

    public void switchAchieved() {
        achieved = !achieved;
        if (achieved)
        {
            dateAchieved = LocalDate.now();
        }
        else {
            dateAchieved = null;
        }
    }

    public Game getBelongsToGame()
    {
        return game;
    }

    public String getGameName()
    {
        return game.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMasterId() {
        return masterId;
    }

    public String toString()
    {
        return getTitle();
    }

    public boolean getTracked() {
        return tracked;
    }
}
