package ra.service.impl;

import ra.dao.UserDAO;
import ra.dao.impl.UserDAOImpl;
import ra.entity.User;
import ra.service.UserService;
import ra.util.BCryptUtil;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public void register(User user) {

        boolean emailExists = userDAO.findAll()
                .stream()
                .anyMatch(u -> u.getEmail() != null && u.getEmail().equals(user.getEmail()));

        if (emailExists) {
            System.out.println("Email đã tồn tại!");
            return;
        }

        boolean phoneExists = userDAO.findAll()
                .stream()
                .anyMatch(u -> u.getPhone() != null && u.getPhone().equals(user.getPhone()));

        if (phoneExists) {
            System.out.println("SĐT đã tồn tại!");
            return;
        }
        User newUser = new User(user.getName(), user.getEmail(), user.getPhone(), user.getAddress(), BCryptUtil.hash(user.getPassword()), user.getRole());
        userDAO.save(newUser);
    }

    @Override
    public User login(String email, String password) {
        return userDAO.login(email, password);
    }
}