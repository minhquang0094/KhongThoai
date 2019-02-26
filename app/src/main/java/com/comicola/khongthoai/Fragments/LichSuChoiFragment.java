package com.comicola.khongthoai.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.comicola.khongthoai.Adapters.LichSuChoiAdapter;
import com.comicola.khongthoai.Common;
import com.comicola.khongthoai.Models.lichSuChoi.DataItem;
import com.comicola.khongthoai.Models.lichSuChoi.LichSuChoiResponse;
import com.comicola.khongthoai.R;
import com.comicola.khongthoai.Service.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LichSuChoiFragment extends Fragment {

    RecyclerView rcvLichSuChoi;
    List<DataItem> listLichSuChoi;

    public LichSuChoiFragment() {
        // Required empty public constructor
    }

    public static LichSuChoiFragment newInstance() {

        Bundle args = new Bundle();

        LichSuChoiFragment fragment = new LichSuChoiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lich_su_choi, container, false);

        initView(v);
        initRcv();
        return v;
    }

    private void initRcv() {
        if (Common.fb_token != null && Common.fb_token.length() > 0) {
            ServiceGenerator.getSOService().getLichSuChoi(Common.fb_token).enqueue(new Callback<LichSuChoiResponse>() {
                @Override
                public void onResponse(Call<LichSuChoiResponse> call, Response<LichSuChoiResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            listLichSuChoi = response.body().getData();
                            LichSuChoiAdapter lichSuChoiAdapter = new LichSuChoiAdapter(listLichSuChoi);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            rcvLichSuChoi.setLayoutManager(linearLayoutManager);
                            rcvLichSuChoi.setAdapter(lichSuChoiAdapter);
                        }
                    }
                }

                @Override
                public void onFailure(Call<LichSuChoiResponse> call, Throwable t) {

                }
            });

        }



    }

    private void initView(View v) {
        rcvLichSuChoi = v.findViewById(R.id.rcvLichSuChoi);
    }

}
