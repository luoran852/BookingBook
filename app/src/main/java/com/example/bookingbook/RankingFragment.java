package com.example.bookingbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class RankingFragment extends Fragment implements RecyclerViewAdapter.OnBookClickListener {

    private RecyclerView recyclerView_ranking_all, recyclerView_ranking_novel, recyclerView_ranking_essay,
            recyclerView_ranking_bio, recyclerView_ranking_philo;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Items> books = new ArrayList<Items>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ranking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        for(int i=0; i<5; i++) {
//            books.add(new ItemBook(R.drawable.img_book_example));
//            books.add(new ItemBook(R.drawable.img_book_example2));
//        }

        //recyclerview 전체 랭킹 순위
        recyclerView_ranking_all = (RecyclerView)view.findViewById(R.id.rv_ranking);
        adapter = new RecyclerViewAdapter(getContext(), books, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_ranking_all.setHasFixedSize(true);
        recyclerView_ranking_all.setLayoutManager(linearLayoutManager);
        recyclerView_ranking_all.setAdapter(adapter);

        //recyclerview 소설장르 인기순위
        recyclerView_ranking_novel = (RecyclerView)view.findViewById(R.id.rv_ranking_novel);
        adapter = new RecyclerViewAdapter(getContext(), books, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_ranking_novel.setHasFixedSize(true);
        recyclerView_ranking_novel.setLayoutManager(linearLayoutManager);
        recyclerView_ranking_novel.setAdapter(adapter);

        //recyclerview 에세이장르 인기순위
        recyclerView_ranking_essay = (RecyclerView)view.findViewById(R.id.rv_ranking_essay);
        adapter = new RecyclerViewAdapter(getContext(), books, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_ranking_essay.setHasFixedSize(true);
        recyclerView_ranking_essay.setLayoutManager(linearLayoutManager);
        recyclerView_ranking_essay.setAdapter(adapter);

        //recyclerview 전기장르 인기순위
        recyclerView_ranking_bio = (RecyclerView)view.findViewById(R.id.rv_ranking_bio);
        adapter = new RecyclerViewAdapter(getContext(), books, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_ranking_bio.setHasFixedSize(true);
        recyclerView_ranking_bio.setLayoutManager(linearLayoutManager);
        recyclerView_ranking_bio.setAdapter(adapter);

        //recyclerview 철학장르
        recyclerView_ranking_philo = (RecyclerView)view.findViewById(R.id.rv_ranking_philo);
        adapter = new RecyclerViewAdapter(getContext(), books, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_ranking_philo.setHasFixedSize(true);
        recyclerView_ranking_philo.setLayoutManager(linearLayoutManager);
        recyclerView_ranking_philo.setAdapter(adapter);

    }


    @Override
    public void onBookClick(int position, ArrayList<Items> books) {
        Log.e(TAG, "onBookClick: 책 아이템이 클릭됨" + position);

        // 세부 액티비티로 이동
        Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
        intent.putExtra("bookList", books);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
