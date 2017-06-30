package com.uuav.uuplayer.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/6/30.
 */

public class ImageFragment extends BaseFragment {
    @Override
    public View getFragmentView() {
        TextView view = new TextView(getActivity());
        view.setText("图片");
        return view;
    }
}
