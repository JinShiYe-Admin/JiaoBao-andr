package com.work.jsy.jiaobao2.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
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
    private String string = "一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十";
    private ArrayMap<Integer, Integer> mArrayMap;
    private PopupWindow mPopWindow;
    private FriendRecyclerViewAdapter mAdapter;
    private TextView supportList;

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
        setRecyclerView(holder.recyclerView_content);
        holder.textView_name.setText(mArrayMap.keyAt(position));
        holder.imageView_person.setImageResource(mArrayMap.valueAt(position));
        holder.imageView_popup.setTag(position);
        holder.imageView_popup.setOnClickListener(this);
        holder.textView_delete.setTag(mArrayMap.keyAt(position));
        holder.textView_delete.setOnClickListener(this);
        holder.textView_name.setText(mArrayMap.keyAt(position));
        holder.textView_contnt.setText(string + string);
        holder.textView_supportList.setTag(mArrayMap.keyAt(position));
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
        tv_support.setTag(view.getTag());
        tv_support.setOnClickListener(this);
        tv_comment.setOnClickListener(this);
        mPopWindow.setAnimationStyle(R.style.social_pop_anim);//绑定动画效果
        int[] location = new int[2];
        view.getLocationOnScreen(location);//获取view x,y坐标
        //WRAP_CONTENT时，必须通过这种方式才能获取PopWindow的宽度
        mPopWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popWidth = mPopWindow.getContentView().getMeasuredWidth();//获取pop宽度
        //显示PopupWindow,出现在view左侧
        mPopWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] - popWidth, location[1]);
    }

    private void addSurpport(TextView textView) {
        SpannableString spanString = new SpannableString("颜色1");
        ForegroundColorSpan span = new ForegroundColorSpan(Color.BLUE);
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(spanString);
        mAdapter.getItemId(position);
    }

    int position;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_supportList:
                addSurpport((TextView) view);
                break;
            case R.id.tv_support:
                position = (int) view.getTag();
                Log.i("position", position + "");
                break;
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
