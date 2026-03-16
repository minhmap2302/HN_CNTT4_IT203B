package Bai2;

public class HolidayDiscount implements DiscountStrategy{
    @Override
    public double appliDiscount(double totalAmount) {
        return totalAmount-(totalAmount*15/100);
    }
}
