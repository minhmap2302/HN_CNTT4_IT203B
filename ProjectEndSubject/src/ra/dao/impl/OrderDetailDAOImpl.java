package ra.dao.impl;

import ra.config.ConnectionDB;
import ra.dao.OrderDetailDAO;
import ra.entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public void save(OrderDetail od) {
        String sql = "INSERT INTO order_details(order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        Connection conn = ConnectionDB.getInstance();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, od.getOrderId());
            ps.setInt(2, od.getProductId());
            ps.setInt(3, od.getQuantity());
            ps.setDouble(4, od.getPrice());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Lỗi hệ thống!");
        }
    }

    @Override
    public List<OrderDetail> findByOrderId(int orderId) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM order_details WHERE order_id = ?";

        Connection conn = ConnectionDB.getInstance();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setOrderId(rs.getInt("order_id"));
                od.setProductId(rs.getInt("product_id"));
                od.setQuantity(rs.getInt("quantity"));
                od.setPrice(rs.getDouble("price"));
                list.add(od);
            }

        } catch (Exception e) {
            System.out.println("Lỗi lấy chi tiết đơn!");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (Exception ignored) {}
        }

        return list;
    }
}