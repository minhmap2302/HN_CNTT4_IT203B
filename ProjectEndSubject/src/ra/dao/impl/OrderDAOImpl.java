package ra.dao.impl;

import ra.config.ConnectionDB;
import ra.dao.OrderDAO;
import ra.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public int createOrder(Order order) {
        String sql = "INSERT INTO orders(user_id, total_price, status) VALUES (?, ?, ?)";

        Connection conn = ConnectionDB.getInstance();

        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalPrice());
            ps.setString(3, "PENDING");

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Lỗi hệ thống!");
        }

        return -1;
    }

    @Override
    public List<Order> findAll() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        Connection conn = ConnectionDB.getInstance();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setUserId(rs.getInt("user_id"));
                o.setTotalPrice(rs.getDouble("total_price"));
                o.setStatus(rs.getString("status"));
                list.add(o);
            }

        } catch (Exception e) {
            System.out.println("Lỗi lấy đơn hàng!");
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (Exception ignored) {}
        }

        return list;
    }

    @Override
    public void updateStatus(int id, String status) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        Connection conn = ConnectionDB.getInstance();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();

            System.out.println("Cập nhật trạng thái thành công!");

        } catch (Exception e) {
            System.out.println("Lỗi cập nhật trạng thái!");
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception ignored) {}
        }
    }
}