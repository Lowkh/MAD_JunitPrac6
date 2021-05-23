package sg.edu.np.mad.madpractical;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.TestCase;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.RootMatchers.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)

public class ListActivityTest extends TestCase {

    public static ViewAction clickChildViewWithId(
            final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }
    @Rule
    public ActivityScenarioRule<ListActivity> listActivityActivityScenarioRule =
            new ActivityScenarioRule<>(ListActivity.class);

    @Test
    public void testRecyclerListVisible(){
        onView(withId(R.id.rv)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerListContent(){
        for(int i=0; i<20; i++) {
            onView(withId(R.id.rv)).perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
        }
    }

    @Test
    public void testRecyclerChild() {
        onView(withId(R.id.rv)).perform(RecyclerViewActions.actionOnItemAtPosition(10, clickChildViewWithId(R.id.img_profile)));
        onView(withText("VIEW")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.btnFollow)).perform(click()).check(matches(isDisplayed()));
    }

    public void tearDown() throws Exception {
    }
}
