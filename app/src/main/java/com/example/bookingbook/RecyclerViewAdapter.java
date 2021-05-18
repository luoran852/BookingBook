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
    ArrayList<ItemBook> list;

    RecyclerViewAdapter(Context context, ArrayList<ItemBook> list) {
        super();
        Log.d("list", list.toString());
        this.context = context;
        this.list = list;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    // 리스너 객체 참조를 저장하는 변수
    private OnItemClickListener mListener = null;

    // OnItemClickListener 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.img_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.poster.setImageResource(list.get(position).image);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView poster;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    // 리스너 객체의 메서드 호출
                    if (pos != RecyclerView.NO_POSITION)
                    {
                        mListener.onItemClick(v, pos);
                    }
                }
            });
            poster = itemView.findViewById(R.id.book);
        }

    }

}
