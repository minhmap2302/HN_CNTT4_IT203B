package ra.service;

import ra.entity.Category;
import java.util.List;

public interface CategoryService {
    void add(Category c);
    List<Category> getAll();
}