package android.bootcamp.travelplanner;

public interface TravelPlannerView {

    void launchTimeActivityWithTimeParameter(String time);

    void launchCamera();

    void displayBuffer(String buffer);
}
