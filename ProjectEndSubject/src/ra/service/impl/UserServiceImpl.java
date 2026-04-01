package ra.service.impl;

import ra.dao.UserDAO;
import ra.dao.impl.UserDAOImpl;
import ra.entity.User;
import ra.service.UserService;
import ra.util.BCryptUtil;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public void register(User user) {

        List<User> list = userDAO.findAll();

        // ===== CHECK EMAIL =====
        boolean emailExists = list.stream()
                .anyMatch(u -> u.getEmail() != null &&
                        u.getEmail().equalsIgnoreCase(user.getEmail()));

        if (emailExists) {
            System.out.println("Email đã tồn tại!");
            return;
        }

        // ===== CHECK PHONE =====
        boolean phoneExists = list.stream()
                .anyMatch(u -> u.getPhone() != null &&
                        u.getPhone().equals(user.getPhone()));

        if (phoneExists) {
            System.out.println("SĐT đã tồn tại!");
            return;
        }

        // ===== HASH PASSWORD =====
        String hashedPassword = BCryptUtil.hash(user.getPassword());

        User newUser = new User(
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                hashedPassword,
                user.getRole()
        );

        userDAO.save(newUser);

        System.out.println(" Đăng ký thành công!");
    }

    @Override
    public User login(String email, String password) {

        List<User> list = userDAO.findAll();

        for (User u : list) {
            if (u.getEmail() != null && u.getEmail().equalsIgnoreCase(email)) {

                // ===== VERIFY BCRYPT =====
                if (BCryptUtil.verify(password, u.getPassword())) {
                    return u;
                } else {
                    return null;
                }
            }
        }

        return null;
    }
}