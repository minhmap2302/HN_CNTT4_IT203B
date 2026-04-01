package ra.presentation;

import ra.entity.Product;
import ra.service.OrderService;
import ra.service.ProductService;
import ra.service.impl.OrderServiceImpl;
import ra.service.impl.ProductServiceImpl;
import ra.util.Validator;

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
        List<Product> list = productService.getAll()
                .stream()
                .filter(p -> p.getStock() > 0)
                .toList();

        if (list.isEmpty()) {
            System.out.println("\n⚠ Không có sản phẩm!\n");
            return;
        }

        System.out.println("\n================ DANH SÁCH SẢN PHẨM ================\n");

        System.out.printf("%-5s %-20s %-10s %-10s %-10s %-10s %-5s\n",
                "ID", "Tên", "Hãng", "Dung lượng", "Màu", "Giá", "SL");

        System.out.println("--------------------------------------------------------------------------");

        for (Product p : list) {
            System.out.printf("%-5d %-20s %-10s %-10s %-10s %-10s %-5d\n",
                    p.getId(),
                    p.getName(),
                    p.getBrand(),
                    p.getStorage(),
                    p.getColor(),
                    String.format("%,.0f", p.getPrice()),
                    p.getStock()
            );
        }

        System.out.println();
    }

    private void order() {
        int productId = Validator.inputInt("Nhập ID sản phẩm: ");
        int quantity = Validator.inputInt("Nhập số lượng: ");

        orderService.createOrder(userId, productId, quantity);
    }
}