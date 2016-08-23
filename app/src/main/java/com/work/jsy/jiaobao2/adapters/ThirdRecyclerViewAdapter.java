package com.work.jsy.jiaobao2.adapters;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.work.jsy.jiaobao2.R;

/**
 * Created by ShangLinMo on 2016/8/23.
 */
public class ThirdRecyclerViewAdapter extends RecyclerView.Adapter<ThirdRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private ArrayMap<Integer, Integer> mArrayMap;

    public void setArrayMap(ArrayMap<Integer, Integer> arrayMap) {
        mArrayMap = arrayMap;
    }

    public ThirdRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ThirdRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_third_recycler, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ThirdRecyclerViewAdapter.ViewHolder holder, int position) {
        //holder.mTextViewFirst.setText(mArrayMap.keyAt(position));
        //holder.mTextViewSecond.setText(mArrayMap.keyAt(position));
        holder.mImageView.setImageResource(mArrayMap.valueAt(position));
    }

    @Override
    public int getItemCount() {
        return mArrayMap == null ? 0 : mArrayMap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextViewFirst;
        TextView mTextViewSecond;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mTextViewFirst = (TextView) itemView.findViewById(R.id.textView_first);
            mTextViewSecond = (TextView) itemView.findViewById(R.id.textView_second);
        }
    }
}
