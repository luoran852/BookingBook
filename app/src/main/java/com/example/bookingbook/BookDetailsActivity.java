package com.example.bookingbook;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BookDetailsActivity extends AppCompatActivity {

    ImageView img_book;
    Button btn_rental, btn_return;
    TextView txt_title, txt_author, txt_translator, txt_publisher, txt_summary;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);


    }
}
