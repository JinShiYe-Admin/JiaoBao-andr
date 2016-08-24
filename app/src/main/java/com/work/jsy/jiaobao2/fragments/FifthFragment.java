package com.work.jsy.jiaobao2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.work.jsy.jiaobao2.R;
import com.work.jsy.jiaobao2.adapters.ForthRecyclerViewAdapter;
import com.work.jsy.jiaobao2.util.DividerItemDecoration;

/**
 * FifthFragment界面
 * Created by admin on 2016/8/3.
 */
public class FifthFragment extends Fragment implements View.OnClickListener {
    final static String TAG = "FirstFragment";
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.item_login, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
    }

    private void findViews() {
        TextView tv_login = (TextView) mView.findViewById(R.id.tv_login);//登录
        TextView tv_register = (TextView) mView.findViewById(R.id.tv_register);//注册
        Button btn_left = (Button) mView.findViewById(R.id.btn_left);//登录后左侧按钮
        Button btn_right = (Button) mView.findViewById(R.id.btn_right);//登录后右侧按钮
        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);//底部按钮区域
        CircularImageView circularImageView = (CircularImageView) mView.findViewById(R.id.circularImageView);//个人头像
        circularImageView.setImageResource(R.drawable.meinv);
        //btn_left.setVisibility(View.VISIBLE);
        //btn_right.setVisibility(View.VISIBLE);
        setRecyclerView(recyclerView);
        tv_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);//设置布局管理器
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));//设置分割线
        ForthRecyclerViewAdapter viewAdapter = new ForthRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(viewAdapter);
        viewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
            case R.id.tv_register:
                FragmentTransaction ft =getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                // Create and show the dialog.
                LoginDialogFragment.getNewInstance().show(ft,"dialog");
                break;
            default:
                break;
        }
    }
}
