package com.example.bookingbook;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NonFictionActivity extends AppCompatActivity {

    private RecyclerView recyclerView_history, recyclerView_philo, recyclerView_bio, recyclerView_sci;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ItemBook> books = new ArrayList<ItemBook>();
    private ImageView backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nonfiction);

        backBtn = findViewById(R.id.btn_back);

        for(int i=0; i<5; i++) {
            books.add(new ItemBook(R.drawable.img_book_example));
            books.add(new ItemBook(R.drawable.img_book_example2));
        }

        //recyclerview 역사
        recyclerView_history = (RecyclerView) findViewById(R.id.rv_history);
        adapter = new RecyclerViewAdapter(this, books);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_history.setHasFixedSize(true);
        recyclerView_history.setLayoutManager(linearLayoutManager);
        recyclerView_history.setAdapter(adapter);

        //recyclerview 철학
        recyclerView_philo = (RecyclerView)findViewById(R.id.rv_philosophy);
        adapter = new RecyclerViewAdapter(this, books);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_philo.setHasFixedSize(true);
        recyclerView_philo.setLayoutManager(linearLayoutManager);
        recyclerView_philo.setAdapter(adapter);

        //recyclerview 전기
        recyclerView_bio = (RecyclerView)findViewById(R.id.rv_biography);
        adapter = new RecyclerViewAdapter(this, books);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_bio.setHasFixedSize(true);
        recyclerView_bio.setLayoutManager(linearLayoutManager);
        recyclerView_bio.setAdapter(adapter);

        //recyclerview 과학
        recyclerView_sci = (RecyclerView)findViewById(R.id.rv_science);
        adapter = new RecyclerViewAdapter(this, books);

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
}
