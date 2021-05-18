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
    TextView txt_title, txt_year, txt_author, txt_translator, txt_publisher, txt_summary;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        img_book = (ImageView) findViewById(R.id.searched_detail_img);
        btn_rental = (Button) findViewById(R.id.btn_rental);
        btn_return = (Button) findViewById(R.id.btn_return);
        txt_title = (TextView) findViewById(R.id.txt_book_title);
        txt_year = (TextView) findViewById(R.id.txt_year);
        txt_author = (TextView) findViewById(R.id.searchedAuthor);
        txt_translator = (TextView) findViewById(R.id.searchedTranslator);
        txt_publisher = (TextView) findViewById(R.id.searchedPublisher);
        txt_summary = (TextView) findViewById(R.id.txt_storyline);

    }
}
