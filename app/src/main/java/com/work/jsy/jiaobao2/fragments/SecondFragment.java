package com.work.jsy.jiaobao2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.work.jsy.jiaobao2.R;
import com.work.jsy.jiaobao2.util.GlideCircleImage;

/**
 * Created by admin on 2016/8/3.
 */
public class SecondFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private Animation scaleAnimation;//动画效果
    private ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_second, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
    }

    private void findViews(){
        String url="http://imgstore04.cdn.sogou.com/app/a/100520024/877e990117d6a7ebc68f46c5e76fc47a";
        ImageView circularImageView_first=(ImageView) mView.findViewById(R.id.imageView);
        ImageView imageView_second=(ImageView)mView.findViewById(R.id.ImageView_second);
        Button button=(Button)mView.findViewById(R.id.btn_animation);
        imageView=(ImageView)mView.findViewById(R.id.imageView_animation);
        mView.findViewById(R.id.login_view).setOnClickListener(this);
        Glide.with(this).load(url).placeholder(R.drawable.my).into(circularImageView_first);
        Glide.with(this).load(url).transform(new GlideCircleImage(getActivity())).placeholder(R.drawable.my).into(imageView_second);
        scaleAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.scaleanim);//设置动画
        Glide.with(this).load(R.drawable.meinv).transform(new GlideCircleImage(getActivity())).placeholder(R.drawable.my).into(imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.startAnimation(scaleAnimation);//绑定动画
            }
        });
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction ft =getActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        LoginDialogFragment.getNewInstance().show(ft,"dialog");
    }

}
