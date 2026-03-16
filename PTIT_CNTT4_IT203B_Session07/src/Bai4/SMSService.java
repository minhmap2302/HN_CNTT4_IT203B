package Bai4;

public class SMSService implements NotificationService{
    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi email: "+message);
    }
}
