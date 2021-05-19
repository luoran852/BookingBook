package com.example.bookingbook;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<ItemBook> books;
    private OnBookClickListener mOnBookClickListener;


    RecyclerViewAdapter(Context context, ArrayList<ItemBook> books, OnBookClickListener onBookClickListener) {
        super();
        Log.d("books", books.toString());
        this.context = context;
        this.books = books;
        this.mOnBookClickListener = onBookClickListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.img_item, parent, false);
        return new MyViewHolder(view, mOnBookClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.poster.setImageResource(books.get(position).image);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView poster;
        OnBookClickListener onBookClickListener;

        public MyViewHolder(View itemView, OnBookClickListener onBookClickListener) {
            super(itemView);
            poster = itemView.findViewById(R.id.book);
            this.onBookClickListener = onBookClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onBookClickListener.onBookClick(getAdapterPosition());
        }
    }

    public interface OnBookClickListener {
        void onBookClick(int position);
    }

}
