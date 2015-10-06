package sqlite.model;

/**
 * Created by risto on 23.09.2015.
 */
public class MainCategory {

    int id;
    String categoryName;

    public MainCategory() {
    }

    public MainCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public MainCategory(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}