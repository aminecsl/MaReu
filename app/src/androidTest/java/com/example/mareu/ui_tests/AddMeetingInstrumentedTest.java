package com.example.mareu.ui_tests;

import androidx.test.espresso.contrib.PickerActions;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.R;
import com.example.mareu.controllers.activities.MainActivity;
import com.example.mareu.di.DI;
import com.example.mareu.utils.RecyclerViewUtils;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AddMeetingInstrumentedTest {


    private MainActivity mMainActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    private int currentMeetingsSize;

    @Before
    public void setUp() {
        mMainActivity = mActivityRule.getActivity();
        assertThat(mMainActivity, notNullValue());
    }

    @Test
    public void checkIfRecyclerViewIsEmpty() {
        onView(ViewMatchers.withId(R.id.meetings_rv)).check(new RecyclerViewUtils.ItemCount(0));
    }

    @Test
    public void checkIfAddingNewMeetingIsWorking() {
        currentMeetingsSize = DI.getMeetingApiService().getMeetings().size();
        // set date to 10 days ahead
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 10);

        onView(withId(R.id.floatingActionButton)).perform(click());
        onView(withId(R.id.new_meeting_subject_input)).perform(typeText("Embauche"));
        onView(withId(R.id.new_meeting_date_input)).perform(click());
        // set date to datePicker dialog
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH)+1,
                        calendar.get(Calendar.DAY_OF_MONTH)));
        // click ok on the datePicker dialog
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.new_meeting_time_input)).perform(scrollTo(), click());
        // set time to timePicker dialog
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(
                        11,
                        30));
        // click ok on the timePicker dialog
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.activity_meeting_emails_input)).perform(scrollTo(), replaceText("abcd@gmail.com"));
        onView(withId(R.id.activity_meeting_add_chip_btn)).perform(scrollTo(), click());
        onView(withId(R.id.menu_activity_new_meeting_save)).perform(click());
        onView(withId(R.id.meetings_rv)).check(new RecyclerViewUtils.ItemCount(currentMeetingsSize + 1));
    }

}
