package com.example.bookingbook;

import android.content.Intent;
import android.os.Bundle;
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

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView_classic_novel, recyclerView_poetry, recyclerView_essay,
            recyclerView_history, recyclerView_philo;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ItemBook> books = new ArrayList<ItemBook>();
    private ConstraintLayout section;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        section = (ConstraintLayout)view.findViewById(R.id.section_click);

        for(int i=0; i<5; i++) {
            books.add(new ItemBook(R.drawable.img_book_example));
            books.add(new ItemBook(R.drawable.img_book_example2));
        }

        //recyclerview 고전소설
        recyclerView_classic_novel = (RecyclerView)view.findViewById(R.id.rv_home_classic_nov);
        adapter = new RecyclerViewAdapter(getContext(), books);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_classic_novel.setHasFixedSize(true);
        recyclerView_classic_novel.setLayoutManager(linearLayoutManager);
        recyclerView_classic_novel.setAdapter(adapter);

        //recyclerview 에세이
        recyclerView_essay = (RecyclerView)view.findViewById(R.id.rv_home_essay);
        adapter = new RecyclerViewAdapter(getContext(), books);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_essay.setHasFixedSize(true);
        recyclerView_essay.setLayoutManager(linearLayoutManager);
        recyclerView_essay.setAdapter(adapter);

        //recyclerview 시집
        recyclerView_poetry = (RecyclerView)view.findViewById(R.id.rv_home_poetry);
        adapter = new RecyclerViewAdapter(getContext(), books);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_poetry.setHasFixedSize(true);
        recyclerView_poetry.setLayoutManager(linearLayoutManager);
        recyclerView_poetry.setAdapter(adapter);

        //recyclerview 역사
        recyclerView_history = (RecyclerView)view.findViewById(R.id.rv_home_history);
        adapter = new RecyclerViewAdapter(getContext(), books);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_history.setHasFixedSize(true);
        recyclerView_history.setLayoutManager(linearLayoutManager);
        recyclerView_history.setAdapter(adapter);

        //recyclerview 철학
        recyclerView_philo = (RecyclerView)view.findViewById(R.id.rv_home_philo);
        adapter = new RecyclerViewAdapter(getContext(), books);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_philo.setHasFixedSize(true);
        recyclerView_philo.setLayoutManager(linearLayoutManager);
        recyclerView_philo.setAdapter(adapter);

        //section clicked
        section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 섹션 액티비티로 이동
                Intent intent = new Intent(getActivity(), SectionActivity.class);
                startActivity(intent);
            }
        });

        //recyclerView item clicked
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                // 상세 액티비티로 이동
                Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
                startActivity(intent);
            }
        });

    }

}
