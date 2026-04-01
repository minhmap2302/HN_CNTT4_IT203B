package ra.dao.impl;

import ra.config.ConnectionDB;
import ra.dao.UserDAO;
import ra.entity.User;
import ra.util.BCryptUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users(name, email, phone, address, password, role) VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = ConnectionDB.getInstance();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Lỗi hệ thống!");
        }
    }

    @Override
    public User login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ?";

        Connection conn = ConnectionDB.getInstance();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                String hashedPassword = rs.getString("password");
                user.setRole(rs.getString("role"));

                // Verify password
                if (BCryptUtil.verify(password, hashedPassword)) {
                    user.setPassword(null);
                    return user;
                } else {
                    return null;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
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

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        Connection conn = ConnectionDB.getInstance();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}