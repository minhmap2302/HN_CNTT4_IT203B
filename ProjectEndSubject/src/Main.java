import ra.presentation.MainMenu;

public class Main {
    public static void main(String[] args) {
        try {
            new MainMenu().display();
        } catch (Exception e) {
            System.err.println("Lỗi khi chạy ứng dụng: " + e.getMessage());
            e.printStackTrace();
        }
    }
}