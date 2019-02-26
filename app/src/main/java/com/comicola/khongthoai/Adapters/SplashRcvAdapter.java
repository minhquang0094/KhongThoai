package com.comicola.khongthoai.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.comicola.khongthoai.Models.SplashModel;
import com.comicola.khongthoai.R;

import java.util.List;

public class SplashRcvAdapter extends RecyclerView.Adapter<SplashRcvAdapter.Viewholder> {
    List<SplashModel> list;

    public SplashRcvAdapter(List<SplashModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.splash_rcv_item, viewGroup, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        viewholder.img.setImageResource(list.get(i).getImg());
        viewholder.tvTitle.setText(list.get(i).getTitle());
        viewholder.tvDetail.setText(list.get(i).getDetail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvTitle, tvDetail;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDetail = itemView.findViewById(R.id.tvDetail);
        }
    }

}
