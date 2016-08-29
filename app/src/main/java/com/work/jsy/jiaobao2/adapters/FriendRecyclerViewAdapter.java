package com.work.jsy.jiaobao2.adapters;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.work.jsy.jiaobao2.R;

/**
 * Created by ShangLinMo on 2016/8/26.
 */
public class FriendRecyclerViewAdapter extends RecyclerView.Adapter<FriendRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private ArrayMap<Integer, Integer> mArrayMap;

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

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_person;
        TextView textView_name;
        TextView textView_contnt;
        RecyclerView recyclerView_content;
        TextView textView_time;
        TextView textView_from;
        TextView textView_delete;
        Button btn_popup;
        RecyclerView recyclerView_support;
        RecyclerView recyclerView_comments;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView_person = (ImageView) itemView.findViewById(R.id.img_person);
            textView_name = (TextView) itemView.findViewById(R.id.tv_name);
            textView_contnt = (TextView) itemView.findViewById(R.id.tv_content);
            recyclerView_content = (RecyclerView) itemView.findViewById(R.id.recyclerView_content);
            textView_time = (TextView) itemView.findViewById(R.id.tv_time);
            textView_from = (TextView) itemView.findViewById(R.id.tv_from);
            textView_delete = (TextView) itemView.findViewById(R.id.tv_delete);
            btn_popup = (Button) itemView.findViewById(R.id.btn_popup);
            recyclerView_support = (RecyclerView) itemView.findViewById(R.id.recyclerView_support);
            recyclerView_comments = (RecyclerView) itemView.findViewById(R.id.recyclerView_comments);
        }
    }
}
