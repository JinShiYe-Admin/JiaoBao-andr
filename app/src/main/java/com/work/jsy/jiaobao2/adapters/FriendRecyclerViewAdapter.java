package com.work.jsy.jiaobao2.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.work.jsy.jiaobao2.R;
import com.work.jsy.jiaobao2.model.Supporter;
import com.work.jsy.jiaobao2.util.PopupWindowUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShangLinMo on 2016/8/26.
 */
public class FriendRecyclerViewAdapter extends RecyclerView.Adapter<FriendRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private String string = "一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十";
    private ArrayMap<Integer, Integer> mArrayMap;
    private FriendRecyclerViewAdapter mAdapter;
    private TextView supportList;
    private int num = 1;
    private List supporters = new ArrayList();
    private ArrayList<Supporter> mAllSupporters = new ArrayList<>();

    public void setArrayMap(ArrayMap<Integer, Integer> arrayMap) {
        mArrayMap = arrayMap;
    }

    public void setMyAdapter(FriendRecyclerViewAdapter friendRecyclerViewAdapter) {
        mAdapter = friendRecyclerViewAdapter;
    }

    public void setSupporters(ArrayList<Supporter> supporters) {
        mAllSupporters = supporters;
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
        setRecyclerView(holder.recyclerView_content);
        holder.textView_name.setText(mArrayMap.keyAt(position));
        holder.imageView_person.setImageResource(mArrayMap.valueAt(position));
        holder.imageView_popup.setTag(position);
        holder.imageView_popup.setOnClickListener(this);
        holder.textView_delete.setTag(mArrayMap.keyAt(position));
        holder.textView_delete.setOnClickListener(this);
        holder.textView_name.setText(mArrayMap.keyAt(position));
        holder.textView_contnt.setText(string + string);
        if (mAllSupporters != null) {
            holder.textView_supportList.setVisibility(View.VISIBLE);
            addSupport(holder.textView_supportList, position, mAllSupporters);
        }
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
        private TextView textView_supportList;
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
            textView_supportList = (TextView) itemView.findViewById(R.id.tv_supportList);
            recyclerView_comments = (RecyclerView) itemView.findViewById(R.id.recyclerView_comments);
        }
    }


    private void addSupport(TextView textView, final int position, ArrayList<Supporter> allSupporters) {
        Supporter supporter = allSupporters.get(position);
        List mSupporters = supporter.getAllSupporters();
//        if (mSupporters.size() == 0) {
//            string = String.valueOf(" 第" + (mSupporters.size() + 1) + "位");
//        } else {
//            string = String.valueOf(",第" + (mSupporters.size() + 1) + "位");
//        }
//        supporters.add(string);
        for (int i = 0; i < mSupporters.size(); i++) {
            String string = String.valueOf(mSupporters.get(i));
            final SpannableString spanString = new SpannableString(string);
            spanString.setSpan(new ClickableSpan() {
                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(Color.BLUE);
                }

                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, spanString + "-" + position, Toast.LENGTH_SHORT).show();
                }
            }, 0, spanString.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.append(spanString);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_supportList:
                //addSupport((TextView) view);
                break;
            case R.id.tv_delete:
                mArrayMap.remove(view.getTag());
                mAdapter.setArrayMap(mArrayMap);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.imageView_popup:
                PopupWindowUtil.showPopupWindow(mContext, view);
                Toast.makeText(mContext, view.getTag() + "", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        FifthRecyclerViewAdapter viewAdapter = new FifthRecyclerViewAdapter(mContext);
        recyclerView.setAdapter(viewAdapter);
        initAdapterData(viewAdapter);
    }

    private void initAdapterData(FifthRecyclerViewAdapter viewAdapter) {
        ArrayMap<Integer, Integer> arrayMap = new ArrayMap<>();
        int i = (int) (1 + Math.random() * (5 - 1 + 1));
        arrayMap.put(R.string.first, i);
        viewAdapter.setArrayMap(arrayMap);
        viewAdapter.notifyDataSetChanged();
    }
}
