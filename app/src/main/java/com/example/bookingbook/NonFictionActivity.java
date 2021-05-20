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

public class NonFictionActivity extends AppCompatActivity implements RecyclerViewAdapter.OnBookClickListener {

    private RecyclerView recyclerView_history, recyclerView_philo, recyclerView_bio, recyclerView_sci;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Items> books = new ArrayList<Items>();
    private ImageView backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nonfiction);

        backBtn = findViewById(R.id.btn_back);

//        for(int i=0; i<5; i++) {
//            books.add(new ItemBook(R.drawable.img_book_example));
//            books.add(new ItemBook(R.drawable.img_book_example2));
//        }

        //recyclerview 역사
        recyclerView_history = (RecyclerView) findViewById(R.id.rv_history);
        adapter = new RecyclerViewAdapter(this, books, this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_history.setHasFixedSize(true);
        recyclerView_history.setLayoutManager(linearLayoutManager);
        recyclerView_history.setAdapter(adapter);

        //recyclerview 철학
        recyclerView_philo = (RecyclerView)findViewById(R.id.rv_philosophy);
        adapter = new RecyclerViewAdapter(this, books, this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_philo.setHasFixedSize(true);
        recyclerView_philo.setLayoutManager(linearLayoutManager);
        recyclerView_philo.setAdapter(adapter);

        //recyclerview 전기
        recyclerView_bio = (RecyclerView)findViewById(R.id.rv_biography);
        adapter = new RecyclerViewAdapter(this, books, this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_bio.setHasFixedSize(true);
        recyclerView_bio.setLayoutManager(linearLayoutManager);
        recyclerView_bio.setAdapter(adapter);

        //recyclerview 과학
        recyclerView_sci = (RecyclerView)findViewById(R.id.rv_science);
        adapter = new RecyclerViewAdapter(this, books, this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_sci.setHasFixedSize(true);
        recyclerView_sci.setLayoutManager(linearLayoutManager);
        recyclerView_sci.setAdapter(adapter);

        // back button clicked
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public void onBookClick(int position, ArrayList<Items> books) {
        Log.e(TAG, "onBookClick: 책 아이템이 클릭됨" + position);

        // 세부 액티비티로 이동
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra("bookList", books);
        startActivity(intent);
    }
}
