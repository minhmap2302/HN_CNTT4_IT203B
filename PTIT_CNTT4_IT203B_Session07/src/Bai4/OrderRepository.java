package Bai4;

import java.util.List;

public interface OrderRepository {
    List<Order> findal();

    void save(Order order);
}
