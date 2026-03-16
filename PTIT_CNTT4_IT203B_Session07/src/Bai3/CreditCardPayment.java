package Bai3;

public class CreditCardPayment implements CardPayable {
    @Override
    public void pay(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng: " + (int)amount + " - Thành công");
    }
}
