package com.item.fragment.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.item.fragment.R;
import com.item.fragment.ment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class TwoActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        mTabLayout = (TabLayout)findViewById(R.id.tabLayout);
        mViewPager = (ViewPager)findViewById(R.id.viewPager);
        initData();
    }

    private void initData() {
        tabs.add("新消息");
        tabs.add("朋友圈");
        tabs.add("公共号");
        tabs.add("你好啊");
        fragments.add(new TwoFragment());
        fragments.add(new TwoFragment());
        fragments.add(new TwoFragment());
        fragments.add(new TwoFragment());
        // 设置TabLayout的模式
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        // 关联ViewPager和TabLayout
        mTabLayout.setupWithViewPager(mViewPager);
        // 设置分割线
        LinearLayout linearLayout = (LinearLayout)mTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        // 设置分割线间隔
        linearLayout.setDividerPadding(dip2px(15));
    }
    //像素单位转换
    public int dip2px(int dip) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5);
    }
    class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        //显示标签上的文字
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }
}
