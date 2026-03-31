package ra.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import ra.config.ConnectionDB;
import ra.dao.UserDAO;
import ra.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users(name, email, phone, address, password, role) VALUES (?, ?, ?, ?, ?, ?)";



        try (Connection conn = ConnectionDB.getInstance();) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole());

            ps.executeUpdate();
            System.out.println("Đăng ký thành công!");

        } catch (Exception e) {
            System.out.println("Lỗi hệ thống!");
        }
    }

    @Override
    public User login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = ConnectionDB.getInstance();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");

                BCrypt.Result result = BCrypt.verifyer()
                        .verify(password.toCharArray(), dbPassword);

                if (result.verified) {

                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));

                    return user;
                }
            }

        } catch (Exception e) {
            System.out.println("Lỗi hệ thống!");
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";

        Connection conn = ConnectionDB.getInstance();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                list.add(u);
            }

        } catch (Exception e) {
            System.out.println("Lỗi hệ thống!");
        }
        return list;
    }
}