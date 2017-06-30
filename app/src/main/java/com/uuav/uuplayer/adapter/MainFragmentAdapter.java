package com.uuav.uuplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.uuav.uuplayer.utils.FragmentFactory;

/**
 * Created by Administrator on 2017/6/30.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {


    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return FragmentFactory.create(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
