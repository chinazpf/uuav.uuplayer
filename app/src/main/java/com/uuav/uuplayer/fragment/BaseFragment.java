package com.uuav.uuplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/6/30.
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getFragmentView();
    }

    /**
     * 初始化Fragment View
     * @return
     */
    public abstract View getFragmentView();


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
