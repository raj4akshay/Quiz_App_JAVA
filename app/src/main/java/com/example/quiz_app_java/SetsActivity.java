package com.example.quiz_app_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.List;


public class SetsActivity extends AppCompatActivity {

    private GridView gridView;
    private List<String> sets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

        gridView = findViewById(R.id.gridview);

        sets = com.example.quiz_app_java.CategoriesActivity.list.get(getIntent().getIntExtra("position", 0)).getSets();

        com.example.quiz_app_java.GridAdaptor adaptor = new com.example.quiz_app_java.GridAdaptor(sets,getIntent().getStringExtra("title"));
        gridView.setAdapter(adaptor);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}