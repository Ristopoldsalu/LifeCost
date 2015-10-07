package sqlite.model;

/**
 * Created by risto on 23.09.2015.
 */
public class MainCategory {
    static int id_counter = 2;
    int id;
    String mainCategory;

    public MainCategory() {
    }

    public MainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
        this.id = id_counter;
        id_counter++;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }
}