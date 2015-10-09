package com.example.risto.lifecost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.risto.resources.DailyCost;

import java.util.ArrayList;

import sqlite.helper.DatabaseHelper;
import sqlite.model.Product;

public class SingleDayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_day);
        Intent intent = getIntent();
        String dateTime = intent.getStringExtra("date");
        DatabaseHelper db = new DatabaseHelper(this);
        DailyCost dailyCost = db.getDailyCost(dateTime);
        ArrayList<Product> products = dailyCost.getProducts();

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.lineLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for( int i = 0; i < products.size(); i++ ) {
            TextView textView = new TextView(this);
            textView.setText(products.get(i).getName() + " " + products.get(i).getPrice());
            linearLayout.addView(textView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_day, menu);
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
}
