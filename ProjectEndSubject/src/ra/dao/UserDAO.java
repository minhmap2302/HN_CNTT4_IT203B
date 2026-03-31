package ra.dao;

import ra.entity.User;
import java.util.List;

public interface UserDAO {
    void save(User user);
    User login(String email, String password);
    List<User> findAll();
}