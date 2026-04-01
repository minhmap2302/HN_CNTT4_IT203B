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
            System.out.println("===== ADMIN =====");
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

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    showProducts();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    addCategory();
                    break;
                case 5:
                    showCategory();
                    break;
                case 6:
                    updateProduct();
                    break;
                case 7:
                    searchProduct();
                    break;
                case 8:
                    showOrders();
                    break;
                case 9:
                    updateOrderStatus();
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Sai lựa chọn!");
            }
        }
    }

    // ================= PRODUCT =================

    private void addProduct() {

        List<Category> categories = categoryService.getAll();

        if (categories.isEmpty()) {
            System.out.println("Chưa có danh mục! Hãy thêm category trước.");
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
                System.out.println("Category không tồn tại!");
            } else break;
        }

        Product p = new Product(name, brand, storage, color, price, stock, desc, cateId);
        productService.add(p);
    }

    private void showProducts() {
        List<Product> list = productService.getAll();

        if (list.isEmpty()) {
            System.out.println("Không có sản phẩm!");
            return;
        }

        System.out.println("--------------------------------------------------");
        System.out.printf("%-5s %-15s %-10s %-10s %-5s\n", "ID", "Tên", "Hãng", "Giá", "SL");
        System.out.println("--------------------------------------------------");

        for (Product p : list) {
            System.out.printf("%-5d %-15s %-10s %-10.0f %-5d\n",
                    p.getId(),
                    p.getName(),
                    p.getBrand(),
                    p.getPrice(),
                    p.getStock());
        }
    }

    private void deleteProduct() {
        if (categoryService.getAll().isEmpty()) {
            System.out.println("Không có sản phẩm để xóa!");
            return;
        }
        int id = Validator.inputInt("Nhập ID cần xóa: ");
        productService.delete(id);
    }

    private void updateProduct() {
        if (categoryService.getAll().isEmpty()) {
            System.out.println("Ko có sản phẩm!");
            return;
        }
        int id = Validator.inputInt("ID sản phẩm: ");

        boolean exists = productService.getAll()
                .stream()
                .anyMatch(p -> p.getId() == id);

        if (!exists) {
            System.out.println("Không tìm thấy sản phẩm!");
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

        int cateId = Validator.inputInt("Chọn category id: ");

        Product p = new Product();
        p.setId(id);
        p.setName(name);
        p.setBrand(brand);
        p.setStorage(storage);
        p.setColor(color);
        p.setPrice(price);
        p.setStock(stock);
        p.setDescription(desc);
        p.setCategoryId(cateId);

        productService.update(p);
    }

    private void searchProduct() {
        if (categoryService.getAll().isEmpty()) {
            System.out.println("Khong co san pham");
        }
        String keyword = Validator.inputRequired("Nhập tên cần tìm: ");

        List<Product> list = productService.search(keyword);

        if (list.isEmpty()) {
            System.out.println("Không tìm thấy!");
            return;
        }

        for (Product p : list) {
            System.out.println(
                    p.getId() + " | " +
                            p.getName() + " | " +
                            p.getBrand() + " | " +
                            p.getStorage() + " | " +
                            p.getColor() + " | " +
                            p.getPrice() + " | SL: " + p.getStock()
            );
        }
    }

    // ================= CATEGORY =================

    private void addCategory() {
        String name = Validator.inputRequired("Tên category: ");
        categoryService.add(new Category(name));
    }

    private void showCategory() {
        List<Category> list = categoryService.getAll();

        if (list.isEmpty()) {
            System.out.println("Chưa có danh mục!");
            return;
        }

        for (Category c : list) {
            System.out.println(c.getId() + " - " + c.getName());
        }
    }

    // ================= ORDER =================

    private void showOrders() {
        List<Order> orders = orderService.getAll();

        if (orders.isEmpty()) {
            System.out.println("Chưa có đơn hàng!");
            return;
        }

        for (Order o : orders) {
            System.out.println("=================================");
            System.out.println("Đơn " + o.getId() +
                    " | User: " + o.getUserId() +
                    " | Total: " + o.getTotalPrice() +
                    " | Status: " + o.getStatus());

            List<OrderDetail> details = orderDetailService.findByOrderId(o.getId());

            for (OrderDetail d : details) {
                Product p = productService.getAll()
                        .stream()
                        .filter(pr -> pr.getId() == d.getProductId())
                        .findFirst()
                        .orElse(null);

                if (p != null) {
                    System.out.println("   - " + p.getName() +
                            " | SL: " + d.getQuantity() +
                            " | Giá: " + d.getPrice());
                }
            }
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