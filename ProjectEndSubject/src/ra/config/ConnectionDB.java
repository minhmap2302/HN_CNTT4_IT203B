package ra.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {

    private static Connection connection;

    private ConnectionDB() {}

    public static Connection getInstance() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/phone_store_db",
                        "root",
                        "23022006"
                );
                System.out.println("Kết nối DB thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}