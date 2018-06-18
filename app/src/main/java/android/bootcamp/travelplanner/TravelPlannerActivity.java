package android.bootcamp.travelplanner;

import android.app.Activity;
import android.bootcamp.travelplanner.databinding.ActivityTravelPlannerBinding;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class TravelPlannerActivity extends Activity implements TravelPlannerView {

  public static final int TIME_ACTIVITY_REQUEST_CODE = 2831;
  public static final int CAMERA_REQUEST_CODE = 1105;
  private TravelPlannerPresenter presenter;
  private TravelPlannerRepository repository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    repository = new TravelPlannerRepository(getApplicationContext());
    presenter = new TravelPlannerPresenter(this, repository);
    ActivityTravelPlannerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_travel_planner);
    binding.setPresenter(presenter);
  }

  public void calculate(View view) {
    String distanceString = ((EditText) findViewById(R.id.distance)).getText().toString();
    String velocityString = ((EditText) findViewById(R.id.velocity)).getText().toString();
    presenter.calculate(distanceString, velocityString);
  }

  public void save(View view){
    String distance = (((EditText) findViewById(R.id.distance)).getText().toString());
    String velocity = (((EditText) findViewById(R.id.velocity)).getText().toString());
    String time = (((TextView) findViewById(R.id.time)).getText().toString());
    String timeWithBuffer = (((TextView) findViewById(R.id.time_with_buffer)).getText().toString());
    presenter.save(distance, velocity, time, timeWithBuffer);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(resultCode == RESULT_OK) {
      if(requestCode == TIME_ACTIVITY_REQUEST_CODE){
          presenter.processBufferReturned(data.getStringExtra(TimeActivity.TIME_WITH_BUFFER));
      } else {
        ImageView imageView = findViewById(R.id.capturedImage);
        imageView.setImageBitmap((Bitmap) data.getExtras().get("data"));

      }
    }
  }

  public void capture(View view) {
    presenter.capture();
  }

  @Override
  public void displayTime(String time) {
    TextView resultView = (TextView) findViewById(R.id.time);
    resultView.setText(time);
  }

  @Override
  public void launchTimeActivityWithTimeParameter(String time) {
    Intent intent = new Intent(this, TimeActivity.class);
    intent.putExtra("time", time);
    startActivityForResult(intent, TIME_ACTIVITY_REQUEST_CODE);

  }

  @Override
  public void launchCamera() {
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(intent, CAMERA_REQUEST_CODE );
  }

    @Override
    public void displayBuffer(String buffer) {
        TextView resultView = findViewById(R.id.time_with_buffer);
        resultView.setText(buffer);

    }
}
