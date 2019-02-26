package com.comicola.khongthoai.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comicola.khongthoai.Adapters.TabAdapter;
import com.comicola.khongthoai.Common;
import com.comicola.khongthoai.Models.userInfo.UserInfoResponse;
import com.comicola.khongthoai.R;
import com.comicola.khongthoai.Service.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    TabAdapter tabAdapter;

    TextView tvPhone, tvBalance, tvSessionCount, tvRewardCount;

    public MeFragment() {
        // Required empty public constructor
    }

    public static MeFragment newInstance() {

        Bundle args = new Bundle();

        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_me, container, false);
        initView(v);
        initTab();
        if (Common.fb_token != null && Common.fb_token.length() > 0) {
            ServiceGenerator.getSOService().getUserInfo(Common.fb_token).enqueue(new Callback<UserInfoResponse>() {
                @Override
                public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            tvPhone.setText(response.body().getData().getPhone());
                            tvBalance.setText(response.body().getData().getBalance() + "");
                            tvSessionCount.setText(response.body().getData().getSessionCount() + "");
                            tvRewardCount.setText(response.body().getData().getRewardCount() + "");

                            Intent intent = new Intent("balance");
                            intent.putExtra("balance", response.body().getData().getBalance());
                            getActivity().sendBroadcast(intent);
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserInfoResponse> call, Throwable t) {

                }
            });
        }

        return v;
    }

    private void initTab() {
        tabAdapter = new TabAdapter(getFragmentManager());
        tabAdapter.addFragment(LichSuChoiFragment.newInstance(), "Lịch sử chơi");
        tabAdapter.addFragment(LichSuNhanThuongFragment.newInstance(), "Lịch sử nhận quà");
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView(View v) {
        tabLayout = v.findViewById(R.id.tabLayout);
        viewPager = v.findViewById(R.id.vp);
        viewPager.setOffscreenPageLimit(2);
        tvPhone = v.findViewById(R.id.tvPhone);
        tvBalance = v.findViewById(R.id.tvBalance);
        tvSessionCount = v.findViewById(R.id.tvSessionCount);
        tvRewardCount = v.findViewById(R.id.tvRewardCount);
    }

}
