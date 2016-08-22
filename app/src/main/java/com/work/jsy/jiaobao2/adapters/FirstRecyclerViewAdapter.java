package com.work.jsy.jiaobao2.adapters;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.work.jsy.jiaobao2.R;


/**
 * adapter
 * Created by admin on 2016/8/3.
 */
public class FirstRecyclerViewAdapter extends RecyclerView.Adapter<FirstRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private ArrayMap<Integer, Integer> mArrayMap;

    public void setArrayMap(ArrayMap<Integer, Integer> arrayMap) {
        mArrayMap = arrayMap;
    }

    public FirstRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public FirstRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_first_recycler, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FirstRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.mTextView.setText(mArrayMap.keyAt(position));
        holder.mImageView.setImageResource(mArrayMap.valueAt(position));
        holder.mLinearLayout.setTag(position);
        holder.mLinearLayout.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mArrayMap == null ? 0 : mArrayMap.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private CircularImageView mImageView;
        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.item_linearLayout);
            mImageView = (CircularImageView) itemView.findViewById(R.id.imageView);
            mTextView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    /**
     * 监听事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch ((int) view.getTag()) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }
}
