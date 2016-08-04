package com.work.jsy.jiaobao2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.jsy.plan.R;
import com.work.jsy.plan.adapters.FirstRecyclerViewAdapter;
import com.work.jsy.plan.selfdefinedView.SlideShowView;

import java.util.ArrayList;

/**
 * 第一个界面
 * Created by admin on 2016/8/3.
 */
public class FirstFragment extends Fragment {
    private View mView;
    private ArrayList<String> photoList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        mView=inflater.inflate(R.layout.fragment_first,container,false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
    }

    /**
     * view
     */
    private void findViews(){
        RecyclerView recyclerView=(RecyclerView)mView.findViewById(R.id.recyclerView_firstFragment);
        SlideShowView showView=(SlideShowView) mView.findViewById(R.id.slideShow_firstFragment);
        setRecyclerView(recyclerView);
        setSlideShowView(showView);
    }

    /**
     * 初始化RecyclerView
     * 加载Adapter
     * @param recyclerView
     */
    private void setRecyclerView(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        FirstRecyclerViewAdapter viewAdapter=new FirstRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(viewAdapter);
        initAdapterData(viewAdapter);
    }

    /**
     * 轮播图加载图片并播放
     * @param showView
     */
    private void setSlideShowView(SlideShowView showView){
        getPhotoList();
        showView.setImageUrls(photoList);
        showView.setCurrentItem(0);
        showView.startPlay();
    }

    /**
     * 获取图片列表
     */
    private void getPhotoList(){
        photoList=new ArrayList<>();
        photoList.add("https://github.com/rockan007/photos/blob/master/MGN%7B4DZ5JP%40FU5UEO%7DU%7DL5B.png");
        photoList.add("https://github.com/rockan007/photos/blob/master/QI%7B~59_DQ%5BTR%257~M09SD%40NY.png");
    }
    /**
     * adapter加载数据
     */
    private void initAdapterData(FirstRecyclerViewAdapter viewAdapter){
        ArrayMap<Integer,Integer> arrayMap=new ArrayMap<>();
//        arrayMap.put();
        viewAdapter.setArrayMap(arrayMap);
        viewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
