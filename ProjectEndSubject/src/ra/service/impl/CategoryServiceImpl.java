package ra.service.impl;

import ra.dao.CategoryDAO;
import ra.dao.impl.CategoryDAOImpl;
import ra.entity.Category;
import ra.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public void add(Category c) {

        boolean exists = categoryDAO.findAll()
                .stream()
                .anyMatch(x -> x.getName().equalsIgnoreCase(c.getName()));

        if (exists) {
            System.out.println("Tên danh mục đã tồn tại!");
            return;
        }

        categoryDAO.save(c);
    }

    @Override
    public List<Category> getAll() {
        return categoryDAO.findAll();
    }
}