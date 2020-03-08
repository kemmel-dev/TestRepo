package practicumopdracht.models;

public class Game {
    private String title;
    private int amountOfAchievements;
    private double percentageAchieved;
    private boolean tracked;
    private int id;

    public Game(String title, int amountOfAchievements, boolean tracked) {
        this.title = title;
        this.amountOfAchievements = amountOfAchievements;
        this.tracked = tracked;
    }

    @Override
    public String toString()
    {
        return new StringBuilder(title).append("\t(").append(amountOfAchievements).append(" achievements)").toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
