package com.example.quiz_app_java;

import static com.example.quiz_app_java.QuestionsActivity.FILE_NAME;
import static com.example.quiz_app_java.QuestionsActivity.KEY_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<QuestionModel> bookmarksList;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        Toolbar toolbar = findViewById(R.id.toolbar);

//        loadAds();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bookmarks");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.rv_bookmarks);

        preferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        gson = new Gson();

        getBookmarks();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);



        BookmarksAdapter adapter = new BookmarksAdapter(bookmarksList);
        recyclerView.setAdapter(adapter);
    }

    protected void onPause(){
        super.onPause();
        storeBookmarks();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getBookmarks(){
        String json = preferences.getString(KEY_NAME, "");
        Type type = new TypeToken<List<QuestionModel>>(){}.getType();

        bookmarksList = gson.fromJson(json,type);

        if (bookmarksList == null){
            bookmarksList = new ArrayList<>();
        }
    }



    private void storeBookmarks(){
        String json = gson.toJson(bookmarksList);
        editor.putString(KEY_NAME,json);
        editor.commit();
    }
}