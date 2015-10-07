package com.example.risto.resources;

import java.util.ArrayList;

import sqlite.model.Product;

/**
 * Created by risto on 07.10.2015.
 */
public class DailyCost {

    double totalCost;
    ArrayList<Product> products = new ArrayList<>();

    public double getTotalCost() {
        for (Product product : products) {
            totalCost+= product.getPrice();
        }
        return totalCost;
    }

    public ArrayList getProducts() {
        return products;
    }

    public void setProducts(ArrayList products) {
        this.products = products;
    }

    public void addProducts(Product product) {
        products.add(product);
    }
}
