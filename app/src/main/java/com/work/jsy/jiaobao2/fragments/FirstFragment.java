package com.work.jsy.jiaobao2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.jsy.jiaobao2.R;
import com.work.jsy.jiaobao2.adapters.HomeRecyclerViewAdapter;

/**
 * 第一个界面,整个界面是由一个RecyclerView组成
 * Created by admin on 2016/8/3.
 */
public class FirstFragment extends Fragment {
    final static String TAG="FirstFragment";
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_first, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
    }

    private void findViews() {
        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        setRecyclerView(recyclerView);
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        HomeRecyclerViewAdapter viewAdapter = new HomeRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(viewAdapter);
        viewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG,TAG+"onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,TAG+"onDetach");
    }
}