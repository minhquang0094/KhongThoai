package com.comicola.khongthoai.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.comicola.khongthoai.Adapters.LichSuNhanThuongAdapter;
import com.comicola.khongthoai.Common;
import com.comicola.khongthoai.Models.userReward.UserRewardResponse;
import com.comicola.khongthoai.R;
import com.comicola.khongthoai.Service.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LichSuNhanThuongFragment extends Fragment {
    RecyclerView rcvLichSuNhanThuong;

    public LichSuNhanThuongFragment() {
        // Required empty public constructor
    }

    public static LichSuNhanThuongFragment newInstance() {

        Bundle args = new Bundle();

        LichSuNhanThuongFragment fragment = new LichSuNhanThuongFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lich_su_nhan_thuong, container, false);
        initView(v);
        initRcv();
        return v;
    }

    private void initRcv() {
        if (Common.fb_token!=null&&Common.fb_token.length()>0){
            ServiceGenerator.getSOService().getLichSuDoiQua(Common.fb_token).enqueue(new Callback<UserRewardResponse>() {
                @Override
                public void onResponse(Call<UserRewardResponse> call, Response<UserRewardResponse> response) {
                    if (response.isSuccessful()){
                        if (response.body().getCode()==200){



                            LichSuNhanThuongAdapter lichSuNhanThuongAdapter=new LichSuNhanThuongAdapter(response.body().getData());
                            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                            rcvLichSuNhanThuong.setLayoutManager(linearLayoutManager);
                            rcvLichSuNhanThuong.setAdapter(lichSuNhanThuongAdapter);
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserRewardResponse> call, Throwable t) {

                }
            });

        }


    }

    private void initView(View v) {
        rcvLichSuNhanThuong = v.findViewById(R.id.rcvLichSuNhanThuong);
    }

}
