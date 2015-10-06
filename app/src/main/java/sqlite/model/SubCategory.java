package sqlite.model;

/**
 * Created by risto on 23.09.2015.
 */
public class SubCategory {

    int id;
    String subCategory;
    int mainCategoryID;

    public SubCategory() {
    }

    public SubCategory(String subCategory, int mainCategory) {
        this.subCategory = subCategory;
        this.mainCategoryID = mainCategory;
    }

    public SubCategory(int id, String subCategory, int mainCategory) {
        this.id = id;
        this.subCategory = subCategory;
        this.mainCategoryID = mainCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public int getMainCategoryID() {
        return mainCategoryID;
    }

    public void setMainCategoryID(int mainCategoryID) {
        this.mainCategoryID = mainCategoryID;
    }
}
