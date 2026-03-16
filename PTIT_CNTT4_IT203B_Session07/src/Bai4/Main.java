package Bai4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Dùng FileOrderRepository và EmailService");
        Custome custome=new Custome("HANOI", "a@example.com", "Nguyễn Văn A ");
        OrderRepository repo1 = new OrderHistory();
        NotificationService notify1 = new EmailService();

        OrderService service1 = new OrderService(repo1, notify1);
        service1.createOrder(custome);


        System.out.println("\nĐổi sang DatabaseOrderRepository và SMSNotification");

        OrderRepository repo2 = new DatabaseOrderRepository();
        NotificationService notify2 = new SMSService();

        OrderService service2 = new OrderService(repo2, notify2);
        service2.createOrder(custome);
    }
}
