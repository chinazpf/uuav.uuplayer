package com.uuav.uuplayer.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/6/30.
 */

public class VideoFragment extends BaseFragment {
    @Override
    public View getFragmentView() {
        TextView view = new TextView(getActivity());
        view.setText("视频");
        return view;
    }
}
