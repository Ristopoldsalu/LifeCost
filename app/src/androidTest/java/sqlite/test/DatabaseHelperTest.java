package sqlite.test;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;


import java.util.List;

import sqlite.helper.DatabaseHelper;
import sqlite.model.MainCategory;
import sqlite.model.Product;

/**
 * Created by risto on 06.10.2015.
 */
public class DatabaseHelperTest extends AndroidTestCase {
    private DatabaseHelper db;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new DatabaseHelper(context);
    }

    @Override
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }

       public void testAddEntry(){
            // Here i have my new database which is not connected to the standard database of the App
            db.getWritableDatabase();
            db.createMainCategory("maasikad");
            Product product = new Product("kartul", 12.45, 0);
            db.createProduct(product);
            List<Product> productList = db.getAllProducts();

            assertEquals("kartul", productList.get(0).getName());
    }

    public void shouldReturnCategoryID() {
        db.getWritableDatabase();
        db.createMainCategory("maasikad");
        MainCategory mainCategory = db.getMainCategoryByName("maasikad");
        assertEquals(0, mainCategory.getId());
    }



}