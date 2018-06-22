package android.bootcamp.travelplanner;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class TravelPlan {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    private int distance;
    private int velocity;
    private int time;
    private int timeWithBuffer;

    public TravelPlan(int distance, int velocity, int time, int timeWithBuffer) {
        this.distance = distance;
        this.velocity = velocity;
        this.time = time;
        this.timeWithBuffer = timeWithBuffer;
    }

    public int getUid() { return uid;}
    public void setUid(int uid) {this.uid = uid;}
    public int getDistance() {return distance;}
    public void setDistance(int distance) {this.distance = distance;}
    public int getVelocity() {return velocity;}
    public void setVelocity(int velocity) {this.velocity = velocity;}
    public int getTime() {return time;}
    public void setTime(int time) { this.time = time;}
    public int getTimeWithBuffer() { return timeWithBuffer; }
    public void setTimeWithBuffer(int timeWithBuffer) { this.timeWithBuffer = timeWithBuffer; }
}