package sqlite.model;

/**
 * Created by risto on 23.09.2015.
 */
public class Product {

    int id;
    String name;
    Double price;
    String createdAt;
    int subCategory;

    public Product() {
    }

    public Product(String name, Double price, int subCategory) {
        this.name = name;
        this.price = price;
        this.subCategory = subCategory;
    }

    public Product(int id, String createdAt, String name, Double price, int subCategory) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.price = price;
        this.subCategory = subCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
    }
}
