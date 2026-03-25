import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/transection";
        String user = "root";
        String password = "23022006";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);

            String checkSql = "SELECT Balance FROM Accounts WHERE AccountId = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);

            checkStmt.setString(1, "ACC01");
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("Balance");
                double amount = 1000;

                if (balance >= amount) {
                    CallableStatement cs1 = conn.prepareCall("{call sp_UpdateBalance(?,?)}");
                    CallableStatement cs2 = conn.prepareCall("{call sp_UpdateBalance(?,?)}");

                    cs1.setString(1, "ACC01");
                    cs1.setDouble(2, -amount);
                    cs1.execute();

                    cs2.setString(1, "ACC02");
                    cs2.setDouble(2, amount);
                    cs2.execute();

                    conn.commit();
                    System.out.println("Chuyển khoản thành công");
                } else {
                    System.out.println("Số dư không đủ");
                    conn.rollback();
                }
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}