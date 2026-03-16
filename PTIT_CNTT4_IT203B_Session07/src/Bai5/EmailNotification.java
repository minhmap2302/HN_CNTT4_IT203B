package Bai5;


public class EmailNotification implements NotificationService {

    @Override
    public void send(String message, String recipient) {

        System.out.println("Đã gửi email: " + message);
    }
}