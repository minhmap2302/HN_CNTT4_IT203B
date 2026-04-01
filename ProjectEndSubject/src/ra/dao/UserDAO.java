package ra.dao;

import ra.entity.User;
import java.util.List;

public interface UserDAO {
    void save(User user);
    User login(String email, String password);
    User findByEmail(String email);
    List<User> findAll();
}