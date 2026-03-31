package ra.service.impl;

import ra.dao.ProductDAO;
import ra.dao.impl.ProductDAOImpl;
import ra.entity.Product;
import ra.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public void add(Product p) {
        productDAO.save(p);
    }

    @Override
    public List<Product> getAll() {
        return productDAO.findAll();
    }

    @Override
    public void delete(int id) {
        productDAO.delete(id);
    }

    @Override
    public void update(Product p) {
        productDAO.update(p);
    }

    @Override
    public List<Product> search(String keyword) {
        return productDAO.searchByName(keyword);
    }
}