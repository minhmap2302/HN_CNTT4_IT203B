package ra.dao;

import ra.entity.Product;
import java.util.List;

public interface ProductDAO {
    void save(Product p);
    List<Product> findAll();
    void delete(int id);
    void update(Product p);
    List<Product> searchByName(String keyword);
}