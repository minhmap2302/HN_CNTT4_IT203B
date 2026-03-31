package ra.dao;

import ra.entity.OrderDetail;

import java.util.List;

public interface OrderDetailDAO {
    void save(OrderDetail od);
    List<OrderDetail> findByOrderId(int orderId);
}