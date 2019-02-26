package com.comicola.khongthoai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import com.comicola.khongthoai.Adapters.SplashRcvAdapter;
import com.comicola.khongthoai.Models.SplashModel;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    RecyclerView rcv;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        getScreenSize();
        initView();
        initRcv();
    }

    private void getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

//        float density  = getResources().getDisplayMetrics().density;
//        float dpHeight = outMetrics.heightPixels / density;
//        float dpWidth  = outMetrics.widthPixels / density;

        Common.SCREEN_WIDTH = outMetrics.widthPixels;

    }

    private void initRcv() {
        List<SplashModel> listSplashModel = new ArrayList<>();
        listSplashModel.add(new SplashModel(R.drawable.ico_moto, "Thử thách không nghe điện thoại", "Di chuyển trên đường trong 1 khoảng thời gian" +
                " và không sử dụng điện thoại di dộng"));
        listSplashModel.add(new SplashModel(R.drawable.ico_box, "Đổi quà hấp dẫn", "Sử dụng số phút tích lũy được để quy đổi thành các món quà"));
        listSplashModel.add(new SplashModel(R.drawable.icon_bxh, "Tích lũy số phút của bạn", "Hoàn thành thử thách được đặt ra để tích lũy số phút và " +
                "so sánh với các người chơi khác trên toàn quốc"));
        SplashRcvAdapter splashRcvAdapter = new SplashRcvAdapter(listSplashModel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcv.setLayoutManager(linearLayoutManager);
        rcv.setAdapter(splashRcvAdapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rcv);
    }

    private void initView() {
        rcv = findViewById(R.id.rcv);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        });
    }
}
