package com.work.jsy.jiaobao2.adapters;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.work.jsy.jiaobao2.R;

/**
 * Created by ShangLinMo on 2016/8/22.
 */
public class SecondRecyclerViewAdapter extends RecyclerView.Adapter<SecondRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private ArrayMap<Integer, Integer> mArrayMap;

    public void setArrayMap(ArrayMap<Integer, Integer> arrayMap) {
        mArrayMap = arrayMap;
    }

    public SecondRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public SecondRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_second_recycler, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SecondRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.tv_mFirst.setText(mArrayMap.keyAt(position));
        holder.tv_mMore.setTag(mArrayMap.keyAt(position));
        holder.tv_mMore.setOnClickListener(this);
        setRecyclerView(holder.recyclerView);
    }

    @Override
    public int getItemCount() {
        return mArrayMap == null ? 0 : mArrayMap.size();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(mContext, (int) view.getTag() + "更多", Toast.LENGTH_SHORT).show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_mFirst;
        private TextView tv_mMore;
        private RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_mFirst = (TextView) itemView.findViewById(R.id.tv_first);
            tv_mMore = (TextView) itemView.findViewById(R.id.tv_more);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
        }
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ThirdRecyclerViewAdapter viewAdapter = new ThirdRecyclerViewAdapter(mContext);
        recyclerView.setAdapter(viewAdapter);
        initAdapterData(viewAdapter);
    }

    private void initAdapterData(ThirdRecyclerViewAdapter viewAdapter) {
        ArrayMap<Integer, Integer> arrayMap = new ArrayMap<>();
        arrayMap.put(R.string.first, R.drawable.my);
        arrayMap.put(R.string.second, R.drawable.my);
        arrayMap.put(R.string.third, R.drawable.my);
        arrayMap.put(R.string.fourth, R.drawable.my);
        viewAdapter.setArrayMap(arrayMap);
        viewAdapter.notifyDataSetChanged();
    }
}
