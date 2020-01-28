package com.example.mareu.ui_tests;

import androidx.test.espresso.contrib.RecyclerViewActions;
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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.example.mareu.utils.RecyclerViewUtils.clickChildView;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Amine K. on 28/01/20.
 */

@RunWith(AndroidJUnit4.class)
public class DeleteMeetingInstrumentedTest {


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
        mApiService.getMeetings().add(FakeApiServiceGenerator.FAKE_MEETINGS.get(0));
        mApiService.getMeetings().add(FakeApiServiceGenerator.FAKE_MEETINGS.get(1));
    }

    @Test
    public void checkIfRemovingMeetingIsWorking() {

        //On vérifie qu'on a bien 2 items au départ dans notre RecyclerView
        onView(ViewMatchers.withId(R.id.meetings_rv)).check(new RecyclerViewUtils.ItemCount(2));
        //On clique sur le bouton Delete du premier item de notre RecyclerView
        onView(ViewMatchers.withId(R.id.meetings_rv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildView(R.id.meeting_delete_btn)));
        //On vérifie enfin qu'il ne nous reste plus qu'un item dans notre RecyclerView
        onView(withId(R.id.meetings_rv)).check(new RecyclerViewUtils.ItemCount(1));

    }

}
