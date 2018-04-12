package android.bootcamp.travelplanner;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {TravelPlan.class}, version = 1)
public abstract class TravelPlannerDatabase extends RoomDatabase {
    public abstract TravelPlanDao travelPlanDao();
}

