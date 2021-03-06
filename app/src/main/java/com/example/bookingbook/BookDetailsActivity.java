package com.example.bookingbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class BookDetailsActivity extends AppCompatActivity implements Serializable {

    ImageView img_book;
    Button btn_rental, btn_return, btn_keep;
    TextView txt_title, txt_year, txt_author, txt_publisher, txt_summary, txt_isbn;
    String image, author, price, isbn, link, discount, publisher, description, title, pubdate;
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
        txt_isbn = findViewById(R.id.isbn);
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
        txt_isbn.setText(list.getIsbn());
        txt_summary.setText(list.getDescription());


        // 대출버튼 클릭
        btn_rental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                /*Log.d("Book Info", list.getImage() + list.getIsbn());*/
                //ISBN = txt_ISBN.getText().toString();
                image = list.getImage();
                title = txt_title.getText().toString();
                pubdate = txt_year.getText().toString();
                author = txt_author.getText().toString();
                publisher = txt_publisher.getText().toString();
                isbn = txt_isbn.getText().toString();
                description = txt_summary.getText().toString();

                price = list.getPrice();
                link = list.getLink();
                discount = list.getDiscount();

                Book book = new Book(image, author, price, isbn, link, discount, publisher, description, title, pubdate);

                sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
                String name = sharedPreferences.getString("login","noname");
                if(name.equals("noname"))
                {
                    Toast.makeText(getApplicationContext(), "로그인이 필요한 서비스입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    userReference.child("users").child(name).child("rental").child(isbn).setValue(book).addOnSuccessListener(new OnSuccessListener<Void>() {
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
            }
        });


        // 반납버튼 클릭
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                isbn = txt_isbn.getText().toString();

                sharedPreferences= getSharedPreferences("login", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
                String name = sharedPreferences.getString("login","noname");

                if(name.equals("noname"))
                {
                    Toast.makeText(getApplicationContext(), "로그인이 필요한 서비스 입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    userDatabase.getReference().child("users").child(name).child("rental").child(isbn).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
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
            }
        });

        // 찜버튼 클릭
        btn_keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                image = list.getImage();
                title = txt_title.getText().toString();
                pubdate = txt_year.getText().toString();
                author = txt_author.getText().toString();
                publisher = txt_publisher.getText().toString();
                isbn = txt_isbn.getText().toString();
                description = txt_summary.getText().toString();

                price = list.getPrice();
                link = list.getLink();
                discount = list.getDiscount();

                Book book = new Book(image, author, price, isbn, link, discount, publisher, description, title, pubdate);
                sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
                String name = sharedPreferences.getString("login","noname");

                if(name.equals("noname"))
                {
                    Toast.makeText(getApplicationContext(), "로그인이 필요한 서비스 입니다.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    userReference.child("users").child(name).child("favorite").child(isbn).setValue(book).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "Successfully kept.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "keep Failure", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

    }

    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }

}
