package ra.service.impl;

import ra.dao.OrderDetailDAO;
import ra.dao.impl.OrderDetailDAOImpl;
import ra.entity.OrderDetail;
import ra.service.OrderDetailService;

import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

    @Override
    public List<OrderDetail> findByOrderId(int orderId) {
        return orderDetailDAO.findByOrderId(orderId);
    }
}