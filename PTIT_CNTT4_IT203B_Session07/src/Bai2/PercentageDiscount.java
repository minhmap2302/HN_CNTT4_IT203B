package Bai2;

public class PercentageDiscount implements DiscountStrategy{

    @Override
    public double appliDiscount(double totalAmount) {
        return totalAmount-(totalAmount*10/100);
    }
}
