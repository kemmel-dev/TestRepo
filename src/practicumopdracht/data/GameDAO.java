package practicumopdracht.data;

import practicumopdracht.models.Game;

import java.util.ArrayList;
import java.util.List;

public class GameDAO implements DAO<Game> {

    protected List<Game> objects;

    public GameDAO()
    {
        objects = new ArrayList<>();
        load();
    }

    private int getUniqueID()
    {
        int highestId = -1;
        for (Game game : objects)
        {
            if (game.getId() > highestId)
            {
                highestId = game.getId();
            }
        }
        if (highestId == -1)
        {
            return 1;
        }
        return highestId + 1;
    }

    @Override
    public List<Game> getAll() {
        return List.copyOf(objects);
    }

    @Override
    public Game get(int id) {
        for(Game game : objects)
        {
            if (game.getId() == id)
            {
                return game;
            }
        }
        return null;
    }

    @Override
    public void addOrUpdate(Game object) {
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
    public void remove(Game object) {
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
