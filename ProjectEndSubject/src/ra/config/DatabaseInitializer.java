package ra.config;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void main(String[] args) {
        try (Connection conn = ConnectionDB.getInstance();
             Statement stmt = conn.createStatement()) {

            // USERS
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100),
                    email VARCHAR(100) UNIQUE,
                    phone VARCHAR(20),
                    address VARCHAR(255),
                    password VARCHAR(255),
                    role ENUM('ADMIN','CUSTOMER')
                )
            """);

            // CATEGORIES
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS categories (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100)
                )
            """);

            // PRODUCTS
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS products (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100),
                    brand VARCHAR(50),
                    storage VARCHAR(50),
                    color VARCHAR(50),
                    price DOUBLE,
                    stock INT,
                    description TEXT,
                    category_id INT,
                    FOREIGN KEY (category_id) REFERENCES categories(id)
                )
            """);

            // ORDERS
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS orders (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    user_id INT,
                    total_price DOUBLE,
                    status ENUM('PENDING','SHIPPING','DELIVERED','CANCELLED'),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                )
            """);

            // ORDER DETAILS
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS order_details (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    order_id INT,
                    product_id INT,
                    quantity INT,
                    price DOUBLE,
                    FOREIGN KEY (order_id) REFERENCES orders(id),
                    FOREIGN KEY (product_id) REFERENCES products(id)
                )
            """);

            System.out.println("Tạo bảng thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}