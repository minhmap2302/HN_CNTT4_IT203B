package Bai3;

public class CODPayment implements  CODPayable{
    @Override
    public void pay(double amount) {
        System.out.println("COD Payment of $" + amount + " to be paid on delivery");
    }
}
