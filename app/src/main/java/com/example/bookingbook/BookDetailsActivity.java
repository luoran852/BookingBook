package com.example.bookingbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class BookDetailsActivity extends AppCompatActivity implements Serializable {

    ImageView img_book;
    Button btn_rental, btn_return, btn_keep;
    TextView txt_title, txt_year, txt_author, txt_publisher, txt_summary, txt_ISBN;
    String title, author, publisher, ISBN, imageUrl;
    BitmapDrawable drawable;
    Bitmap bitmap;
    int position;

    private FirebaseDatabase userDatabase;
    private DatabaseReference userReference;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        // 각 책의 데이터
        Intent passedIntent = getIntent();
        Items list = (Items) passedIntent.getSerializableExtra("bookList");
        passedIntent.removeExtra("bookList");

        FirebaseApp.initializeApp(getApplicationContext()); // firebase 초기화
        userDatabase = FirebaseDatabase.getInstance();
        userReference = userDatabase.getReference();

        img_book = findViewById(R.id.searched_detail_img);
        drawable = (BitmapDrawable) img_book.getDrawable();
        bitmap = drawable.getBitmap();

        btn_keep = findViewById(R.id.btn_keep);
        btn_rental = findViewById(R.id.btn_rental);
        btn_return = findViewById(R.id.btn_return);

        txt_title = findViewById(R.id.txt_book_title);
        txt_year = findViewById(R.id.txt_year_detail);
        txt_author = findViewById(R.id.txt_author_detail);
        txt_publisher = findViewById(R.id.txt_publisher_detail);
        txt_ISBN = findViewById(R.id.isbn);
        txt_summary = findViewById(R.id.txt_storyline);


        position = passedIntent.getIntExtra("position", 0);
        passedIntent.removeExtra("position");

        // 아이템 정보 연결
        Glide.with(this)
                .load(list.getImage())
                .into(img_book);

        txt_title.setText(list.getTitle());
        txt_year.setText(list.getPubdate());
        txt_author.setText(list.getAuthor());
        txt_publisher.setText(list.getPublisher());
        //txt_ISBN.setText(list.get(position).isbn);
        txt_summary.setText(list.getDescription());


        // 대출버튼 클릭
        btn_rental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                title = txt_title.getText().toString();
                author = txt_author.getText().toString();
                publisher = txt_publisher.getText().toString();
                Log.d("Book Info", list.getImage() + list.getIsbn());
                //ISBN = txt_ISBN.getText().toString();

                Book book = new Book(title, author, publisher, ISBN, imageUrl);

                sharedPreferences= getSharedPreferences("login", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
                String name = sharedPreferences.getString("login","");
                userReference.child("users").child(ISBN).child(title).setValue(book).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Successfully rented.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "rental Failure", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        // 반납버튼 클릭
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                title = txt_title.getText().toString();
                author = txt_author.getText().toString();
                publisher = txt_publisher.getText().toString();
                ISBN = txt_ISBN.getText().toString();

                sharedPreferences= getSharedPreferences("login", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
                String name = sharedPreferences.getString("login","");

                userDatabase.getReference().child("users").child(name).child(ISBN).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Successfully returned", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "return Failure", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // 찜버튼 클릭
        btn_keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
