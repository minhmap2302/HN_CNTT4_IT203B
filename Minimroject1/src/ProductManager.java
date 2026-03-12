import java.util.*;
import java.util.stream.*;

public class ProductManager {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product p) throws InvalidProductException {
        if (products.stream().anyMatch(prod -> prod.getId() == p.getId())) {
            throw new InvalidProductException("Id invalid");
        }
        products.add(p);
    }

    public void displayProducts() {
        System.out.println("ID    Name            Price      Quantity   Category");
        products.forEach(System.out::println);
    }

    public void updateQuantity(int id, int newQuantity) throws InvalidProductException {
        Optional<Product> opt = products.stream().filter(p -> p.getId() == id).findFirst();
        if (opt.isPresent()) {
            opt.get().setQuantity(newQuantity);
        } else {
            throw new InvalidProductException("Can't find category with id: " + id);
        }
    }

    public void deleteOutOfStock() {
        products.removeIf(p -> p.getQuantity() == 0);
    }
}