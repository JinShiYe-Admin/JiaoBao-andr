package com.work.jsy.jiaobao2.adapters;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.work.jsy.jiaobao2.R;

/**
 * Created by ShangLinMo on 2016/9/2.
 */
public class FifthRecyclerViewAdapter extends RecyclerView.Adapter<FifthRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private ArrayMap<Integer, Integer> mArrayMap;

    public void setArrayMap(ArrayMap<Integer, Integer> arrayMap) {
        mArrayMap = arrayMap;
    }

    public FifthRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public FifthRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_fifth_recycler, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FifthRecyclerViewAdapter.ViewHolder holder, int position) {
        //holder.mTextViewFirst.setText(mArrayMap.keyAt(position));
        //holder.mTextViewSecond.setText(mArrayMap.keyAt(position));
        holder.mImageView.setImageResource(R.drawable.meinv);
        //Glide.with(mContext).load(R.drawable.my).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mArrayMap.valueAt(0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
