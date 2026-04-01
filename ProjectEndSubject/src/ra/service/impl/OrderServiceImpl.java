package ra.service.impl;

import ra.config.ConnectionDB;
import ra.dao.OrderDAO;
import ra.dao.ProductDAO;
import ra.dao.impl.OrderDAOImpl;
import ra.dao.impl.ProductDAOImpl;
import ra.entity.Order;
import ra.entity.Product;
import ra.service.OrderService;
import ra.dao.OrderDetailDAO;
import ra.dao.impl.OrderDetailDAOImpl;
import ra.entity.OrderDetail;

import java.sql.Connection;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();
    private final OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

    @Override
    public void createOrder(int userId, int productId, int quantity) {

        Connection conn = ConnectionDB.getInstance();

        try {
            conn.setAutoCommit(false);

            Product p = productDAO.findAll()
                    .stream()
                    .filter(pr -> pr.getId() == productId)
                    .findFirst()
                    .orElse(null);

            if (p == null) {
                System.out.println("Không tìm thấy sản phẩm!");
                return;
            }

            if (p.getStock() < quantity) {
                System.out.println("Không đủ hàng!");
                return;
            }

            double total = p.getPrice() * quantity;

            Order order = new Order();
            order.setUserId(userId);
            order.setTotalPrice(total);

            int orderId = orderDAO.createOrder(conn, order);

            OrderDetail od = new OrderDetail();
            od.setOrderId(orderId);
            od.setProductId(productId);
            od.setQuantity(quantity);
            od.setPrice(p.getPrice());

            orderDetailDAO.save(conn, od);

            // trừ kho
            p.setStock(p.getStock() - quantity);
            productDAO.update(conn, p);

            conn.commit();
            System.out.println("Đặt hàng thành công! ID: " + orderId);

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Lỗi đặt hàng! Đã rollback!");
        }
    }

    @Override
    public void updateStatus(int id, String status) {
        boolean exists = orderDAO.findAll()
                .stream()
                .anyMatch(o -> o.getId() == id);

        if (!exists) {
            System.out.println("Không tìm thấy đơn hàng!");
            return;
        }

        orderDAO.updateStatus(id, status);
        System.out.println("Cập nhật trạng thái thành công!");
    }

    @Override
    public List<Order> getAll() {
        return orderDAO.findAll();
    }

    @Override
    public List<Order> getByUser(int userId) {
        return orderDAO.findByUserId(userId);
    }
}