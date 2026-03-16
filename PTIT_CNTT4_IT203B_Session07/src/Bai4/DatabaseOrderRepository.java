package Bai4;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOrderRepository implements OrderRepository{
    static List<Order> orders=new ArrayList<>();
    @Override
    public List<Order> findal() {
        return orders;
    }

    @Override
    public void save(Order order) {
        orders.add(order);
        System.out.println("Lưu đơn hàng vào file: " + order.getId());
    }
}
