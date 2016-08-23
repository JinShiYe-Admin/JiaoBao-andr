package com.work.jsy.jiaobao2.adapters;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.work.jsy.jiaobao2.R;
import com.work.jsy.jiaobao2.selfdefinedView.SlideShowView;
import com.work.jsy.jiaobao2.util.DividerItemDecoration;

import java.util.ArrayList;

/**
 * 第一个界面RecyclerView的Adapter
 * Created by ShangLinMo on 2016/8/22.
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<String> photoList;
    public HomeRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_recycler, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setVisibility(View.VISIBLE);
        holder.textView.setText("一二三四五六七八九十");
        setRecyclerView(holder.recyclerView);
        setSlideShowView(holder.showView);
        setRecyclerViewSecond(holder.recyclerViewSecond);
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        SlideShowView showView;
        TextView textView;
        RecyclerView recyclerViewSecond;
        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView_firstFragment);
            showView = (SlideShowView) itemView.findViewById(R.id.slideShow_firstFragment);
            textView = (TextView) itemView.findViewById(R.id.tv_firstFragment);
            recyclerViewSecond = (RecyclerView) itemView.findViewById(R.id.recyclerView_firstFragment_Second);
        }
    }

    /**
     * 初始化第一个RecyclerView
     * 加载Adapter
     *
     * @param recyclerView r
     */
    private void setRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        FirstRecyclerViewAdapter viewAdapter = new FirstRecyclerViewAdapter(mContext);
        recyclerView.setAdapter(viewAdapter);
        initAdapterData(viewAdapter);
    }

    /**
     * 第一个RecyclerView的adapter加载数据
     */
    private void initAdapterData(FirstRecyclerViewAdapter viewAdapter) {
        ArrayMap<Integer, Integer> arrayMap = new ArrayMap<>();
        arrayMap.put(R.string.first, R.drawable.ic_card_travel_blue_800_48dp);
        arrayMap.put(R.string.second, R.drawable.ic_assignment_blue_800_48dp);
        arrayMap.put(R.string.third, R.drawable.ic_home_blue_800_48dp);
        arrayMap.put(R.string.fourth, R.drawable.ic_bookmark_border_blue_800_48dp);
        arrayMap.put(R.string.fifth, R.drawable.ic_chrome_reader_mode_blue_800_48dp);
        viewAdapter.setArrayMap(arrayMap);
        viewAdapter.notifyDataSetChanged();
    }

    /**
     * 轮播图加载图片并播放
     *
     * @param showView show
     */
    private void setSlideShowView(SlideShowView showView) {
        getPhotoList();
        showView.setImageUrls(photoList);
        showView.setCurrentItem(0);
        showView.startPlay();
    }

    /**
     * 获取图片列表
     */
    private void getPhotoList() {
        photoList = new ArrayList<>();
        photoList.add("http://imgstore04.cdn.sogou.com/app/a/100520024/877e990117d6a7ebc68f46c5e76fc47a");
        photoList.add("https://github.com/rockan007/photos/blob/master/QI%7B~59_DQ%5BTR%257~M09SD%40NY.png");
    }

    /**
     * 初始化第二个RecyclerView
     * 加载Adapter
     */
    private void setRecyclerViewSecond(RecyclerView recyclerViewSecond) {
        recyclerViewSecond.setLayoutManager(new GridLayoutManager(mContext, 1));
        recyclerViewSecond.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSecond.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL_LIST));
        SecondRecyclerViewAdapter viewAdapter = new SecondRecyclerViewAdapter(mContext);
        recyclerViewSecond.setAdapter(viewAdapter);
        initAdapterDataSecond(viewAdapter);
    }

    /**
     * 第二个RecyclerView的adapter加载数据
     */
    private void initAdapterDataSecond(SecondRecyclerViewAdapter viewAdapter) {
        ArrayMap<Integer, Integer> arrayMap = new ArrayMap<>();
        arrayMap.put(R.string.first, 0);
        arrayMap.put(R.string.second, 1);
        arrayMap.put(R.string.third, 2);
        arrayMap.put(R.string.fourth, 3);
        arrayMap.put(R.string.fifth, 4);
        viewAdapter.setArrayMap(arrayMap);
        viewAdapter.notifyDataSetChanged();
    }
}
