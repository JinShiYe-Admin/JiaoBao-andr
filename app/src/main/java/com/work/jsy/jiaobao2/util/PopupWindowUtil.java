package com.work.jsy.jiaobao2.util;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.work.jsy.jiaobao2.R;

/**
 * Created by ShangLinMo on 2016/9/1.
 */
public class PopupWindowUtil {
    /**
     * ThirdFragment 点赞和评论
     * @param mContext
     * @param view
     */
    public static void showPopupWindow(final Context mContext, View view) {
        PopupWindow mPopWindow;
        //设置contentView
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.item_popup_support_comment, null);
        mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置各个控件的点击响应
        TextView tv_support = (TextView) contentView.findViewById(R.id.tv_support);
        TextView tv_comment = (TextView) contentView.findViewById(R.id.tv_comment);
        tv_support.setTag(view.getTag());
        tv_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                Toast.makeText(mContext, position + "", Toast.LENGTH_SHORT).show();
            }
        });
        tv_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        mPopWindow.setAnimationStyle(R.style.social_pop_anim);//绑定动画效果
        int[] location = new int[2];
        view.getLocationOnScreen(location);//获取view x,y坐标
        //WRAP_CONTENT时，必须通过这种方式才能获取PopWindow的宽度
        mPopWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popWidth = mPopWindow.getContentView().getMeasuredWidth();//获取pop宽度
        //显示PopupWindow,出现在view左侧
        mPopWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] - popWidth, location[1]);
    }
}
