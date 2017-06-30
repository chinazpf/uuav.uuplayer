package com.uuav.uuplayer.activity;

import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.uuav.uuplayer.R;
import com.uuav.uuplayer.adapter.MainFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.bottom_navigation_bar_main)
    BottomNavigationBar bottomNavigationBarMain;
    @BindView(R.id.viewpager_main)
    ViewPager viewpagerMain;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initBnb();
        initViewPager();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        viewpagerMain.addOnPageChangeListener(this);
    }

    /***
     * bottomNavigationBar初始化
     */
    private void initBnb() {
        bottomNavigationBarMain.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBarMain.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBarMain.addItem(new BottomNavigationItem(R.drawable.ic_main_home, "首页").setInActiveColor(R.color.main_color).setActiveColor("#FF8a02"))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_video, "视频").setInActiveColor(R.color.main_color).setActiveColor("#32CD32"))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_picture, "图片").setInActiveColor(R.color.main_color).setActiveColor("#22a7ff"))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_mine, "我").setInActiveColor(R.color.main_color).setActiveColor("#e9d795"))
                .setFirstSelectedPosition(0)//设置默认选中
                .initialise();
        bottomNavigationBarMain.setTabSelectedListener(this);
    }

    @Override
    protected void initData() {
        viewpagerMain.setAdapter(new MainFragmentAdapter(getSupportFragmentManager()));
    }


    @Override
    public void onTabSelected(int position) {
        viewpagerMain.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigationBarMain.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
