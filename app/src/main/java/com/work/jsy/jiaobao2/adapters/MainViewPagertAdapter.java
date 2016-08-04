package com.work.jsy.jiaobao2.adapters;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * viewpager 传入Fragment
 * Created by admin on 2016/8/3.
 */
public class MainViewPagertAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private Context mContext;
    private ViewPager mViewPager;
    private ArrayList<TextView> mArrayList;
    private  ArrayList<FragmentInfo> mFragmentInfos = new ArrayList<>();

    public MainViewPagertAdapter(AppCompatActivity activity, ViewPager viewPager) {
        super(activity.getSupportFragmentManager());
        mContext = activity;
        mViewPager = viewPager;
        mViewPager.setAdapter(this);
        mViewPager.addOnPageChangeListener(this);
    }


    static final class FragmentInfo {

        private final Class<?> clss;
        private final Bundle args;

        FragmentInfo(Class _clss, Bundle _args) {
            clss = _clss;
            args = _args;
        }
    }

    public void addTitle(TextView view, Class<?> clss, Bundle args) {
        FragmentInfo fragmentInfo = new FragmentInfo(clss, args);
        mFragmentInfos.add(fragmentInfo);
        mArrayList = new ArrayList<>();
        view.setTag(fragmentInfo);
        view.setOnClickListener(this);
        mArrayList.add(view);
    }

    @Override
    public void onClick(View view) {
        if(mFragmentInfos!=null&&mFragmentInfos.size()>0){
            Object tag = view.getTag();
            for (int i = 0; i < mFragmentInfos.size(); i++) {
                if (mFragmentInfos.get(i) == tag) {
                    mViewPager.setCurrentItem(i);
                    //提交
                }
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        if(mFragmentInfos!=null&&mFragmentInfos.size()>0){
            FragmentInfo fragmentInfo = mFragmentInfos.get(position);
            Fragment fragment = Fragment.instantiate(mContext, fragmentInfo.clss.getName(), fragmentInfo.args);
            return fragment;
        }else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return mArrayList == null ? 0 : mArrayList.size();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mArrayList.get(position).requestFocus();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
