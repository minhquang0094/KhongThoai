package com.comicola.khongthoai.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.comicola.khongthoai.Models.lichSuChoi.DataItem;
import com.comicola.khongthoai.R;

import java.util.List;

public class LichSuChoiAdapter extends RecyclerView.Adapter<LichSuChoiAdapter.Viewholder> {
    List<DataItem> listLichSuChoi;

    public LichSuChoiAdapter(List<DataItem> listLichSuChoi) {
        this.listLichSuChoi = listLichSuChoi;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lich_su_choi_item, viewGroup, false);

        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        viewholder.tvTime.setText(listLichSuChoi.get(i).getDatetime());
        viewholder.tvPackage.setText(listLichSuChoi.get(i).getJsonMemberPackage());

        viewholder.imgStatus.setImageResource(listLichSuChoi.get(i).isSuccess()? R.drawable.checked : R.drawable.cancel);
    }

    @Override
    public int getItemCount() {
        return listLichSuChoi.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imgStatus;
        TextView tvTime, tvPackage;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imgStatus = itemView.findViewById(R.id.imgStatus);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPackage = itemView.findViewById(R.id.tvPackage);
        }
    }
}
