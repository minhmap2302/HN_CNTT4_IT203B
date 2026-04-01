package ra.dao.impl;

import ra.config.ConnectionDB;
import ra.dao.ProductDAO;
import ra.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public void save(Product p) {
        String sql = "INSERT INTO products(name, brand, storage, color, price, stock, description, category_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = ConnectionDB.getInstance();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, p.getName());
            ps.setString(2, p.getBrand());
            ps.setString(3, p.getStorage());
            ps.setString(4, p.getColor());
            ps.setDouble(5, p.getPrice());
            ps.setInt(6, p.getStock());
            ps.setString(7, p.getDescription());
            ps.setInt(8, p.getCategoryId());

            ps.executeUpdate();
            System.out.println("Thêm sản phẩm thành công!");

        } catch (Exception e) {
            System.out.println("Lỗi thêm sản phẩm!");
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception ignored) {}
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";

        Connection conn = ConnectionDB.getInstance();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setBrand(rs.getString("brand"));
                p.setStorage(rs.getString("storage"));
                p.setColor(rs.getString("color"));
                p.setPrice(rs.getDouble("price"));
                p.setStock(rs.getInt("stock"));
                p.setDescription(rs.getString("description"));
                p.setCategoryId(rs.getInt("category_id"));
                list.add(p);
            }

        } catch (Exception e) {
            System.out.println("Lỗi lấy danh sách sản phẩm!");
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (Exception ignored) {}
        }
        return list;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        Connection conn = ConnectionDB.getInstance();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Xóa thành công!");

        } catch (Exception e) {
            System.out.println("Không thể xóa!");
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception ignored) {}
        }
    }

    public void update(Connection conn, Product p) {
        String sql = "UPDATE products SET stock=? WHERE id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getStock());
            ps.setInt(2, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product p) {
        String sql = """
        UPDATE products 
        SET name=?, brand=?, storage=?, color=?, price=?, stock=?, description=?, category_id=? 
        WHERE id=?
    """;

        try (Connection conn = ConnectionDB.getInstance();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setString(2, p.getBrand());
            ps.setString(3, p.getStorage());
            ps.setString(4, p.getColor());
            ps.setDouble(5, p.getPrice());
            ps.setInt(6, p.getStock());
            ps.setString(7, p.getDescription());
            ps.setInt(8, p.getCategoryId());
            ps.setInt(9, p.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Lỗi cập nhật!");
        }
    }

    @Override
    public List<Product> searchByName(String keyword) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ?";

        Connection conn = ConnectionDB.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setBrand(rs.getString("brand"));
                p.setStorage(rs.getString("storage"));
                p.setColor(rs.getString("color"));
                p.setPrice(rs.getDouble("price"));
                p.setStock(rs.getInt("stock"));
                p.setDescription(rs.getString("description"));
                p.setCategoryId(rs.getInt("category_id"));
                list.add(p);
            }

        } catch (Exception e) {
            System.out.println("Lỗi tìm kiếm!");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (Exception ignored) {}
        }

        return list;
    }
}