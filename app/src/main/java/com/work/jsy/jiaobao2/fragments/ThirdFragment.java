package com.work.jsy.jiaobao2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.jsy.jiaobao2.R;
import com.work.jsy.jiaobao2.adapters.FriendRecyclerViewAdapter;

/**
 * Created by admin on 2016/8/3.
 */
public class ThirdFragment extends Fragment {
    final static String TAG = "ThirdFragment";
    private View mView;

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
        RecyclerView recyclerView=(RecyclerView)mView.findViewById(R.id.recyclerView);
        setRecyclerView(recyclerView);
    }
    private void setRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));//设置布局管理器
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        FriendRecyclerViewAdapter viewAdapter = new FriendRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(viewAdapter);
        viewAdapter.notifyDataSetChanged();
    }
}
