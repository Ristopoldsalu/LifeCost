package com.example.risto.lifecost;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import sqlite.helper.DatabaseHelper;
import sqlite.model.MainCategory;

public class addProductActivity extends Activity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        db = new DatabaseHelper(this);
        System.out.println("creatib asja");
        fillData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main22, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void fillData() {

        List<MainCategory> categories = db.getAllMainCategories();
        List<String> categoriesstrings = new ArrayList<>();
        for (MainCategory category : categories) {
            categoriesstrings.add(category.getCategoryName());
        }
        System.out.println("teeb midagi");
        Spinner my_spinner = (Spinner) findViewById(R.id.spinner1);
        System.out.println("leidis spinneri");
        ArrayAdapter<String> my_Adapter = new ArrayAdapter<>(this, R.layout.spinner_row,
                categoriesstrings);
        System.out.println("my_adapter");
        my_spinner.setAdapter(my_Adapter);
        System.out.println("settis 2ra");



    }
}
