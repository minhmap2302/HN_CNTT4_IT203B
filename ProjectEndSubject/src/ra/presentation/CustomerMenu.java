package ra.presentation;

import ra.entity.Product;
import ra.service.OrderService;
import ra.service.ProductService;
import ra.service.impl.OrderServiceImpl;
import ra.service.impl.ProductServiceImpl;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {

    private final ProductService productService = new ProductServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final Scanner sc = new Scanner(System.in);

    private int userId;

    public CustomerMenu(int userId) {
        this.userId = userId;
    }

    public void display() {
        while (true) {
            System.out.println("===== CUSTOMER =====");
            System.out.println("1. Xem sản phẩm");
            System.out.println("2. Đặt hàng");
            System.out.println("0. Thoát");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("⚠ Vui lòng nhập số!");
                continue;
            }

            switch (choice) {
                case 1:
                    showProducts();
                    break;
                case 2:
                    order();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void showProducts() {
        productService.getAll().stream()
                .filter(p -> p.getStock() > 0)
                .forEach(p -> System.out.println(
                        p.getId() + " | " +
                                p.getName() + " | " +
                                p.getPrice() + " | SL: " + p.getStock()));
    }

    private void order() {
        System.out.print("Nhập ID sản phẩm: ");
        int productId = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập số lượng: ");
        int quantity = Integer.parseInt(sc.nextLine());

        orderService.createOrder(userId, productId, quantity);
    }
}