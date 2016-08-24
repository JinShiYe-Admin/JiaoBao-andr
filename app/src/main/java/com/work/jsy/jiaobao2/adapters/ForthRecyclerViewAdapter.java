package com.work.jsy.jiaobao2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.work.jsy.jiaobao2.R;

/**
 * Created by ShangLinMo on 2016/8/24.
 */
public class ForthRecyclerViewAdapter extends RecyclerView.Adapter<ForthRecyclerViewAdapter.ViewHolder>{
    private Context mContext;
    public ForthRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ForthRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_forth_recycler, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ForthRecyclerViewAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_first;
        TextView tv_second;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_first=(TextView)itemView.findViewById(R.id.tv_first);
            tv_second=(TextView)itemView.findViewById(R.id.tv_second);
        }
    }
}
