package Bai6;

public interface DiscountStrategy {
    double calculateDiscount(double amount);
    String getDescription();
}
