package sg.edu.np.mad.madpractical;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DBHandlerTest {

    @Rule
    public ActivityScenarioRule<ListActivity> rule = new ActivityScenarioRule<>(ListActivity.class);

    private Context appContext;
    private DBHandler dbHandler;
    private List<User> users;

    @Before
    public void createDBHandler() {
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dbHandler = new DBHandler(appContext);
        users =  dbHandler.getUsers();
        assertNotNull(users);
    }

    @Test
    public void getUsers_20UsersPresent() {
        assertEquals(20, users.size());
        for (User user : users) {
            assertNotNull(user);
            assertNotNull(user.id);
            assertNotNull(user.name);
            assertNotNull(user.description);
            assertNotNull(user.followed);
        }
    }

    @Test
    public void updateUser_ChangeFollowState() {
        User oldUser = users.get(0);
        oldUser.followed = !oldUser.followed;

        dbHandler.updateUser(oldUser);

        users = dbHandler.getUsers();
        assertNotNull(users);

        User newUser = users.stream()
                .filter(u -> u.id == oldUser.id)
                .findFirst()
                .orElse(null);

        assertNotNull(newUser);
        assertEquals(oldUser.id, newUser.id);
        assertEquals(oldUser.name, newUser.name);
        assertEquals(oldUser.description, newUser.description);
        assertEquals(oldUser.followed, newUser.followed);
    }
}
