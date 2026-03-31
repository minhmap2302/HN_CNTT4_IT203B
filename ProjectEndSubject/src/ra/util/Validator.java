package ra.util;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Validator {
    private static final Scanner sc = new Scanner(System.in);

    // ===== REQUIRED =====
    public static String inputRequired(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Lỗi - phải nhập dữ liệu!");
            } else {
                return input;
            }
        }
    }

    // ===== INT (SỐ DƯƠNG) =====
    public static int inputInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(sc.nextLine());

                if (value <= 0) {
                    System.out.println("Lỗi - phải nhập số dương!");
                } else {
                    return value;
                }

            } catch (Exception e) {
                System.out.println("Lỗi - phải nhập số!");
            }
        }
    }

    // ===== DOUBLE (GIÁ) =====
    public static double inputDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(sc.nextLine());

                if (value <= 0) {
                    System.out.println("Lỗi - phải nhập số dương!");
                } else {
                    return value;
                }

            } catch (Exception e) {
                System.out.println("Lỗi - phải nhập số!");
            }
        }
    }

    // ===== EMAIL =====
    public static String inputEmail(String message) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        while (true) {
            String email = inputRequired(message);

            if (!Pattern.matches(regex, email)) {
                System.out.println("Email không hợp lệ!");
            } else {
                return email;
            }
        }


    }

    // ===== PHONE =====
    public static String inputPhone(String message) {
        String regex = "^(0|\\+84)[0-9]{9}$";

        while (true) {
            String phone = inputRequired(message);

            if (!Pattern.matches(regex, phone)) {
                System.out.println("SĐT không hợp lệ!");
            } else {
                return phone;
            }
        }
    }
}