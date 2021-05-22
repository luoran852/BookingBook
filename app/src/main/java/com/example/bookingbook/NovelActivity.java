package com.example.bookingbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class NovelActivity extends AppCompatActivity implements RecyclerViewAdapter.OnBookClickListener {

    private RecyclerView recyclerView_classic_novel, recyclerView_poetry,
            recyclerView_genre_novel, recyclerView_essay;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Items> books = new ArrayList<Items>();
    private ImageView backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel);

        backBtn = findViewById(R.id.btn_back);

//        for(int i=0; i<5; i++) {
//            books.add(new ItemBook(R.drawable.img_book_example));
//            books.add(new ItemBook(R.drawable.img_book_example2));
//        }

        //recyclerview 고전 소설
        recyclerView_classic_novel = (RecyclerView) findViewById(R.id.rv_classic_novel);
        adapter = new RecyclerViewAdapter(this, books, this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_classic_novel.setHasFixedSize(true);
        recyclerView_classic_novel.setLayoutManager(linearLayoutManager);
        recyclerView_classic_novel.setAdapter(adapter);

        //recyclerview 시집
        recyclerView_poetry = (RecyclerView)findViewById(R.id.rv_poetry);
        adapter = new RecyclerViewAdapter(this, books, this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_poetry.setHasFixedSize(true);
        recyclerView_poetry.setLayoutManager(linearLayoutManager);
        recyclerView_poetry.setAdapter(adapter);

        //recyclerview 장르 소설
        recyclerView_genre_novel = (RecyclerView)findViewById(R.id.rv_genre_novel);
        adapter = new RecyclerViewAdapter(this, books, this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_genre_novel.setHasFixedSize(true);
        recyclerView_genre_novel.setLayoutManager(linearLayoutManager);
        recyclerView_genre_novel.setAdapter(adapter);

        //recyclerview 에세이
        recyclerView_essay = (RecyclerView)findViewById(R.id.rv_essay);
        adapter = new RecyclerViewAdapter(this, books, this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_essay.setHasFixedSize(true);
        recyclerView_essay.setLayoutManager(linearLayoutManager);
        recyclerView_essay.setAdapter(adapter);

        // back button clicked
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void onBookClick(int position, ArrayList<Items> books) {
        Log.e(TAG, "onBookClick: 책 아이템이 클릭됨" + position);

        // 세부 액티비티로 이동
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra("bookList", books);
        intent.putExtra("position", position);
        startActivity(intent);
    }

}
