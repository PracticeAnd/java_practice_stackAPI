package stack.dao;


import ru.stackoverflow.entity.User;

import java.util.List;

public interface UserDAO {
    public User addUser(User user);

    public List<User> listOfUser();

    public void removeUser(Integer id);

    public User getUserById(Integer id);
}
