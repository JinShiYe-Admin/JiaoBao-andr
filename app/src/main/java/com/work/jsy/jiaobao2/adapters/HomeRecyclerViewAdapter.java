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
import android.widget.Toast;

import com.work.jsy.jiaobao2.R;
import com.work.jsy.jiaobao2.selfdefinedView.SlideShowView;
import com.work.jsy.jiaobao2.util.DividerItemDecoration;

import java.util.ArrayList;

/**
 * 第一个界面RecyclerView的Adapter
 * Created by ShangLinMo on 2016/8/22.
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
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
        holder.textView.setText("品牌 ip 化即品牌主围绕品牌定制各种内容，以丰富品牌内涵，提升品牌美誉度或者传达品牌知识或者精神理念，最终增加消费者对品牌的认知与好感。");
        holder.textView.setOnClickListener(this);
        setRecyclerView(holder.recyclerView);
        setSlideShowView(holder.showView);
        setRecyclerViewSecond(holder.recyclerViewSecond);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    /**
     * 推荐标题的点击事件
     */
    @Override
    public void onClick(View view) {
        Toast.makeText(mContext, ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        SlideShowView showView;//头部可轮播的图片
        TextView textView;//头部和中部之间的推荐标题
        RecyclerView recyclerView;//中部可左右滑动的图标
        RecyclerView recyclerViewSecond;//底部内容

        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView_firstFragment);
            showView = (SlideShowView) itemView.findViewById(R.id.slideShow_firstFragment);
            textView = (TextView) itemView.findViewById(R.id.tv_firstFragment);
            recyclerViewSecond = (RecyclerView) itemView.findViewById(R.id.recyclerView_firstFragment_Second);
        }
    }

    /**
     * 初始化中部RecyclerView
     * 加载Adapter
     */
    private void setRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);//设置布局管理器
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        FirstRecyclerViewAdapter viewAdapter = new FirstRecyclerViewAdapter(mContext);
        recyclerView.setAdapter(viewAdapter);
        initAdapterData(viewAdapter);
    }

    /**
     * 中部RecyclerView的adapter加载数据
     */
    private void initAdapterData(FirstRecyclerViewAdapter viewAdapter) {
        ArrayMap<Integer, Integer> arrayMap = new ArrayMap<>();
        arrayMap.put(R.string.China, R.drawable.ic_china_48px);
        arrayMap.put(R.string.America, R.drawable.ic_america_48px);
        arrayMap.put(R.string.Germany, R.drawable.ic_germany_48px);
        arrayMap.put(R.string.Russia, R.drawable.ic_russia_48px);
        arrayMap.put(R.string.England, R.drawable.ic_england_48px);
        arrayMap.put(R.string.France, R.drawable.ic_france_48px);
        arrayMap.put(R.string.Japan, R.drawable.ic_japan_48px);
        viewAdapter.setArrayMap(arrayMap);
        viewAdapter.notifyDataSetChanged();
    }

    /**
     * 头部轮播图加载图片并播放
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
        photoList.add("http://img05.tooopen.com/images/20140919/sy_71272488121.jpg");
        photoList.add("http://img04.tooopen.com/images/20130701/tooopen_10055061.jpg");
        photoList.add("http://img05.tooopen.com/images/20150202/sy_80219211654.jpg");
        photoList.add("http://img06.tooopen.com/images/20160731/tooopen_sy_173429136985.jpg");
    }

    /**
     * 初始化底部RecyclerView
     * 加载Adapter
     */
    private void setRecyclerViewSecond(RecyclerView recyclerViewSecond) {
        recyclerViewSecond.setLayoutManager(new GridLayoutManager(mContext, 1));//设置布局管理器
        recyclerViewSecond.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSecond.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));//设置分割线
        SecondRecyclerViewAdapter viewAdapter = new SecondRecyclerViewAdapter(mContext);
        recyclerViewSecond.setAdapter(viewAdapter);
        initAdapterDataSecond(viewAdapter);
    }

    /**
     * 底部RecyclerView的adapter加载数据
     */
    private void initAdapterDataSecond(SecondRecyclerViewAdapter viewAdapter) {
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        arrayMap.put("最热", "最热-更多");
        arrayMap.put("颜值", "颜值-更多");
        arrayMap.put("英雄联盟", "英雄联盟-更多");
        arrayMap.put("炉石传说", "炉石传说-更多");
        arrayMap.put("DOTA2", "DOTA2-更多");
        viewAdapter.setArrayMap(arrayMap);
        viewAdapter.notifyDataSetChanged();
    }
}
