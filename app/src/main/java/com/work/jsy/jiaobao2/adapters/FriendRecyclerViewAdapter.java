package com.work.jsy.jiaobao2.adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
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
    private FriendRecyclerViewAdapter mAdapter;
    public void setArrayMap(ArrayMap<Integer, Integer> arrayMap) {
        mArrayMap = arrayMap;
    }

    public void setMyAdapter(FriendRecyclerViewAdapter friendRecyclerViewAdapter) {
        mAdapter = friendRecyclerViewAdapter;
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String string = "一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十";
        holder.textView_name.setText(mArrayMap.keyAt(position));
        holder.textView_contnt.setText(string + string);
        holder.imageView_person.setImageResource(mArrayMap.valueAt(position));
        holder.imageView_popup.setTag(position);
        holder.imageView_popup.setOnClickListener(this);
        holder.textView_delete.setTag(mArrayMap.keyAt(position));
        holder.textView_delete.setOnClickListener(this);
        setRecyclerView(holder.recyclerView_content);
    }

    @Override
    public int getItemCount() {
        return mArrayMap == null ? 0 : mArrayMap.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView_person;
        private TextView textView_name;
        private TextView textView_contnt;
        private RecyclerView recyclerView_content;
        private TextView textView_time;
        private TextView textView_from;
        private TextView textView_delete;
        private ImageView imageView_popup;
        private RecyclerView recyclerView_support;
        private RecyclerView recyclerView_comments;

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

    private void showPopupWindow(View view) {
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
        tv_support.setOnClickListener(this);
        tv_comment.setOnClickListener(this);
        //显示PopupWindow
        mPopWindow.showAsDropDown(view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_support:
            case R.id.tv_comment:
                Toast.makeText(mContext, ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                break;
            case R.id.tv_delete:
                mArrayMap.remove(view.getTag());
                mAdapter.setArrayMap(mArrayMap);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.imageView_popup:
                showPopupWindow(view);
                Toast.makeText(mContext, view.getTag() + "", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        FifthRecyclerViewAdapter viewAdapter = new FifthRecyclerViewAdapter(mContext);
        recyclerView.setAdapter(viewAdapter);
        initAdapterData(viewAdapter);
    }

    private void initAdapterData(FifthRecyclerViewAdapter viewAdapter) {
        ArrayMap<Integer, Integer> arrayMap = new ArrayMap<>();
        int i = (int) (1 + Math.random() * (10 - 1 + 1));
        arrayMap.put(R.string.first,i);
        viewAdapter.setArrayMap(arrayMap);
        viewAdapter.notifyDataSetChanged();
    }
}
