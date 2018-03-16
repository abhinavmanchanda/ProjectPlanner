package android.bootcamp.travelplanner;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class TravelPlannerActivity extends Activity {

  public static final int TIME_ACTIVITY_REQUEST_CODE = 2831;
  public static final int CAMERA_REQUEST_CODE = 1105;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_travel_planner);
  }

  public void calculate(View view) {
    int distance = Integer.parseInt(((EditText) findViewById(R.id.distance)).getText().toString());
    int velocity = Integer.parseInt(((EditText) findViewById(R.id.velocity)).getText().toString());
    int result = distance / velocity;
    TextView resultView = (TextView) findViewById(R.id.time);
    String resultString = String.valueOf(result);
    resultView.setText(resultString);

    Intent intent = new Intent(this, TimeActivity.class);
    intent.putExtra("time", result);
    startActivityForResult(intent, TIME_ACTIVITY_REQUEST_CODE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(resultCode == RESULT_OK) {
      if(requestCode == TIME_ACTIVITY_REQUEST_CODE){
        TextView resultView = (TextView) findViewById(R.id.time_with_buffer);
        resultView.setText("Value with buffer = " + String.valueOf(data.getIntExtra(TimeActivity.TIME_WITH_BUFFER, -1)));
      } else {
        ImageView imageView = findViewById(R.id.capturedImage);
        imageView.setImageBitmap((Bitmap) data.getExtras().get("data"));

      }
    }
  }

  public void capture(View view) {
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(intent, CAMERA_REQUEST_CODE );
  }
}
