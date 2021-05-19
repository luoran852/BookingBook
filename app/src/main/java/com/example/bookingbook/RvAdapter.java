package com.example.bookingbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private ArrayList<ItemSearched> contents;
    private RvAdapter.OnBookClickListener mOnBookClickListener;

    public RvAdapter(ArrayList searchData, RvAdapter.OnBookClickListener onBookClickListener) {
        this.contents = searchData;
        this.mOnBookClickListener = onBookClickListener;
    }

    @NonNull
    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent,false);
        ViewHolder viewHolder = new ViewHolder(view, mOnBookClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.ViewHolder holder, int position) {
        //holder.searchedPoster.setImageResource(contents.get(position).image);
        holder.searchedTitle.setText(contents.get(position).title);
        holder.searchedYear.setText(contents.get(position).year);
        holder.searchedAuthor.setText(contents.get(position).author);
        //holder.searchedTranslator.setText(contents.get(position).translator);
        holder.searchedPublisher.setText(contents.get(position).publisher);

        Glide.with(holder.itemView.getContext())
                .load(contents.get(position).getImage())
                .into(holder.searchedPoster);
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView searchedPoster;
        TextView searchedTitle;
        TextView searchedAuthor;
        TextView searchedYear;
        //TextView searchedTranslator;
        TextView searchedPublisher;

        RvAdapter.OnBookClickListener onBookClickListener;

        public ViewHolder(@NonNull View itemView, RvAdapter.OnBookClickListener onBookClickListener) {
            super(itemView);

            this.onBookClickListener = onBookClickListener;

            itemView.setOnClickListener(this);

            searchedPoster = (ImageView)itemView.findViewById(R.id.searchedPoster);
            searchedTitle = (TextView)itemView.findViewById(R.id.searchedTitle);
            searchedAuthor = (TextView)itemView.findViewById(R.id.searchedAuthor);
            searchedYear = (TextView)itemView.findViewById(R.id.searchedYear);
            //searchedTranslator = (TextView)itemView.findViewById(R.id.searchedTranslator);
            searchedPublisher = (TextView)itemView.findViewById(R.id.searchedPublisher);
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

