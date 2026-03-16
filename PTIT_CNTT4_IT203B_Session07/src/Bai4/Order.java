package Bai4;

import java.util.ArrayList;
import java.util.List;

public class Order {
    static int count=1;
    String id;
    Custome custome;
    List<Product> products=new ArrayList<>();

    public Order( Custome custome) {
        this.id = "SODER"+count++;
        this.custome = custome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Custome getCustome() {
        return custome;
    }

    public void setCustome(Custome custome) {
        this.custome = custome;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public void addoder(Product p){
        products.add(p);
    }
}
