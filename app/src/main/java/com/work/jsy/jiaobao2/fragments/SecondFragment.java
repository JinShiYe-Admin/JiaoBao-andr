package com.work.jsy.jiaobao2.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.work.jsy.jiaobao2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/3.
 */
public class SecondFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private TextView textView;
    private Button button;
    private List allSupporters=new ArrayList();
    private String supporter;

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

    private void findViews() {
        button = (Button) mView.findViewById(R.id.btn_animation);
        textView = (TextView) mView.findViewById(R.id.textView_animation);
        mView.findViewById(R.id.login_view).setOnClickListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSupport(textView);
            }
        });
    }

    private void addSupport(TextView textView) {
        String string;
        if (allSupporters.size() == 0) {
            string = String.valueOf(" 第" + (allSupporters.size() + 1) + "位");
        } else {
            string = String.valueOf(",第" + (allSupporters.size() + 1) + "位");
        }
        allSupporters.add(string);
        final SpannableString spanString = new SpannableString(string);
        spanString.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.BLUE);
            }

            @Override
            public void onClick(View view) {
                Log.i("onClick", spanString + "");
            }
        }, 1, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.append(spanString);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        LoginDialogFragment.getNewInstance().show(ft, "dialog");
    }

}
