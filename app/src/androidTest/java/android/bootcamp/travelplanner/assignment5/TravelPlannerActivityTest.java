package android.bootcamp.travelplanner.assignment5;


import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityResult;
import android.bootcamp.travelplanner.R;
import android.bootcamp.travelplanner.TravelPlannerActivity;
import android.bootcamp.travelplanner.matchers.CustomEspressoMatcher;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.test.espresso.intent.ActivityResultFunction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.bootcamp.travelplanner.matchers.CustomEspressoMatcher.withDrawable;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class TravelPlannerActivityTest {


    @Rule
    public IntentsTestRule<TravelPlannerActivity> intentsTestRule =
            new IntentsTestRule<>(TravelPlannerActivity.class);
    
    @Test
    public void shouldOpenCameraOnClickingCapture() {
        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(createResultForCameraAction());

        onView(withId(R.id.capturedImage)).check(matches(not(withDrawable(R.mipmap.ic_launcher))));
        onView(withId(R.id.capture)).perform(click());
        intended(allOf(hasAction(equalTo(MediaStore.ACTION_IMAGE_CAPTURE))));
        onView(withId(R.id.capturedImage)).check(matches(withDrawable(R.mipmap.ic_launcher)));
    }

    public ActivityResult createResultForCameraAction(){
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", BitmapFactory.decodeResource(
                intentsTestRule.getActivity().getResources(), R.mipmap.ic_launcher));

        Intent resultData = new Intent();
        resultData.putExtras(bundle);

        return new ActivityResult(Activity.RESULT_OK, resultData);

    }
}