package stack.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.stackoverflow.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbc;

    @Autowired
    public UserDAOImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    //Добавление нового пользователя в БД
    @Override
    public User addUser(User user) {
        Integer id = user.getId();
        if (id == null) {
            Integer userId = insertUserAndReturnId(user);
            return new User(userId, user.getLogin(), user.getPassword(), user.getEmail());
        } else {
            jdbc.update("update User set login=?, password=?, email=? where user_id=?",
                    user.getLogin(),
                    user.getPassword(),
                    user.getEmail(),
                    id);
        }
        return user;
    }

    private Integer insertUserAndReturnId(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbc).withTableName("User");
        jdbcInsert.setGeneratedKeyName("user_id");
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("login", user.getLogin());
        args.put("password", user.getPassword());
        args.put("email", user.getEmail());
        Integer userId = jdbcInsert.executeAndReturnKey(args).intValue();
        return userId;
    }

    //Метод для вывода полного списка юзеров
    @Override
    public List<User> listOfUser() {
        String sql = "SELECT user_id, login, password, email  FROM User";
        List<User> userList = jdbc.query(sql, new UserRowMapper());
        return userList;
    }

    //Удаление пользователя
    //Дописать
    @Override
    public void removeUser(Integer id) {

    }

    //Метод для вытаскивания из базы юзера по id
    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT user_id, login, password, email FROM User WHERE user_id = ?";
        return jdbc.queryForObject(
                sql,
                new UserRowMapper(),
                userId);
    }

    private static class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getInt("user_id"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("email"));
        }
    }
}
