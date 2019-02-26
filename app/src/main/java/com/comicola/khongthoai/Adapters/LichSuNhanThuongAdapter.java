package com.comicola.khongthoai.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comicola.khongthoai.Models.userReward.DataItem;
import com.comicola.khongthoai.R;

import java.util.List;

public class LichSuNhanThuongAdapter extends RecyclerView.Adapter<LichSuNhanThuongAdapter.Viewholder> {
    List<DataItem> listLichSuNhanQua;

    public LichSuNhanThuongAdapter(List<DataItem> listLichSuNhanQua) {
        this.listLichSuNhanQua = listLichSuNhanQua;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lich_su_nhan_thuong_item, viewGroup, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        viewholder.tvDatetime.setText(listLichSuNhanQua.get(i).getDatetime());
        viewholder.tvReward.setText(listLichSuNhanQua.get(i).getReward());
    }

    @Override
    public int getItemCount() {
        return listLichSuNhanQua.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tvDatetime, tvReward;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvDatetime = itemView.findViewById(R.id.tvDatetime);
            tvReward = itemView.findViewById(R.id.tvReward);
        }
    }
}
