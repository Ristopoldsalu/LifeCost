package com.example.risto.lifecost;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sqlite.helper.DatabaseHelper;
import sqlite.model.MainCategory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper db = new DatabaseHelper(this);
        /*
        //Info that it works
        TextView tvName = (TextView)findViewById(R.id.textView);
        tvName.setText(db.getAllMainCategories().toString());
        //Info that it works
        TextView products = (TextView)findViewById(R.id.textView2);
        products.setText((CharSequence) db.getAllProducts().toString());
        */
        //add 5 days of expenses
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateTime dt = new DateTime();
        Log.e("TIIIIIMEMEEEEEE", dt.toString("yyyy-MM-dd  hh-mm-ss"));



        DateTime daysPast1 = dt.minusDays(0);
        TextView day5 = (TextView)findViewById(R.id.day5);
        double totalDailyCost1 = db.getDailyCost(dt.toString("yyyy-MM-dd")).getTotalCost();
        day5.setText(String.valueOf(totalDailyCost1));
        day5.setOnClickListener(new MyOnClickListener(dt.toString("yyyy-MM-dd")));



        DateTime daysPast2 = dt.minusDays(1);
        TextView day4 = (TextView)findViewById(R.id.day4);
        double totalDailyCost2 = db.getDailyCost(daysPast2.toString("yyyy-MM-dd")).getTotalCost();
        day4.setText(String.valueOf(totalDailyCost2));
        day4.setOnClickListener(new MyOnClickListener(daysPast2.toString("yyyy-MM-dd")));

        DateTime daysPast3 = dt.minusDays(2);
        TextView day3 = (TextView)findViewById(R.id.day3);
        double totalDailyCost3 = db.getDailyCost(daysPast3.toString("yyyy-MM-dd")).getTotalCost();
        day3.setText(String.valueOf(totalDailyCost3));
        day4.setOnClickListener(new MyOnClickListener(daysPast3.toString("yyyy-MM-dd")));


        DateTime daysPast4 = dt.minusDays(3);
        TextView day2 = (TextView)findViewById(R.id.day2);
        double totalDailyCost4 = db.getDailyCost(daysPast4.toString("yyyy-MM-dd")).getTotalCost();
        day2.setText(String.valueOf(totalDailyCost4));
        day4.setOnClickListener(new MyOnClickListener(daysPast4.toString("yyyy-MM-dd")));


        DateTime daysPast5 = dt.minusDays(4);
        TextView day1 = (TextView)findViewById(R.id.day1);
        double totalDailyCost5 = db.getDailyCost(daysPast5.toString("yyyy-MM-dd")).getTotalCost();
        day1.setText(String.valueOf(totalDailyCost5));
        day4.setOnClickListener(new MyOnClickListener(daysPast5.toString("yyyy-MM-dd")));



        //
        final Button addProductButton = (Button) findViewById(R.id.button);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("clicked");
                Intent myIntent = new Intent(v.getContext(), addProductActivity.class);
                startActivity(myIntent);


            }
            // Perform action on click
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    //OnClickListener for details of one day
    public class MyOnClickListener implements View.OnClickListener {

        String costDate;
        public MyOnClickListener(String costDate) {
            this.costDate = costDate;
        }

        @Override
        public void onClick(View v)
        {
            Intent myIntent = new Intent(v.getContext(), SingleDayActivity.class);
            myIntent.putExtra("date", costDate);
            startActivity(myIntent);
        }

    };

}
