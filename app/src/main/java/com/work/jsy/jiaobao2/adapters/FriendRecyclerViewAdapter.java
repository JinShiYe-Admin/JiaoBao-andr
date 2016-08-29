package com.work.jsy.jiaobao2.adapters;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.work.jsy.jiaobao2.R;

/**
 * Created by ShangLinMo on 2016/8/26.
 */
public class FriendRecyclerViewAdapter extends RecyclerView.Adapter<FriendRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private ArrayMap<Integer, Integer> mArrayMap;
    private PopupWindow mPopWindow;

    public void setArrayMap(ArrayMap<Integer, Integer> arrayMap) {
        mArrayMap = arrayMap;
    }

    public FriendRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_friend_recycler, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        imageView_popup.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return 5;
    }
    ImageView imageView_person;
    TextView textView_name;
    TextView textView_contnt;
    RecyclerView recyclerView_content;
    TextView textView_time;
    TextView textView_from;
    TextView textView_delete;
    ImageView imageView_popup;
    RecyclerView recyclerView_support;
    RecyclerView recyclerView_comments;
    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
            imageView_person = (ImageView) itemView.findViewById(R.id.img_person);
            textView_name = (TextView) itemView.findViewById(R.id.tv_name);
            textView_contnt = (TextView) itemView.findViewById(R.id.tv_content);
            recyclerView_content = (RecyclerView) itemView.findViewById(R.id.recyclerView_content);
            textView_time = (TextView) itemView.findViewById(R.id.tv_time);
            textView_from = (TextView) itemView.findViewById(R.id.tv_from);
            textView_delete = (TextView) itemView.findViewById(R.id.tv_delete);
            imageView_popup = (ImageView) itemView.findViewById(R.id.imageView_popup);
            recyclerView_support = (RecyclerView) itemView.findViewById(R.id.recyclerView_support);
            recyclerView_comments = (RecyclerView) itemView.findViewById(R.id.recyclerView_comments);
        }

    }
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.item_popup_support_comment, null);
        mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_support = (TextView) contentView.findViewById(R.id.tv_support);
        TextView tv_comment = (TextView) contentView.findViewById(R.id.tv_comment);
        tv_support.setOnClickListener(this);
        tv_comment.setOnClickListener(this);
        //显示PopupWindow
        View rootview = imageView_popup;
        mPopWindow.showAsDropDown(rootview,-50,-50);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_support:
            case R.id.tv_comment:
                Toast.makeText(mContext, ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                break;
            case R.id.imageView_popup:
                showPopupWindow();
                break;
            default:
                break;
        }

    }
}
