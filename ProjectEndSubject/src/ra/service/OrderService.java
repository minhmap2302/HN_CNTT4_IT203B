package ra.service;

import ra.entity.Order;
import java.util.List;

public interface OrderService {

    void createOrder(int userId, int productId, int quantity);

    List<Order> getAll();   // ✅ sửa lại dòng này

    void updateStatus(int id, String status);
}