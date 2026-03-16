package Bai4;

public class OrderService {

    private OrderRepository orderRepository;
    private NotificationService notificationService;

    public OrderService(OrderRepository orderRepository,
                        NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
    }

    public void createOrder(Custome custome) {

        Order order = new Order(custome);

        orderRepository.save(order);

        notificationService.send(
                "Đơn hàng " + custome.name + " đã được tạo",
                "customer"
        );
    }
}