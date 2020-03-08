package practicumopdracht.data;

import practicumopdracht.models.Achievement;
import practicumopdracht.models.Game;

import java.time.LocalDate;

public class FakeGameDAO extends GameDAO {
    @Override
    public boolean save()
    {
        return true;
    }

    @Override
    public boolean load()
    {
        int fakeMasterId = 1;
        addOrUpdate(new Game("Non Tracked title (5)", 5, false));
        fakeMasterId = 2;
        addOrUpdate(new Game("Tracked title (27)", 27, true));
        fakeMasterId = -1;
        addOrUpdate(new Game("negative amount title", -5, true));
        return true;
    }
}
