package ra.dao;

import ra.entity.OrderDetail;

import java.sql.Connection;
import java.util.List;

public interface OrderDetailDAO {
    void save(Connection conn, OrderDetail od);
    List<OrderDetail> findByOrderId(int orderId);
}