package com.work.jsy.jiaobao2.circledemo.activity;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.work.jsy.jiaobao2.MainActivity;
import com.work.jsy.jiaobao2.R;
import com.work.jsy.jiaobao2.circledemo.adapter.CircleAdapter;
import com.work.jsy.jiaobao2.circledemo.bean.CircleItem;
import com.work.jsy.jiaobao2.circledemo.bean.CommentConfig;
import com.work.jsy.jiaobao2.circledemo.bean.CommentItem;
import com.work.jsy.jiaobao2.circledemo.bean.FavortItem;
import com.work.jsy.jiaobao2.circledemo.mvp.contract.CircleContract;
import com.work.jsy.jiaobao2.circledemo.mvp.presenter.CirclePresenter;
import com.work.jsy.jiaobao2.circledemo.utils.CommonUtils;
import com.work.jsy.jiaobao2.circledemo.widgets.CommentListView;
import com.work.jsy.jiaobao2.circledemo.widgets.DivItemDecoration;
import com.work.jsy.jiaobao2.circledemo.widgets.dialog.UpLoadDialog;

import java.util.List;

/**
 * @author yiw
 * @ClassName: FriendFragment
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2015-12-28 下午4:21:18
 */
public class FriendFragment extends Fragment implements CircleContract.View {

    protected static final String TAG = "FriendFragment";
    private CircleAdapter circleAdapter;
    private LinearLayout edittextbody;
    private EditText editText;
    private ImageView sendIv;

    private int screenHeight;
    private int editTextBodyHeight;
    private int currentKeyboardH;
    private int selectCircleItemH;
    private int selectCommentItemOffset;

    private CirclePresenter presenter;
    private CommentConfig commentConfig;
    private SuperRecyclerView recyclerView;
    private RelativeLayout bodyLayout;
    private LinearLayoutManager layoutManager;
    private Toolbar titleBar;

    private final static int TYPE_PULLREFRESH = 1;
    private final static int TYPE_UPLOADREFRESH = 2;
    private UpLoadDialog uploadDialog;
    private View mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.friend_activity_main, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new CirclePresenter(this);
        initView();
        presenter.loadData(TYPE_PULLREFRESH);
    }

    @Override
    public void onDetach() {
        if (presenter != null) {
            presenter.recycle();
        }
        super.onDetach();
    }

    @SuppressLint({"ClickableViewAccessibility", "InlinedApi"})
    private void initView() {

        initTitle();
        initUploadDialog();

        recyclerView = (SuperRecyclerView) mView.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DivItemDecoration(2, true));
        recyclerView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (edittextbody.getVisibility() == View.VISIBLE) {
                    updateEditTextBodyVisible(View.GONE, null);
                    return true;
                }
                return false;
            }
        });

        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.loadData(TYPE_PULLREFRESH);
                        recyclerView.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(FriendFragment.this).resumeRequests();
                }else{
                    Glide.with(FriendFragment.this).pauseRequests();
                }

            }
        });


        circleAdapter = new CircleAdapter(getActivity());
        circleAdapter.setCirclePresenter(presenter);
        recyclerView.setAdapter(circleAdapter);

        edittextbody = (LinearLayout) mView.findViewById(R.id.editTextBodyLl);
        editText = (EditText) mView.findViewById(R.id.circleEt);
        sendIv = (ImageView) mView.findViewById(R.id.sendIv);
        sendIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter != null) {
                    //发布评论
                    String content = editText.getText().toString().trim();
                    if (TextUtils.isEmpty(content)) {
                        Toast.makeText(getContext(), "评论内容不能为空...", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    presenter.addComment(content, commentConfig);
                }
                updateEditTextBodyVisible(View.GONE, null);
            }
        });

//        setViewTreeObserver();
    }

    private void initUploadDialog() {
        uploadDialog = new UpLoadDialog(getContext());
    }

    private void initTitle() {

        titleBar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        titleBar.setTitle("朋友圈");
//        titleBar.setTitleTextColor(getResources().getColor(R.color.white));


//        TextView textView = (TextView) titleBar.addAction(new TitleBar.TextAction("发布视频") {
//            @Override
//            public void performAction(View view) {
//                //Toast.makeText(MainActivity.this, "敬请期待...", Toast.LENGTH_SHORT).show();
//
////				QPManager.startRecordActivity(FriendFragment.this);
//            }
//        });
//        textView.setTextColor(getResources().getColor(R.color.white));
    }


    private void setViewTreeObserver() {
        bodyLayout = (RelativeLayout) mView.findViewById(R.id.bodyLayout);
        final ViewTreeObserver swipeRefreshLayoutVTO = bodyLayout.getViewTreeObserver();
        swipeRefreshLayoutVTO.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                bodyLayout.getWindowVisibleDisplayFrame(r);
                int statusBarH = getStatusBarHeight();//状态栏高度
                int screenH = bodyLayout.getRootView().getHeight();
                if (r.top != statusBarH) {
                    //在这个demo中r.top代表的是状态栏高度，在沉浸式状态栏时r.top＝0，通过getStatusBarHeight获取状态栏高度
                    r.top = statusBarH;
                }
                int keyboardH = screenH - (r.bottom - r.top);
                Log.d(TAG, "screenH＝ " + screenH + " &keyboardH = " + keyboardH + " &r.bottom=" + r.bottom + " &top=" + r.top + " &statusBarH=" + statusBarH);

                if (keyboardH == currentKeyboardH) {//有变化时才处理，否则会陷入死循环
                    return;
                }

                currentKeyboardH = keyboardH;
                screenHeight = screenH;//应用屏幕的高度
                editTextBodyHeight = edittextbody.getHeight();

                if (keyboardH < 150) {//说明是隐藏键盘的情况
                    updateEditTextBodyVisible(View.GONE, null);
                    return;
                }
                //偏移listview
                if (layoutManager != null && commentConfig != null) {
                    layoutManager.scrollToPositionWithOffset(commentConfig.circlePosition + CircleAdapter.HEADVIEW_SIZE, getListviewOffset(commentConfig));
                }
            }
        });
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    @Override
    public void update2DeleteCircle(String circleId) {
        List<CircleItem> circleItems = circleAdapter.getDatas();
        for (int i = 0; i < circleItems.size(); i++) {
            if (circleId.equals(circleItems.get(i).getId())) {
                circleItems.remove(i);
                circleAdapter.notifyDataSetChanged();
                //circleAdapter.notifyItemRemoved(i+1);
                return;
            }
        }
    }

    @Override
    public void update2AddFavorite(int circlePosition, FavortItem addItem) {
        if (addItem != null) {
            CircleItem item = (CircleItem) circleAdapter.getDatas().get(circlePosition);
            item.getFavorters().add(addItem);
            circleAdapter.notifyDataSetChanged();
            //circleAdapter.notifyItemChanged(circlePosition+1);
        }
    }

    @Override
    public void update2DeleteFavort(int circlePosition, String favortId) {
        CircleItem item = (CircleItem) circleAdapter.getDatas().get(circlePosition);
        List<FavortItem> items = item.getFavorters();
        for (int i = 0; i < items.size(); i++) {
            if (favortId.equals(items.get(i).getId())) {
                items.remove(i);
                circleAdapter.notifyDataSetChanged();
                //circleAdapter.notifyItemChanged(circlePosition+1);
                return;
            }
        }
    }

    @Override
    public void update2AddComment(int circlePosition, CommentItem addItem) {
        if (addItem != null) {
            CircleItem item = (CircleItem) circleAdapter.getDatas().get(circlePosition);
            item.getComments().add(addItem);
            circleAdapter.notifyDataSetChanged();
            //circleAdapter.notifyItemChanged(circlePosition+1);
        }
        //清空评论文本
        editText.setText("");
    }

    @Override
    public void update2DeleteComment(int circlePosition, String commentId) {
        CircleItem item = (CircleItem) circleAdapter.getDatas().get(circlePosition);
        List<CommentItem> items = item.getComments();
        for (int i = 0; i < items.size(); i++) {
            if (commentId.equals(items.get(i).getId())) {
                items.remove(i);
                circleAdapter.notifyDataSetChanged();
                //circleAdapter.notifyItemChanged(circlePosition+1);
                return;
            }
        }
    }

    @Override
    public void updateEditTextBodyVisible(int visibility, CommentConfig commentConfig) {
        this.commentConfig = commentConfig;
        edittextbody.setVisibility(visibility);

        measureCircleItemHighAndCommentItemOffset(commentConfig);

        if (View.VISIBLE == visibility) {
            editText.requestFocus();
            //弹出键盘
            CommonUtils.showSoftInput(editText.getContext(), editText);
            MainActivity.rg_bottom.setVisibility(View.GONE);
        } else if (View.GONE == visibility) {
            //隐藏键盘
            CommonUtils.hideSoftInput(editText.getContext(), editText);
            MainActivity.rg_bottom.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void update2loadData(int loadType, List<CircleItem> datas) {
        if (loadType == TYPE_PULLREFRESH) {
            circleAdapter.setDatas(datas);
        } else if (loadType == TYPE_UPLOADREFRESH) {
            circleAdapter.getDatas().addAll(datas);
        }
        circleAdapter.notifyDataSetChanged();

        if (circleAdapter.getDatas().size() < 45 + CircleAdapter.HEADVIEW_SIZE) {
            recyclerView.setupMoreListener(new OnMoreListener() {
                @Override
                public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            presenter.loadData(TYPE_UPLOADREFRESH);
                        }
                    }, 2000);

                }
            }, 1);
        } else {
            recyclerView.removeMoreListener();
            recyclerView.hideMoreProgress();
        }

    }


    /**
     * 测量偏移量
     * @param commentConfig
     * @return
     */
    private int getListviewOffset(CommentConfig commentConfig) {
        if (commentConfig == null)
            return 0;
        //这里如果你的listview上面还有其它占高度的控件，则需要减去该控件高度，listview的headview除外。
        //int listviewOffset = mScreenHeight - mSelectCircleItemH - mCurrentKeyboardH - mEditTextBodyHeight;
        int listviewOffset = screenHeight - selectCircleItemH - currentKeyboardH - editTextBodyHeight - titleBar.getHeight()+MainActivity.mBottomHeight;
        Log.d(TAG,"bottom"+MainActivity.mBottomHeight);
        if (commentConfig.commentType == CommentConfig.Type.REPLY) {
            //回复评论的情况
            listviewOffset = listviewOffset + selectCommentItemOffset;
        }
        Log.i(TAG, "listviewOffset : " + listviewOffset);
        return listviewOffset;
    }

    private void measureCircleItemHighAndCommentItemOffset(CommentConfig commentConfig) {
        if (commentConfig == null)
            return;

        int firstPosition = layoutManager.findFirstVisibleItemPosition();
        //只能返回当前可见区域（列表可滚动）的子项
        View selectCircleItem = layoutManager.getChildAt(commentConfig.circlePosition + CircleAdapter.HEADVIEW_SIZE - firstPosition);

        if (selectCircleItem != null) {
            selectCircleItemH = selectCircleItem.getHeight();
        }

        if (commentConfig.commentType == CommentConfig.Type.REPLY) {
            //回复评论的情况
            CommentListView commentLv = (CommentListView) selectCircleItem.findViewById(R.id.commentList);
            if (commentLv != null) {
                //找到要回复的评论view,计算出该view距离所属动态底部的距离
                View selectCommentItem = commentLv.getChildAt(commentConfig.commentPosition);
                if (selectCommentItem != null) {
                    //选择的commentItem距选择的CircleItem底部的距离
                    selectCommentItemOffset = 0;
                    View parentView = selectCommentItem;
                    do {
                        int subItemBottom = parentView.getBottom();
                        parentView = (View) parentView.getParent();
                        if (parentView != null) {
                            selectCommentItemOffset += (parentView.getHeight() - subItemBottom- MainActivity.mBottomHeight);
                            Log.d(TAG+"bottom",MainActivity.mBottomHeight+"");
                        }
                    } while (parentView != null && parentView != selectCircleItem);
                }
            }
        }
    }


//    String videoFile;
//    String[] thum;
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//		if (resultCode == RESULT_OK) {
//			RecordResult result =new RecordResult(data);
//			//得到视频地址，和缩略图地址的数组，返回十张缩略图
//			videoFile = result.getPath();
//			thum = result.getThumbnail();
//			result.getDuration();
//
//            Log.e(TAG, "视频路径:" + videoFile + "图片路径:" + thum[0]);
//
//			QPManager.getInstance(getApplicationContext()).startUpload(videoFile, thum[0], new IUploadListener() {
//                @Override
//                public void preUpload() {
//                    uploadDialog.show();
//                }
//
//                @Override
//                public void uploadComplet(String videoUrl, String imageUrl, String message) {
//                    uploadDialog.hide();
//                    Toast.makeText(FriendFragment.this, "上传成功...", Toast.LENGTH_LONG).show();
//
//                    //将新拍摄的video刷新到列表中
//                    circleAdapter.getDatas().add(0, DatasUtil.createVideoItem(videoFile, thum[0]));
//                    circleAdapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void uploadError(int errorCode, final String message) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            uploadDialog.hide();
//                            Toast.makeText(FriendFragment.this, message, Toast.LENGTH_LONG).show();
//                        }
//                    });
//                }
//
//                @Override
//                public void uploadProgress(int percentsProgress) {
//                    uploadDialog.setPercentsProgress(percentsProgress);
//                }
//            });
//
//			/**
//			 * 清除草稿,草稿文件将会删除。所以在这之前我们执行拷贝move操作。
//			 * 上面的拷贝操作请自行实现，第一版本的copyVideoFile接口不再使用
//			 */
//            /*QupaiService qupaiService = QupaiManager
//                    .getQupaiService(MainActivity.this);
//            qupaiService.deleteDraft(getApplicationContext(),data);*/
//
//		} else {
//			if (resultCode == RESULT_CANCELED) {
//				Toast.makeText(FriendFragment.this, "RESULT_CANCELED", Toast.LENGTH_LONG).show();
//			}
//		}
//	}

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorMsg) {

    }
}
