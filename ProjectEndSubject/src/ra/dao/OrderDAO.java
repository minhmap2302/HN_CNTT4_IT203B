package ra.dao;

import ra.entity.Order;

import java.sql.Connection;
import java.util.List;

public interface OrderDAO {
    int createOrder(Connection conn, Order order);
    List<Order> findAll();
    void updateStatus(int id, String status);
    List<Order> findByUserId(int userId);
}