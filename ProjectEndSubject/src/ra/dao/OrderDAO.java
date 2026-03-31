package ra.dao;

import ra.entity.Order;

import java.util.List;

public interface OrderDAO {
    int createOrder(Order order);
    List<Order> findAll();
    void updateStatus(int id, String status);
}