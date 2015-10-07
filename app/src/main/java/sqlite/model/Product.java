package sqlite.model;

import android.database.sqlite.SQLiteDatabase;

import sqlite.helper.DatabaseHelper;

/**
 * Created by risto on 23.09.2015.
 */
public class Product {

    int id;
    String name;
    Double price;
    String createdAt;
    int mainCategory_id;

    public Product() {
    }

    public Product(String name, Double price, int mainCategory) {
        this.name = name;
        this.price = price;
        this.mainCategory_id = mainCategory;
    }

    public Product(int id, String createdAt, String name, Double price, int mainCategory_id) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.price = price;
        this.mainCategory_id = mainCategory_id;
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

    public int getMainCategory_id() {
        return mainCategory_id;
    }

    public void setMainCategory_id(int mainCategory_id) {
        this.mainCategory_id = mainCategory_id;
    }
}
