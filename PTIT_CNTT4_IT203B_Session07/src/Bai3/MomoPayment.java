package Bai3;

public class MomoPayment implements EWalletPayable {

    @Override
    public void pay(double amount) {
        System.out.println("Xử lý thanh toán MoMo: " + (int)amount + " - Thành công");
    }
}
