package ra.service;

import ra.entity.Product;
import java.util.List;

public interface ProductService {
    void add(Product p);
    List<Product> getAll();
    void delete(int id);
    void update(Product p);
    List<Product> search(String keyword);
}