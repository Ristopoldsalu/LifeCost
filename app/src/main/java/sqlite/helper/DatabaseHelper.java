package sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.risto.resources.DailyCost;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.MissingFormatArgumentException;

import sqlite.model.MainCategory;
import sqlite.model.Product;

/**
 * Created by risto on 23.09.2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "product_history.db";

    // Table Names
    private static final String TABLE_PRODUCT = "product";
    private static final String TABLE_MAINCATEGORY = "main_category";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";


    // PRODUCT Table - column names
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_MAINCATEGORY_ID = "maincategory_id";

    // MAINCATEGORY Table - column names
    private static final String KEY_MAINCATEGORY = "maincategory";


    // Table Create Statements
    // Product table create statement
    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE "
            + TABLE_PRODUCT + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            KEY_NAME + " TEXT, " +
            KEY_PRICE + " DOUBLE, " +
            KEY_CREATED_AT + " CURRENT_TIMESTAMP," +
            KEY_MAINCATEGORY_ID + " INT " + ");";

    // Maincategory table create statement
    private static final String CREATE_TABLE_MAINCATEGORY = "CREATE TABLE "
            + TABLE_MAINCATEGORY + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            KEY_MAINCATEGORY + " TEXT " + ");";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MAINCATEGORY);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL("INSERT INTO " + TABLE_MAINCATEGORY + " VALUES" + " (0,'Jahutoode');");
        db.execSQL("INSERT INTO " + TABLE_MAINCATEGORY + " VALUES" + " (1,'Liha');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAINCATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);

        onCreate(db);
    }


    //--------------------------  Product Table Methods  ---------------------------
    /*
    creating a product
     */
    public long createProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.getName());
        values.put(KEY_PRICE, product.getPrice());
        values.put(KEY_CREATED_AT, getDateTime());
        values.put(KEY_MAINCATEGORY_ID, product.getMainCategory_id());

        //insert row
        long product_id = db.insert(TABLE_PRODUCT, null, values);

        return product_id;
    }

    /*
    get single product
     */
    public Product getProduct(long product_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCT + " WHERE "
                + KEY_ID + " = " + product_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Product pr = new Product();
        pr.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        pr.setName((c.getString(c.getColumnIndex(KEY_NAME))));
        pr.setPrice((c.getDouble(c.getColumnIndex(KEY_PRICE))));
        pr.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
        pr.setMainCategory_id(c.getInt(c.getColumnIndex(KEY_MAINCATEGORY_ID)));

        return pr;
    }

    /*
 * getting all products
 * */
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCT;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Product pr = new Product();
                pr.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                pr.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                pr.setPrice((c.getDouble(c.getColumnIndex(KEY_PRICE))));
                pr.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                pr.setMainCategory_id(c.getInt(c.getColumnIndex(KEY_MAINCATEGORY_ID)));

                // adding to product list
                products.add(pr);
            } while (c.moveToNext());
        }

        return products;
    }

    /*
     * Updating a product
     */
    public int updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.getName());
        values.put(KEY_PRICE, product.getPrice());
        values.put(KEY_MAINCATEGORY_ID, product.getMainCategory_id());


        // updating row
        return db.update(TABLE_PRODUCT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(product.getId()) });
    }

    /*
     * Deleting a product
     */
    public void deleteProduct(long product_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCT, KEY_ID + " = ?",
                new String[] { String.valueOf(product_id) });
    }


    //--------------------------  MainCategory Table Methods  ---------------------------
    /*
     * Creating MainCategory
     */
    public long createMainCategory(String main) {
        SQLiteDatabase db = this.getWritableDatabase();

        MainCategory mainCategory = new MainCategory(main);

        ContentValues values = new ContentValues();
        values.put(KEY_MAINCATEGORY, main);
        values.put(KEY_ID, mainCategory.getId());

        // insert row
        long main_id = db.insert(TABLE_MAINCATEGORY, null, values);

        return main_id;
    }

    /*
     * Creating MainCategory
     */
    public MainCategory getMainCategoryByName(String maincategory) {

        String selectQuery = "SELECT  * FROM " + TABLE_MAINCATEGORY + " WHERE "
                + KEY_MAINCATEGORY + " = '" + maincategory + "'";

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        MainCategory mC = new MainCategory();
        mC.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        mC.setMainCategory(c.getString(c.getColumnIndex(KEY_MAINCATEGORY)));

        return mC;
    }

    /**
     * getting all tags
     * */
    public List<MainCategory> getAllMainCategorys() {
        List<MainCategory> mainCategories = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_MAINCATEGORY;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                MainCategory t = new MainCategory();
                t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                t.setMainCategory(c.getString(c.getColumnIndex(KEY_MAINCATEGORY)));

                // adding to mainCategorieslist
                mainCategories.add(t);
            } while (c.moveToNext());
        }
        return mainCategories;
    }

    /*
     * Updating a Category

    public int updateTag(SubCategory sub) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SUBCATEGORY, sub.getMainCategory_id());
        values.put(KEY_MAINCATEGORY_ID, sub.getMainCategoryID());

        // updating row
        return db.update(TABLE_SUBCATEGORY, values, KEY_ID + " = ?",
                new String[] { String.valueOf(sub.getId()) });
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
    */

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getDateTimeRequested(String string) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date(string);
        return dateFormat.format(date);
    }

    /*
     * Creating subCategory
     */
    public long createMainCategory(int id, String category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MAINCATEGORY, category);

        // insert row
        long sub_id = db.insert(TABLE_MAINCATEGORY, null, values);

        return sub_id;
    }

    public List<MainCategory> getAllMainCategories() {

        List<MainCategory> mainCategories = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MAINCATEGORY;
        SQLiteDatabase db = null;
        Log.e(LOG, selectQuery);


        db = this.getReadableDatabase();

        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                MainCategory t = new MainCategory();
                t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                t.setMainCategory(c.getString(c.getColumnIndex(KEY_MAINCATEGORY)));

                // adding to subCategorise list
                mainCategories.add(t);
            } while (c.moveToNext());
        }
        return mainCategories;
    }

    public DailyCost getDailyCost(String s) {
        SQLiteDatabase db = null;
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCT + " WHERE "
                + KEY_CREATED_AT + " = '" + s + "'";

        Log.e(LOG, selectQuery);


        db = this.getReadableDatabase();

        Cursor c = db.rawQuery(selectQuery, null);
        DailyCost dailyCost = new DailyCost();
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Product pr = new Product();
                pr.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                pr.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                pr.setPrice((c.getDouble(c.getColumnIndex(KEY_PRICE))));
                pr.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                pr.setMainCategory_id(c.getInt(c.getColumnIndex(KEY_MAINCATEGORY_ID)));

                // adding to subCategorise list
                dailyCost.addProducts(pr);
            } while (c.moveToNext());
        }
        return dailyCost;
    }
}
