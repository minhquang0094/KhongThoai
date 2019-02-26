package com.comicola.khongthoai.Fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comicola.khongthoai.Adapters.RewardsAdapter;
import com.comicola.khongthoai.Dialog.DialogRewardClick;
import com.comicola.khongthoai.Interface.IRewardClickListener;
import com.comicola.khongthoai.Models.rewards.DataItem;
import com.comicola.khongthoai.Models.rewards.RewardResponse;
import com.comicola.khongthoai.R;
import com.comicola.khongthoai.Service.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RewardFragment extends Fragment implements IRewardClickListener {
    RecyclerView rcv;
    List<DataItem> listReward;
    IRewardClickListener iRewardClickListener = this;
    TextView tvMinus;

    public RewardFragment() {
        // Required empty public constructor
    }

    public static RewardFragment newInstance() {

        Bundle args = new Bundle();

        RewardFragment fragment = new RewardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
//        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, new IntentFilter("location_update"));
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter("balance"));


    }

    @Override
    public void onPause() {
        super.onPause();
//        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
        getActivity().unregisterReceiver(broadcastReceiver);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reward, container, false);
        tvMinus=v.findViewById(R.id.tvMinus);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv = view.findViewById(R.id.rcv);

        initRcv();
    }

    private void initRcv() {

        ServiceGenerator.getSOService().getRewardList().enqueue(new Callback<RewardResponse>() {
            @Override
            public void onResponse(Call<RewardResponse> call, Response<RewardResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        listReward = response.body().getData();
                        RewardsAdapter rewardsAdapter = new RewardsAdapter(listReward, iRewardClickListener);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                        rcv.setLayoutManager(gridLayoutManager);
                        rcv.setAdapter(rewardsAdapter);
                        rcv.addItemDecoration(new RecyclerView.ItemDecoration() {
                            @Override
                            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                int position = parent.getChildAdapterPosition(view); // item position
//                int spanCount = 2;
//                int spacing = 20;//spacing between views in grid
//
//                if (position >= 0) {
//                    int column = position % spanCount; // item column
//
//                    outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
//                    outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)
//
//                    if (position < spanCount) { // top edge
//                        outRect.top = spacing;
//                    }
//                    outRect.bottom = spacing; // item bottom
//                } else {
//                    outRect.left = 0;
//                    outRect.right = 0;
//                    outRect.top = 0;
//                    outRect.bottom = 0;
//                }
                                int position = parent.getChildAdapterPosition(view);
                                if (position % 2 == 0) {
                                    outRect.left = 20;
                                } else {
                                    outRect.right = 20;
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<RewardResponse> call, Throwable t) {

            }
        });


    }

    @Override
    public void onRewardClick(String rewardName, int mins) {
        DialogRewardClick.showDialog(getActivity(),rewardName,mins);
    }


    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            tvMinus.setText(intent.getIntExtra("balance",0)+" phút");
        }
    };

}
