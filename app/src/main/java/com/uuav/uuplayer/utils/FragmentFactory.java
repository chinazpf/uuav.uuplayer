package com.uuav.uuplayer.utils;

import com.uuav.uuplayer.fragment.BaseFragment;
import com.uuav.uuplayer.fragment.HomeFragment;
import com.uuav.uuplayer.fragment.ImageFragment;
import com.uuav.uuplayer.fragment.MineFragment;
import com.uuav.uuplayer.fragment.VideoFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/30.
 */

public class FragmentFactory {
    private static Map<Integer, BaseFragment> fragents = new HashMap<>();

    /**
     * 将每次创建的Fragment对象 放在缓存中,下次再使用的时候,先去缓存中获取,缓存中存在
     * 就使用缓存中对象.     就直接创建新的对象,再次添加到缓存中
     * 根据position生产Fragment
     *
     * @param position
     * @return
     */
    public static BaseFragment create(int position) {
        BaseFragment fragment = fragents.get(position);//从缓存中获取
        if (fragment == null) { //如果缓存中没有 创建新的对象 添加到缓存中
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new VideoFragment();
                    break;
                case 2:
                    fragment = new ImageFragment();
                    break;
                case 3:
                    fragment = new MineFragment();
                    break;
            }
            if (fragment != null) {
                //缓存下来
                fragents.put(position, fragment);
            }
        }
        return fragment;
    }
}
