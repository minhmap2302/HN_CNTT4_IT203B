package Bai3;

public class OrderCalculator {
    public double calculate(Order Order ){
        double total=0;
        for(Product P: Order.getProducts()){
            total+=P.getPrice();
        }
        return total;
    }
}
