package com.work.jsy.jiaobao2.adapters;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;

/**
 * viewpager 传入Fragment
 * Created by admin on 2016/8/3.
 */
public class MainViewPagertAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private final static String TAG="MainViewPagertAdapter";
    private int clickFirst=0;
    private Context mContext;
    private ViewPager mViewPager;
    private ArrayList<RadioButton> mArrayList=new ArrayList<>();
    private ArrayList<FragmentInfo> mFragmentInfos = new ArrayList<>();

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

    public void addTitle(RadioButton view, Class<?> clss, Bundle args) {
        FragmentInfo fragmentInfo = new FragmentInfo(clss, args);
        mFragmentInfos.add(fragmentInfo);
        view.setTag(fragmentInfo);
        view.setOnClickListener(this);
        mArrayList.add(view);
    }



    @Override
    public void onClick(View view) {
        if (mFragmentInfos != null && mFragmentInfos.size() > 0) {
            FragmentInfo tag = (FragmentInfo)view.getTag();
            Log.d(TAG,tag.clss.getName());
            for (int i = 0; i < mFragmentInfos.size(); i++) {
                if (mFragmentInfos.get(i).clss.getName() .equals(tag.clss.getName())) {
                    mViewPager.setCurrentItem(i);
                }
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragmentInfos != null && mFragmentInfos.size() > 0) {
            FragmentInfo fragmentInfo = mFragmentInfos.get(position);
            Fragment fragment = Fragment.instantiate(mContext, fragmentInfo.clss.getName(), fragmentInfo.args);
            Log.d(TAG,fragmentInfo.clss.getName());
            return fragment;
        } else {
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
        mArrayList.get(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
