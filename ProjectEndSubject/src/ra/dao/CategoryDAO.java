package ra.dao;

import ra.entity.Category;
import java.util.List;

public interface CategoryDAO {
    void save(Category c);
    List<Category> findAll();
}