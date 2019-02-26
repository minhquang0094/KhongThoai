package com.comicola.khongthoai.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.comicola.khongthoai.R;

public class DialogRewardClick extends Dialog {
    public Activity activity;

    public TextView tvAgree, tvCancel;
    public static TextView tvTitle;

    public DialogRewardClick(Activity activity) {
        super(activity);
        // TODO Auto-generated constructor stub
        this.activity = activity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_reward);
        tvAgree = findViewById(R.id.btnAgree);
        tvTitle = findViewById(R.id.tvDialogTitle);
        tvCancel = findViewById(R.id.btnCancel);

        tvAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public static void showDialog(Activity a,String rewardName, int mins) {
        final Dialog dialog = new DialogRewardClick(a);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        tvTitle.setText("Nhận "+rewardName+" đổi bằng "+mins+" phút tích lũy");



    }
}
