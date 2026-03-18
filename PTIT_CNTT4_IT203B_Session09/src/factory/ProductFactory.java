package factory;


import entity.DigitalProduct;
import entity.PhysicalProduct;
import entity.Product;

public class ProductFactory {
    public static PhysicalProduct createPhysicalProduct(String id, String name, double price, double weight) {
        return new PhysicalProduct(id, name, price, weight);
    }

    public static DigitalProduct createDigitalProduct(String id, String name, double price, int sizeMB) {
        return new DigitalProduct(id, name, price, sizeMB);
    }
}
