package practicumopdracht.data;

import practicumopdracht.models.Achievement;
import practicumopdracht.models.Game;

import java.util.ArrayList;
import java.util.List;

public class AchievementDAO implements DAO<Achievement> {

    protected List<Achievement> objects;

    public AchievementDAO()
    {
        objects = new ArrayList<>();
        load();
    }

    public List<Achievement> getAllFor(Game game)
    {
        List<Achievement> achievementsThatBelongToGame = new ArrayList<>();
        for (Achievement achievement : objects)
        {
            if (achievement.getBelongsToGame() == game)
            {
                achievementsThatBelongToGame.add(achievement);
            }
        }
        return achievementsThatBelongToGame;
    }

    private int getUniqueID()
    {
        int highestId = -1;
        for (Achievement achievement : objects)
        {
            if (achievement.getId() > highestId)
            {
                highestId = achievement.getId();
            }
        }
        if (highestId == -1)
        {
            return 1;
        }
        return highestId + 1;
    }

    @Override
    public List<Achievement> getAll() {
        return List.copyOf(objects);
    }

    @Override
    public Achievement get(int id) {
        for(Achievement achievement : objects)
        {
            if (achievement.getId() == id)
            {
                return achievement;
            }
        }
        return null;
    }

    @Override
    public void addOrUpdate(Achievement object) {
        if (object.getId() < 1)
        {
            object.setId(getUniqueID());
            objects.add(object);
            return;
        }
        int indexOfObject = objects.indexOf(get(object.getId()));
        objects.remove(indexOfObject);
        objects.add(indexOfObject, object);
    }

    @Override
    public void remove(Achievement object) {
        objects.remove(objects.indexOf(object));
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        return false;
    }
}
