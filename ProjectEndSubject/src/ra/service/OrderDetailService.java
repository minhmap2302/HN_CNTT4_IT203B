package ra.service;

import ra.entity.OrderDetail;
import java.util.List;

public interface OrderDetailService {

    List<OrderDetail> findByOrderId(int orderId);

}