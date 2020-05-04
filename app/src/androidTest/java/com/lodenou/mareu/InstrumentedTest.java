package com.lodenou.mareu;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.lodenou.mareu.Controller.ActivityForm;
import com.lodenou.mareu.Controller.ActivityListMareu;
import com.lodenou.mareu.Controller.ActivityZoom;
import com.lodenou.mareu.Model.Reunion;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.PendingIntent.getActivity;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    private ActivityListMareu mActivity;

    public ActivityTestRule<ActivityListMareu> getActivityRule() {
        return mActivityRule;
    }

    @Rule
    public ActivityTestRule<ActivityListMareu> mActivityRule =
            new ActivityTestRule(ActivityListMareu.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        Intents.init();
    }


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.lodenou.mareu", appContext.getPackageName());
    }

    @Test
    public void activityFormWorks() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

        // check if we are in the ActivityForm
        intended(hasComponent(ActivityForm.class.getName()));

    }

    @Test
    public void addEmailButtonWorks(){
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.fields_2_form)).perform(replaceText("ffff@ff.fd"));
        onView(withId(R.id.email_button)).perform(click());
    }

    @Test
    public void activityZoomWorks() throws InterruptedException {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.fields_0_form)).perform(replaceText("Dddddd"), closeSoftKeyboard());
        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.fields_1_form),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.core.widget.NestedScrollView")),
                                        0),
                                4),
                        isDisplayed()));
        materialTextView.perform(click());
        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton.perform(scrollTo(), click());
        onView(withId(R.id.Spinner)).perform(click());
        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(3);
        appCompatCheckedTextView.perform(click());

        onView(withId(R.id.fields_2_form)).perform(replaceText("gggggg@ggg.dd"));
        Thread.sleep(500);
        onView(withId(R.id.email_button)).perform(click());
        Thread.sleep(500);

        // Swipe to see validate button
        Espresso.onView(ViewMatchers.withId(R.id.nested_scroll_view)).perform(ViewActions.swipeUp());
        onView(withId(R.id.validate_button)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.unique_item)).perform(click());

        // check if we are in the ActivityZoom
        intended(hasComponent(ActivityZoom.class.getName()));
    }

    @Test
    public void warningValidateToastWorks() {
        onView(withId(R.id.fab)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.nested_scroll_view)).perform(ViewActions.swipeUp());
        onView(withId(R.id.validate_button)).perform(click());
        onView(withText("Remplissez tous les champs")).inRoot(withDecorView(not(mActivityRule
                .getActivity()
                .getWindow()
                .getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void warningEmailButtonWorks() {
        onView(withId(R.id.fab)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.nested_scroll_view)).perform(ViewActions.swipeUp());
        onView(withId(R.id.email_button)).perform(click());
        onView(withText("Veuillez rentrer un email valide")).inRoot(withDecorView(not(mActivityRule
                .getActivity()
                .getWindow()
                .getDecorView())))
                .check(matches(isDisplayed()));
    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }


}
