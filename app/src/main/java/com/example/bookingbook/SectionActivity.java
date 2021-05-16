package com.example.bookingbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SectionActivity extends AppCompatActivity {

    private ImageView backBtn;
    private ConstraintLayout novelSection, nonfictionSection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        backBtn = findViewById(R.id.btn_back);
        novelSection= findViewById(R.id.section_novel);
        nonfictionSection = findViewById(R.id.section_non_fiction);

        // back button clicked
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // novel section clicked
        novelSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SectionActivity.this, NovelActivity.class));
            }
        });

        // non-fiction section clicked
        nonfictionSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SectionActivity.this, NonFictionActivity.class));
            }
        });

    }
}
