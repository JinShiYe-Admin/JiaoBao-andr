package com.work.jsy.jiaobao2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.jsy.jiaobao2.R;
import com.work.jsy.jiaobao2.adapters.FriendRecyclerViewAdapter;
import com.work.jsy.jiaobao2.util.DividerItemDecoration;

/**
 * Created by admin on 2016/8/3.
 */
public class ThirdFragment extends Fragment {
    private final static String TAG = "ThirdFragment";
    private ArrayMap<Integer, Integer> arrayMap = new ArrayMap<>();
    private View mView;
    private Context mContext;
    private RecyclerView recyclerView;
    private FriendRecyclerViewAdapter viewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_third, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
    }

    private void findViews() {
        mContext=getContext();
        recyclerView=(RecyclerView)mView.findViewById(R.id.recyclerView);
        setRecyclerView(recyclerView);
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));//设置布局管理器
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));//设置分割线
        viewAdapter = new FriendRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(viewAdapter);
        viewAdapter.setMyAdapter(viewAdapter);
        initAdapterData(viewAdapter);
    }
    /**
     * RecyclerView的adapter加载数据
     */
    private void initAdapterData(FriendRecyclerViewAdapter viewAdapter) {
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
}
