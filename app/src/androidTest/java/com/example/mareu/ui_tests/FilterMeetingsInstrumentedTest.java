package com.example.mareu.ui_tests;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.R;
import com.example.mareu.controllers.activities.MainActivity;
import com.example.mareu.di.DI;
import com.example.mareu.service.FakeApiServiceGenerator;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.utils.RecyclerViewUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Amine K. on 28/01/20.
 */

@RunWith(AndroidJUnit4.class)
public class FilterMeetingsInstrumentedTest {

    //private MainActivity mMainActivity;
    private MeetingApiService mApiService;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        //mMainActivity = mActivityRule.getActivity();
        //assertThat(mMainActivity, notNullValue());

        //Avant de lancer l'application et les tests nous ajoutons 2 réunions à notre API
        mApiService = DI.getMeetingApiService();
        //Date : 23/01/2020 & Salle : C
        mApiService.getMeetings().add(FakeApiServiceGenerator.FAKE_MEETINGS.get(0));
        //Date : 29/01/2020 & Salle : A
        mApiService.getMeetings().add(FakeApiServiceGenerator.FAKE_MEETINGS.get(1));
    }

    @Test
    public void checkIfFilteringMeetingsIsWorking() {

        //On vérifie qu'on a bien 2 items au départ dans notre RecyclerView
        onView(ViewMatchers.withId(R.id.meetings_rv)).check(new RecyclerViewUtils.ItemCount(2));
        onView(withId(R.id.menu_activity_main_filter)).perform(click());
        onView(withId(R.id.dialog_dates_spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is("29/01/2020"))).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialog_rooms_spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)),
                is("A"))).inRoot(isPlatformPopup()).perform(click());
        onView(withId(R.id.dialog_confirm_button)).perform(click());
        //On vérifie qu'il ne nous reste plus qu'un item dans notre RecyclerView
        onView(withId(R.id.meetings_rv)).check(new RecyclerViewUtils.ItemCount(1));
        //et que cet item contient la date et la salle qu'on a filtrées
        onView(ViewMatchers.withId(R.id.meeting_date_txt)).check(matches(withText("29/01/2020")));
        onView(ViewMatchers.withId(R.id.meeting_icon_room)).check(matches(withText("A")));

    }
}
