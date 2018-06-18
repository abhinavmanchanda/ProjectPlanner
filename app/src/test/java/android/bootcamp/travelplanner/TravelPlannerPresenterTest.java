package android.bootcamp.travelplanner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TravelPlannerPresenterTest {

    @Mock
    private TravelPlannerView mockView;
    private TravelPlannerPresenter presenter;
    @Mock
    private TravelPlannerRepository repository;

    @Before
    public void setup(){
        presenter = new TravelPlannerPresenter(mockView, repository);
    }
    @Test
    public void shouldCalculateTimeFromDistanceAndVelocityAndDisplayItOnTheView() {
        presenter.distance.set("50");
        presenter.velocity.set("10");
        presenter.calculate();
        assertEquals("5", presenter.time.get());
    }

    @Test
    public void shouldCalculateTimeAndSendItToTimeActivity(){
        presenter.distance.set("50");
        presenter.velocity.set("10");
        presenter.calculate();
        verify(mockView).launchTimeActivityWithTimeParameter("5");
    }

    @Test
    public void shouldLaunchCameraOnClickingCapture(){
        presenter.capture();
        verify(mockView).launchCamera();

    }

    @Test
    public void displayBufferOnBeingReturnedFromTimeActivity(){
        presenter.processBufferReturned("30");
        assertEquals("30", presenter.timeWithBuffer.get());
    }

    @Test
    public void saveToRepositoryOnClickingSave(){
        presenter.save("10", "3", "3", "6");
        verify(repository).savePlan(new TravelPlan(10, 3, 3, 6));

    }

}