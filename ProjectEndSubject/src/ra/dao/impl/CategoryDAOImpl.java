package ra.dao.impl;

import ra.config.ConnectionDB;
import ra.dao.CategoryDAO;
import ra.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public void save(Category c) {
        String sql = "INSERT INTO categories(name) VALUES (?)";

        Connection conn = ConnectionDB.getInstance();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.executeUpdate();

            System.out.println("Thêm category thành công!");

        } catch (Exception e) {
            System.out.println("Lỗi hệ thống!");
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories";

        Connection conn = ConnectionDB.getInstance();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                list.add(c);
            }

        } catch (Exception e) {
            System.out.println("Lỗi hệ thống!");
        }

        return list;
    }
}