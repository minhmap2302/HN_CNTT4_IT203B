package ra.presentation;

import ra.entity.User;
import ra.service.UserService;
import ra.service.impl.UserServiceImpl;
import ra.util.Validator;

import java.util.Scanner;

public class MainMenu {

    private final UserService userService = new UserServiceImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("\n==================================");
            System.out.println("        📱 PHONE STORE");
            System.out.println("==================================");
            System.out.println("1. Đăng ký");
            System.out.println("2. Đăng nhập");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("⚠ Vui lòng nhập số!");
                continue;
            }

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 0:
                    System.out.println("👋 Thoát chương trình!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("❌ Sai lựa chọn!");
            }
        }
    }

    // ================= REGISTER =================
    private void register() {
        System.out.println("\n========== ĐĂNG KÝ ==========");

        String name = Validator.inputRequired("Tên: ");
        String email = Validator.inputEmail("Email: ");
        String phone = Validator.inputPhone("SĐT: ");
        String address = Validator.inputRequired("Địa chỉ: ");
        String password = Validator.inputRequired("Mật khẩu: ");

        User user = new User(name, email, phone, address, password, "CUSTOMER");

        userService.register(user);

        System.out.println("✅ Đăng ký thành công!");
    }

    // ================= LOGIN =================
    private void login() {
        System.out.println("\n========== ĐĂNG NHẬP ==========");

        // ❗ Login không nên validate email quá gắt
        String email = Validator.inputRequired("Email: ");
        String password = Validator.inputRequired("Mật khẩu: ");

        User user = userService.login(email, password);

        if (user == null) {
            System.out.println("❌ Sai tài khoản hoặc mật khẩu!");
            return;
        }

        System.out.println("\n==================================");
        System.out.println("👋 Xin chào: " + user.getName());
        System.out.println("==================================");

        // ================= ROLE =================
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            System.out.println("👉 Đăng nhập ADMIN");
            new AdminMenu().display();
        } else {
            System.out.println("👉 Đăng nhập CUSTOMER");
            new CustomerMenu(user.getId()).display();
        }
    }
}