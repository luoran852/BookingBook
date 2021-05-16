package com.example.bookingbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyPageFragment extends Fragment {

    private RecyclerView recyclerView_keep, recyclerView_record;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ItemBook> books = new ArrayList<ItemBook>();
    private Button btn_login; // 로그인 버튼

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mypage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for(int i=0; i<5; i++) {
            books.add(new ItemBook(R.drawable.img_book_example));
            books.add(new ItemBook(R.drawable.img_book_example2));
        }

        //recyclerview 대출 목록
        recyclerView_keep = (RecyclerView)view.findViewById(R.id.mypage_rvRanking_keep);
        adapter = new RecyclerViewAdapter(getContext(), books);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_keep.setHasFixedSize(true);
        recyclerView_keep.setLayoutManager(linearLayoutManager);
        recyclerView_keep.setAdapter(adapter);

        //recyclerview 찜 목록
        recyclerView_record = (RecyclerView)view.findViewById(R.id.mypage_rvRanking_record);
        adapter = new RecyclerViewAdapter(getContext(), books);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_record.setHasFixedSize(true);
        recyclerView_record.setLayoutManager(linearLayoutManager);
        recyclerView_record.setAdapter(adapter);

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

}
