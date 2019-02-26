package com.comicola.khongthoai.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.comicola.khongthoai.Fragments.MeFragment;
import com.comicola.khongthoai.Fragments.RewardFragment;
import com.comicola.khongthoai.Fragments.RunFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return RunFragment.newInstance();

            case 1:
                return new RewardFragment();

            case 2:
                return MeFragment.newInstance();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
