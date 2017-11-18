package com.item.fragment.home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.item.fragment.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private CategoryPagerAdapter mAdapter;
    private List<String> mTitleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        mTabLayout = (TabLayout)findViewById(R.id.category_title);
        mViewPager = (ViewPager)findViewById(R.id.category_content);
        // Tab 标签页
        mTitleList = new ArrayList<>();
        mTitleList.add("Android");
        mTitleList.add("IOS");
        mTitleList.add("APP");
        mTitleList.add("前端");
        mTitleList.add("拓展资源");
        mTitleList.add("休息视频");
        mTitleList.add("福利");
        // 适配器
        mAdapter = new CategoryPagerAdapter(getSupportFragmentManager(),mTitleList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mTitleList.size());
        // 关联ViewPager 和 TabLayout
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
