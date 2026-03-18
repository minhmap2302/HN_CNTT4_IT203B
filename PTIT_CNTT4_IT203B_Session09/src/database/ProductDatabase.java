package database;

import entity.Product;

import java.util.*;

public class ProductDatabase {
    private static ProductDatabase instance;
    private final List<Product> products;

    private ProductDatabase() {
        products = new ArrayList<>();
    }

    public static ProductDatabase getInstance() {
        if (instance == null) {
            instance = new ProductDatabase();
        }
        return instance;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void updateProduct(String id, Product newProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.set(i, newProduct);
                return;
            }
        }
    }

    public void deleteProduct(String id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public List<Product> getAllProducts() {
        return products;
    }
}