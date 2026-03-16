package Bai4;

public class FixedDiscount implements DiscountStrategy {
    @Override
    public double appliDiscount(double totalAmount) {
        return totalAmount-50000;
    }
}
