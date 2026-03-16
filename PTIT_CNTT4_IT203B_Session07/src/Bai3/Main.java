package Bai3;

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
        PercentageDiscount percentageDiscount=new PercentageDiscount();
        FixedDiscount fixedDiscount=new FixedDiscount();
        NoDiscount noDiscount=new NoDiscount();
        HolidayDiscount holidayDiscount=new HolidayDiscount();
        System.out.println("PercentageDiscount:"+percentageDiscount.appliDiscount(orderCalculator.calculate(order)));
        System.out.println("FixedDiscount:"+fixedDiscount.appliDiscount(orderCalculator.calculate(order)));
        System.out.println("NoDiscount:"+noDiscount.appliDiscount(orderCalculator.calculate(order)));
        System.out.println("HolidayDiscount:"+holidayDiscount.appliDiscount(orderCalculator.calculate(order)));
        PaymentProcessor processor = new PaymentProcessor();

        System.out.println("Thanh toán COD");
        PaymentMethod cod = new CODPayment();
        processor.processPayment(cod, 500000);
        System.out.println("\nThanh toán thẻ tín dụng");
        PaymentMethod card = new CreditCardPayment();
        processor.processPayment(card, 1000000);
        System.out.println("\nThanh toán MoMo");
        PaymentMethod momo = new MomoPayment();
        processor.processPayment(momo, 750000);
        PaymentMethod payment = new MomoPayment();
        processor.processPayment(payment, 300000);
    }
}
