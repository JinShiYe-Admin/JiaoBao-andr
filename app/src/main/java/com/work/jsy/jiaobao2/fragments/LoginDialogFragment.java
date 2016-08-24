package com.work.jsy.jiaobao2.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.work.jsy.jiaobao2.R;

/**
 * Created by admin on 2016/8/24.
 */
public class LoginDialogFragment extends DialogFragment implements View.OnClickListener {
    public static LoginDialogFragment newInstance;

    public static LoginDialogFragment getNewInstance() {
        if (newInstance == null) {
            newInstance = new LoginDialogFragment();
            newInstance.setStyle(STYLE_NO_TITLE,0);
        }
        return newInstance;
    }

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_dialog, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        mView.findViewById(R.id.img_cancel).setOnClickListener(this);
        mView.findViewById(R.id.register_weiXin).setOnClickListener(this);
        mView.findViewById(R.id.register_qq).setOnClickListener(this);
        mView.findViewById(R.id.login_self).setOnClickListener(this);
        mView.findViewById(R.id.register_self).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_weiXin:
                break;
            case R.id.register_qq:
                break;
            case R.id.login_self:
                break;
            case R.id.register_self:
                break;
            case R.id.img_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }
}
