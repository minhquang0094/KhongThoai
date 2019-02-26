package com.comicola.khongthoai.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comicola.khongthoai.Interface.IRewardClickListener;
import com.comicola.khongthoai.Models.rewards.DataItem;
import com.comicola.khongthoai.R;

import java.util.List;

public class RewardsAdapter extends RecyclerView.Adapter<RewardsAdapter.Viewholder> {
    IRewardClickListener iRewardClickListener;
    List<DataItem> listReward;

    public RewardsAdapter(List<DataItem> listReward, IRewardClickListener iRewardClickListener) {
        this.iRewardClickListener = iRewardClickListener;
        this.listReward = listReward;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reward_item, viewGroup, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, final int i) {
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iRewardClickListener.onRewardClick(listReward.get(i).getTitle(),listReward.get(i).getMins());
            }
        });
        viewholder.tvTitle.setText(listReward.get(i).getTitle());
        viewholder.tvMin.setText(listReward.get(i).getMins()+" phút");
    }

    @Override
    public int getItemCount() {
        return listReward.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvMin;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTit);
            tvMin=itemView.findViewById(R.id.tvMin);
        }
    }
}
