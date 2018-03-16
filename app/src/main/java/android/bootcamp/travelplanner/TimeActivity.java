package android.bootcamp.travelplanner;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TimeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        TextView timeTextView = (TextView) findViewById(R.id.time);
        timeTextView.setText(String.valueOf(getIntent().getIntExtra("time", -1)));
    }
}
