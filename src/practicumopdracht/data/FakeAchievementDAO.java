package practicumopdracht.data;

import practicumopdracht.models.Achievement;

import java.time.LocalDate;

public class FakeAchievementDAO extends AchievementDAO {
    @Override
    public boolean save()
    {
        return true;
    }

    @Override
    public boolean load()
    {
        int fakeMasterId = 1;
        addOrUpdate(new Achievement(fakeMasterId, "Test Title 1", "This achievement is not tracked nor is it achieved, it belongs to game " + fakeMasterId, null));
        fakeMasterId = 2;
        addOrUpdate(new Achievement(fakeMasterId, "Test Title 2", "This achievement is tracked, with priority 3, it belongs to game " + fakeMasterId, true, 3));
        fakeMasterId = -1;
        addOrUpdate(new Achievement(fakeMasterId, "Test Title 3", "This achievement is not tracked, but is achieved on today, it belongs to game " + fakeMasterId, LocalDate.now()));
        return true;
    }
}
