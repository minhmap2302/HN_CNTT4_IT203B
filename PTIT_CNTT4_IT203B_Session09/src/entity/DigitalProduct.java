package entity;

public class DigitalProduct extends Product {
    private final int sizeMB;
    public DigitalProduct(String id, String name, double price, int sizeMB) {
        super(id, name, price);
        this.sizeMB = sizeMB;
    }

    @Override
    public void displayInfo(){
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Size: " + sizeMB);
    }
}
