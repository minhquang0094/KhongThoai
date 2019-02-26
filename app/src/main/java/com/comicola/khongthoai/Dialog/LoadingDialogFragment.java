package com.comicola.khongthoai.Dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.comicola.khongthoai.R;

public class LoadingDialogFragment extends DialogFragment {
    private static String TAG = LoadingDialogFragment.class.getSimpleName();

    public static void show(FragmentManager fragmentManager) {
        LoadingDialogFragment loadingDialogFragment = (LoadingDialogFragment) fragmentManager.findFragmentByTag(TAG);
        if (loadingDialogFragment == null) {
            loadingDialogFragment = new LoadingDialogFragment();
            loadingDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
            loadingDialogFragment.setCancelable(false);
            loadingDialogFragment.show(fragmentManager, TAG);
        }
    }

    public static void dismiss(FragmentManager fragmentManager) {
        try {
            LoadingDialogFragment loadingDialogFragment = (LoadingDialogFragment) fragmentManager.findFragmentByTag(TAG);
            if (loadingDialogFragment != null) {
                loadingDialogFragment.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_loading,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
