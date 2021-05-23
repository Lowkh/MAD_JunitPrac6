package sg.edu.np.mad.madpractical;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

public class MainActivityIntentTest {

    @Rule
    public ActivityScenarioRule<ListActivity> rule = new ActivityScenarioRule<>(ListActivity.class);

    @Test
    public void passUserIdData() {
        final Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final Intent intent = new Intent(targetContext, MainActivity.class);
        intent.putExtra("id", 2);
        ActivityScenario.launch(intent);

        User targetUser = ListActivity.userList.get(2);

        onView(withId(R.id.txtName)).check((view, noViewFoundException) -> {
            String name = ((TextView) view).getText().toString();
            assertEquals(targetUser.name, name);
        });
    }

    @Test
    public void noUserIdData() {
        final Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final Intent intent = new Intent(targetContext, MainActivity.class);
        ActivityScenario.launch(intent);

        User targetUser = ListActivity.userList.get(0);

        onView(withId(R.id.txtName)).check((view, noViewFoundException) -> {
            String name = ((TextView) view).getText().toString();
            assertEquals(targetUser.name, name);
        });
    }
}
