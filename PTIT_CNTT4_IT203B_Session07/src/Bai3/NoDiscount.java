package Bai3;

public class NoDiscount implements DiscountStrategy {

    @Override
    public double appliDiscount(double totalAmount) {
        return totalAmount;
    }
}
