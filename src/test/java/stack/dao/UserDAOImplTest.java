package stack.dao;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.stackoverflow.entity.User;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Оля on 06.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JdbcConfig.class)
public class UserDAOImplTest {

    @Autowired
    UserDAO userDAO;

    @Test
    @Transactional
    public void addUser() throws Exception {
        User user = new User(null, "newUser", "password", "new@mail.ru");
        User saved = userDAO.addUser(user);
        assertUser(4, saved);
        assertUser(4, userDAO.getUserById(5));
    }

    @Test
    @Transactional
    public void listOfUser() throws Exception {
        List<User> users = userDAO.listOfUser();
        assertEquals(4, users.size());
        assertUser(0, users.get(0));
        assertUser(1, users.get(1));
        assertUser(2, users.get(2));
        assertUser(3, users.get(3));
    }

    @Test
    @Transactional
    public void getUserById() throws Exception {
        assertUser(0, userDAO.getUserById(1));
        assertUser(1, userDAO.getUserById(2));
        assertUser(2, userDAO.getUserById(3));
        assertUser(3, userDAO.getUserById(4));

    }

    private static void assertUser(int expectedUserIndex, User actual) {
        User expected = USERS[expectedUserIndex];
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getLogin(), actual.getLogin());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getEmail(), actual.getEmail());
    }

    private static User[] USERS = new User[6];

    @BeforeClass
    public static void before() {
        USERS[0] = new User(1, "habuma", "password", "craig@habuma.com");
        USERS[1] = new User(2, "mwalls", "password", "mwalls@habuma.com");
        USERS[2] = new User(3, "chuck", "password", "chuck@habuma.com");
        USERS[3] = new User(4, "artnames", "password", "art@habuma.com");
        USERS[4] = new User(5, "newUser", "password", "new@mail.ru");
        USERS[5] = new User(4, "arthur", "letmein", "arthur@habuma.com");
    }

}