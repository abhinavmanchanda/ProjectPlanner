package android.bootcamp.travelplanner;

import android.databinding.ObservableField;

import static java.lang.Integer.parseInt;

public class TravelPlannerPresenter {

    private TravelPlannerView view;
    private TravelPlannerRepository repository;
    public final ObservableField<String> distance = new ObservableField<>();
    public final ObservableField<String> velocity = new ObservableField<>();
    public String time;

    public TravelPlannerPresenter(TravelPlannerView view, TravelPlannerRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void calculate(){
        time = String.valueOf(parseInt(distance.get()) / parseInt(velocity.get()));

        view.launchTimeActivityWithTimeParameter(time);
    }

    public void capture() {
        view.launchCamera();
    }


    public void processBufferReturned(String buffer) {
        view.displayBuffer(buffer);
    }

    public void save(String distance, String velocity, String time, String timeWithBuffer) {
        repository.savePlan(new TravelPlan(parseInt(distance), parseInt(velocity), parseInt(time), parseInt(timeWithBuffer)));
    }
}


