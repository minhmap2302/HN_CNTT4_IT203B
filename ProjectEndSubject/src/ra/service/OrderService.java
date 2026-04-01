package ra.service;

import ra.entity.Order;
import java.util.List;

public interface OrderService {

    void createOrder(int userId, int productId, int quantity);

    List<Order> getAll();

    void updateStatus(int id, String status);
    List<Order> getByUser(int userId);
}