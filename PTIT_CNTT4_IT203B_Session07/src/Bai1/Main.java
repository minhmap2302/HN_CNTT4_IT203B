package Bai1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        Product SP1 = new Product("SP01", "LAPTOP", 15000000);
        Product SP2 = new Product("SP02", "CHUOT", 300000);
        Custome custome = new Custome("HANOI", "a@example.com", "Nguyễn Văn A ");
        Order order = new Order(custome);
        order.products.add(SP1);
        order.products.add(SP2);
        order.products.add(SP2);
        OrderCalculator orderCalculator = new OrderCalculator();
        OrderRepository orderRepository = new OrderRepository();
        System.out.println("Tong tien: " + orderCalculator.calculate(order));
        orderRepository.save(order);
    }
}
