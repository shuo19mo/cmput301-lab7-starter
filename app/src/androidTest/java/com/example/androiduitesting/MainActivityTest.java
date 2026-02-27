package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    // Test 1: Add city and verify it appears in list
    @Test
    public void addCityTest() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        onView(withText("Edmonton"))
                .check(matches(withText("Edmonton")));
    }

    // Test 2: Click city and verify ShowActivity displays correct name
    @Test
    public void openShowActivityTest() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Tokyo"));
        onView(withId(R.id.button_confirm)).perform(click());

        onView(withText("Tokyo")).perform(click());

        onView(withId(R.id.text_city_name))
                .check(matches(withText("Tokyo")));
    }

    // Test 3: Back button returns to MainActivity
    @Test
    public void backButtonTest() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Berlin"));
        onView(withId(R.id.button_confirm)).perform(click());

        onView(withText("Berlin")).perform(click());

        onView(withId(R.id.button_back)).perform(click());

        onView(withId(R.id.city_list))
                .check(matches(withId(R.id.city_list)));
    }
}