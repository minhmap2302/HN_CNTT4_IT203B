package Bai5;

public class SMSNotification implements NotificationService {

    @Override
    public void send(String message, String recipient) {

        System.out.println("Đã gửi SMS: " + message);
    }
}