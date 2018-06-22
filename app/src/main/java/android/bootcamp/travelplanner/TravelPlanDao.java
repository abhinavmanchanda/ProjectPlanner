package android.bootcamp.travelplanner;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TravelPlanDao {
    @Query("SELECT * FROM travelplan") List<TravelPlan> getAll();
    @Insert void insertAll(TravelPlan... travelPlans);
    @Delete void delete(TravelPlan travelPlan);
}

