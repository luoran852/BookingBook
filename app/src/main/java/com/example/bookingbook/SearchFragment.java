package com.example.bookingbook;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    RecyclerView mRecyclerView ;
    RecyclerView.LayoutManager mLayoutManager;
    RvAdapter mAdapter;
    ArrayList searchedItems;

    private String CLIENT_ID = "3_FP0gfD3YHrOdUGUrm0";
    private String CLIENT_SECRET = "eIZ969Ubz1";
    private String baseUrl = "https://openapi.naver.com/v1/search/";
    private MovieRequest movieRequest;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText searchText = (EditText)view.findViewById(R.id.etSearch);
        AppCompatImageButton searchBtn = (AppCompatImageButton)view.findViewById(R.id.btnSearch);
        TextView CancelTxt = (TextView) view.findViewById(R.id.txt_cancel);
        LinearLayout searchTextInfo = (LinearLayout)view.findViewById(R.id.searchTexts);
        TextView searchName = (TextView)view.findViewById(R.id.searchName);
        //TextView tvRec = (TextView)view.findViewById(R.id.tvRec);
        searchedItems = new ArrayList();

        // If enter pressed, do search
        searchText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    String searchString = searchText.getText().toString();
                    Log.d("search", searchString);
                    searchTextInfo.setVisibility(View.VISIBLE);
                    //recommends.setVisibility(View.VISIBLE);
                    //tvRec.setVisibility(View.VISIBLE);
                    searchName.setText(searchString);
                    search(view, searchString);
                }
                return false;
            }
        });

        // If search button pressed, do search
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchString = searchText.getText().toString();
                Log.d("search", searchString);
                searchTextInfo.setVisibility(View.VISIBLE);
                //recommends.setVisibility(View.VISIBLE);
                //tvRec.setVisibility(View.VISIBLE);
                searchName.setText(searchString);
                //searchedItems.add(new ItemSearched(R.drawable.img_book_example, searchString, "2010", "리처드 도킨스", "홍영남", "을유문화사"));
                search(view, searchString);
            }
        });

        // If erase button pressed, erase text
        CancelTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText.getText().clear();
                searchTextInfo.setVisibility(View.INVISIBLE);
                mRecyclerView.setVisibility(View.INVISIBLE);
                //recommends.setVisibility(View.INVISIBLE);

                searchedItems.clear();
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    public void search(View view, String search) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieRequest= retrofit.create(MovieRequest.class);

        Call<MovieResponse> callGetBook = movieRequest.getBook(CLIENT_ID, CLIENT_SECRET, search);
        callGetBook.enqueue(retrofitCallback);

        //searchedItems.add(new ItemSearched(R.drawable.img_book_example2, search, "2016", "기시미 이치로", "전경아", "인플루엔셜"));
        mRecyclerView = (RecyclerView)view.findViewById(R.id.rvSearched);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new RvAdapter(searchedItems);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setVisibility(View.VISIBLE);


    }
    private Callback<MovieResponse> retrofitCallback = new Callback<MovieResponse>() {
        @Override
        public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
            if (response.isSuccessful()) {
                int total = response.body().getItems().length;
                Items[] booksSearched = response.body().getItems();
                for (int i=0; i<total; i++) {
                    Log.d("Response", String.valueOf(booksSearched[i]));
                    searchedItems.add(new ItemSearched(booksSearched[i].getImage(), booksSearched[i].getTitle(), booksSearched[i].getPubdate(),
                            booksSearched[i].getAuthor(), booksSearched[i].getPublisher()));
                    mAdapter.notifyDataSetChanged();
                }
            }
            else {
                Log.d("ResponseError", response.raw().toString());
            }

        }

        @Override
        public void onFailure(Call<MovieResponse> call, Throwable t) {
            Log.d("Response", "Fail");
            t.printStackTrace();

        }
    };


}
