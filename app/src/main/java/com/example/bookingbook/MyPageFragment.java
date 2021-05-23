package com.example.bookingbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class MyPageFragment extends Fragment implements RecyclerViewAdapter.OnBookClickListener {

    private RecyclerView recyclerView_keep, recyclerView_record;
    private RecyclerViewAdapter rentAdapter;
    private RecyclerViewAdapter keepAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Items> rentalBooks = new ArrayList<Items>();
    private ArrayList<Items> keptBooks = new ArrayList<Items>();
    private Button btn_login; // 로그인 버튼
    private TextView txt_keep, txt_record, txt_before_login;

    SharedPreferences sharedPreferences;
    private FirebaseDatabase userDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference userReference = userDatabase.getReference();
    String image, author, price, isbn, link, discount, publisher, description, title, pubdate;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mypage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("login", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        String name = sharedPreferences.getString("login","noname");

        DatabaseReference rental = userReference.child("users").child(name).child("rental");
        rental.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    image = ds.child("image").getValue(String.class);
                    title = ds.child("title").getValue(String.class);
                    pubdate = ds.child("pubdate").getValue(String.class);
                    author = ds.child("author").getValue(String.class);
                    publisher = ds.child("publisher").getValue(String.class);
                    isbn = ds.child("isbn").getValue(String.class);
                    description = ds.child("description").getValue(String.class);
                    price = ds.child("price").getValue(String.class);
                    link = ds.child("link").getValue(String.class);
                    discount = ds.child("discount").getValue(String.class);
                    rentalBooks.add(new Items(image, author, price, isbn, link, discount, publisher, description, title, pubdate));
                    rentAdapter.notifyDataSetChanged();
                    Log.d("rental", image);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference keep = userReference.child("users").child(name).child("rental");
        keep = userReference.child("users").child(name).child("favorite");
        keep.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    image = ds.child("image").getValue(String.class);
                    title = ds.child("title").getValue(String.class);
                    pubdate = ds.child("pubdate").getValue(String.class);
                    author = ds.child("author").getValue(String.class);
                    publisher = ds.child("publisher").getValue(String.class);
                    isbn = ds.child("isbn").getValue(String.class);
                    description = ds.child("description").getValue(String.class);
                    price = ds.child("price").getValue(String.class);
                    link = ds.child("link").getValue(String.class);
                    discount = ds.child("discount").getValue(String.class);
                    keptBooks.add(new Items(image, author, price, isbn, link, discount, publisher, description, title, pubdate));
                    keepAdapter.notifyDataSetChanged();
                    Log.d("keep", image);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //recyclerview 대출 목록
        recyclerView_keep = (RecyclerView)view.findViewById(R.id.mypage_rvRanking_keep);
        rentAdapter = new RecyclerViewAdapter(getContext(), rentalBooks, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_keep.setHasFixedSize(true);
        recyclerView_keep.setLayoutManager(linearLayoutManager);
        recyclerView_keep.setAdapter(rentAdapter);

        //recyclerview 찜 목록
        recyclerView_record = (RecyclerView)view.findViewById(R.id.mypage_rvRanking_record);
        keepAdapter = new RecyclerViewAdapter(getContext(), keptBooks, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_record.setHasFixedSize(true);
        recyclerView_record.setLayoutManager(linearLayoutManager);
        recyclerView_record.setAdapter(keepAdapter);

        txt_keep = (TextView) view.findViewById(R.id.txt_keep);
        txt_record = (TextView) view.findViewById(R.id.txt_record);
        txt_before_login = (TextView) view.findViewById(R.id.txt_before_login);

        // 비로그인 상태일 때 리싸이클러뷰 안 보이게
        if (name.equals("noname")) {
            txt_keep.setVisibility(View.INVISIBLE);
            txt_record.setVisibility(View.INVISIBLE);
            recyclerView_keep.setVisibility(View.INVISIBLE);
            recyclerView_record.setVisibility(View.INVISIBLE);
            txt_before_login.setVisibility(View.VISIBLE);
        }


        btn_login = (Button) getView().findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 화면으로 이동
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onBookClick(int position, ArrayList<Items> books) {
        Log.e(TAG, "onBookClick: 책 아이템이 클릭됨" + position);

        // 세부 액티비티로 이동
        Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
        intent.putExtra("bookList", (Serializable)books.get(position));
        intent.putExtra("position", position);
        startActivity(intent);
    }

}