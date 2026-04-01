package ra.presentation;

import ra.entity.*;
import ra.service.*;
import ra.service.impl.*;
import ra.util.Validator;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private final ProductService productService = new ProductServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final OrderDetailService orderDetailService = new OrderDetailServiceImpl();
    private final Scanner sc = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("\n===== ADMIN =====");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Hiển thị sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Thêm danh mục");
            System.out.println("5. Hiển thị danh mục");
            System.out.println("6. Sửa sản phẩm");
            System.out.println("7. Tìm sản phẩm");
            System.out.println("8. Xem đơn hàng");
            System.out.println("9. Cập nhật trạng thái");
            System.out.println("10. Thoát");
            System.out.print("Chọn: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("⚠ Phải nhập số!");
                continue;
            }

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> showProducts();
                case 3 -> deleteProduct();
                case 4 -> addCategory();
                case 5 -> showCategory();
                case 6 -> updateProduct();
                case 7 -> searchProduct();
                case 8 -> showOrders();
                case 9 -> updateOrderStatus();
                case 10 -> {
                    return;
                }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    // ================= PRODUCT =================

    private void addProduct() {
        List<Category> categories = categoryService.getAll();

        if (categories.isEmpty()) {
            System.out.println("⚠ Chưa có danh mục!");
            return;
        }

        String name = Validator.inputRequired("Tên: ");
        String brand = Validator.inputRequired("Hãng: ");
        String storage = Validator.inputRequired("Dung lượng: ");
        String color = Validator.inputRequired("Màu: ");
        double price = Validator.inputDouble("Giá: ");
        int stock = Validator.inputInt("Số lượng: ");
        String desc = Validator.inputRequired("Mô tả: ");

        showCategory();

        int cateId;
        while (true) {
            cateId = Validator.inputInt("Chọn category id: ");

            int finalCateId = cateId;
            boolean exists = categories.stream()
                    .anyMatch(c -> c.getId() == finalCateId);

            if (!exists) {
                System.out.println("❌ Category không tồn tại!");
            } else break;
        }

        Product p = new Product(name, brand, storage, color, price, stock, desc, cateId);
        productService.add(p);
    }

    private void showProducts() {
        List<Product> list = productService.getAll();

        if (list.isEmpty()) {
            System.out.println("⚠ Không có sản phẩm!");
            return;
        }

        System.out.println("\n================ DANH SÁCH SẢN PHẨM ================\n");

        System.out.printf("%-5s %-15s %-10s %-10s %-10s %-15s %-5s\n",
                "ID", "Tên", "Hãng", "Dung lượng", "Màu", "Giá", "SL");

        System.out.println("--------------------------------------------------------------------------");

        for (Product p : list) {
            System.out.printf("%-5d %-15s %-10s %-10s %-10s %-15s %-5d\n",
                    p.getId(),
                    p.getName(),
                    p.getBrand(),
                    p.getStorage(),
                    p.getColor(),
                    String.format("%,.0f", p.getPrice()), // format tiền đúng
                    p.getStock()
            );
        }

        System.out.println("\n====================================================\n");
    }

    private void deleteProduct() {
        List<Product> list = productService.getAll();

        if (list.isEmpty()) {
            System.out.println("⚠ Không có sản phẩm!");
            return;
        }

        int id = Validator.inputInt("Nhập ID cần xóa: ");

        boolean exists = list.stream().anyMatch(p -> p.getId() == id);

        if (!exists) {
            System.out.println("❌ ID không tồn tại!");
            return;
        }

        productService.delete(id);
    }

    private void updateProduct() {
        List<Product> products = productService.getAll();

        if (products.isEmpty()) {
            System.out.println("⚠ Không có sản phẩm!");
            return;
        }

        int id = Validator.inputInt("ID sản phẩm: ");

        Product old = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (old == null) {
            System.out.println("❌ Không tìm thấy sản phẩm!");
            return;
        }

        String name = Validator.inputRequired("Tên: ");
        String brand = Validator.inputRequired("Hãng: ");
        String storage = Validator.inputRequired("Dung lượng: ");
        String color = Validator.inputRequired("Màu: ");
        double price = Validator.inputDouble("Giá: ");
        int stock = Validator.inputInt("Số lượng: ");
        String desc = Validator.inputRequired("Mô tả: ");

        List<Category> categories = categoryService.getAll();
        showCategory();

        int cateId;
        while (true) {
            cateId = Validator.inputInt("Chọn category id: ");

            int finalCateId = cateId;
            boolean exists = categories.stream()
                    .anyMatch(c -> c.getId() == finalCateId);

            if (!exists) {
                System.out.println("Category không tồn tại!");
            } else break;
        }

        Product p = new Product(id, name, brand, storage, color, price, stock, desc, cateId);
        productService.update(p);
    }

    private void searchProduct() {
        List<Product> all = productService.getAll();

        if (all.isEmpty()) {
            System.out.println("⚠ Không có sản phẩm!");
            return;
        }

        String keyword = Validator.inputRequired("Nhập tên cần tìm: ");
        List<Product> list = productService.search(keyword);

        if (list.isEmpty()) {
            System.out.println("Không tìm thấy!");
            return;
        }

        list.forEach(p -> System.out.println(
                p.getId() + " | " +
                        p.getName() + " | " +
                        p.getBrand() + " | " +
                        p.getStorage() + " | " +
                        p.getColor() + " | " +
                        String.format("%,.0f", p.getPrice()) + " | SL: " + p.getStock()
        ));
    }

    // ================= CATEGORY =================

    private void addCategory() {
        String name = Validator.inputRequired("Tên category: ");
        categoryService.add(new Category(name));
    }

    private void showCategory() {
        List<Category> categories = categoryService.getAll();
        List<Product> products = productService.getAll();

        if (categories.isEmpty()) {
            System.out.println("\n⚠ Chưa có danh mục!\n");
            return;
        }

        System.out.println("\n=========== DANH MỤC ===========");
        System.out.printf("%-5s | %-20s | %-10s\n", "ID", "Tên", "Số SP");
        System.out.println("------------------------------------------------");

        for (Category c : categories) {

            long count = products.stream()
                    .filter(p -> p.getCategoryId() == c.getId())
                    .count();

            System.out.printf("%-5d | %-20s | %-10d\n",
                    c.getId(),
                    c.getName(),
                    count);
        }

        System.out.println("================================================\n");
    }

    // ================= ORDER =================

    private void showOrders() {
        List<Order> orders = orderService.getAll();

        if (orders == null || orders.isEmpty()) {
            System.out.println("\n⚠ Không có đơn hàng!\n");
            return;
        }

        List<Product> products = productService.getAll();

        System.out.println("\n================== DANH SÁCH ĐƠN HÀNG ==================\n");

        for (Order o : orders) {
            System.out.println("=======================================================");
            System.out.println("Đơn hàng ID: " + o.getId());
            System.out.println("User ID   : " + o.getUserId());
            System.out.println("Tổng tiền : " + String.format("%,.0f", o.getTotalPrice()) + " VND");
            System.out.println("Trạng thái: " + o.getStatus());

            System.out.println("\n------------------ CHI TIẾT SẢN PHẨM ------------------");
            System.out.printf("%-5s %-20s %-10s %-10s %-10s\n",
                    "ID", "Tên", "SL", "Giá", "Thành tiền");
            System.out.println("-------------------------------------------------------");

            List<OrderDetail> details = orderDetailService.findByOrderId(o.getId());

            double totalCheck = 0;

            for (OrderDetail d : details) {
                Product p = products.stream()
                        .filter(pr -> pr.getId() == d.getProductId())
                        .findFirst()
                        .orElse(null);

                if (p != null) {
                    double sub = d.getQuantity() * d.getPrice();
                    totalCheck += sub;

                    System.out.printf("%-5d %-20s %-10d %-10s %-10s\n",
                            p.getId(),
                            p.getName(),
                            d.getQuantity(),
                            String.format("%,.0f", d.getPrice()),
                            String.format("%,.0f", sub));
                }
            }

            System.out.println("-------------------------------------------------------");
            System.out.println("✔ Tổng kiểm tra: " + String.format("%,.0f", totalCheck) + " VND");
            System.out.println("=======================================================\n");
        }
    }

    private void updateOrderStatus() {
        int id = Validator.inputInt("ID đơn: ");

        System.out.println("1. SHIPPING");
        System.out.println("2. DELIVERED");
        System.out.println("3. CANCELLED");

        int choice = Validator.inputInt("Chọn: ");

        String status = switch (choice) {
            case 1 -> "SHIPPING";
            case 2 -> "DELIVERED";
            case 3 -> "CANCELLED";
            default -> "PENDING";
        };

        orderService.updateStatus(id, status);
    }
}