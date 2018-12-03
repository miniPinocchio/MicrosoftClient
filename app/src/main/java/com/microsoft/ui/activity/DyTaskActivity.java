package com.microsoft.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.WdApp;
import com.microsoft.base.BaseActivity;
import com.microsoft.base.LoadMoreAdapter;
import com.microsoft.base.UserService;
import com.microsoft.bean.DyListBean;
import com.microsoft.bean.DyMiddleBean;
import com.microsoft.bean.DyRootBean;
import com.microsoft.bean.LoginBean;
import com.microsoft.constant.Constant;
import com.microsoft.dapter.TaskDyListAdapter;
import com.microsoft.microsoftclient.R;
import com.microsoft.util.GsonUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author huiliu
 * 抖音任务
 */
public class DyTaskActivity extends BaseActivity implements Callback<String>, View.OnClickListener, LoadMoreAdapter.LoadMoreApi, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.iv_title_arrow)
    ImageView mIvTitleArrow;
    @BindView(R.id.tv_title_name)
    TextView mTvTitleName;
    @BindView(R.id.rv_douyin_list)
    RecyclerView mRvDouyinList;
    @BindView(R.id.bga_douyin_list)
    BGARefreshLayout mBgaDouyinList;
    private String id; //用户id
    private ArrayList<DyListBean> mMessages;
    private LoadMoreAdapter mLoadMoreAdapter;
    private TaskDyListAdapter mAdapter;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dytask);
        ButterKnife.bind(this);
        initTopBar(this, "任务", true);
        LoginBean userInfo = UserService.getUserInfo();
        if (userInfo != null) {
            mId = userInfo.getId();
            getDyList(mId);
        }
        initView();
        initRefreshLayout();

    }

    private void initView() {
        mMessages = new ArrayList<>();
        mAdapter = new TaskDyListAdapter(this, mMessages, this);
        mLoadMoreAdapter = new LoadMoreAdapter(this, mAdapter);
        mLoadMoreAdapter.setLoadMoreListener(this);
        mRvDouyinList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvDouyinList.setAdapter(mLoadMoreAdapter);
    }

    private void initRefreshLayout() {
        // 为BGARefreshLayout 设置代理
        mBgaDouyinList.setDelegate(this);
        BGAMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new BGAMeiTuanRefreshViewHolder(this, true);
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);
        mBgaDouyinList.setRefreshViewHolder(meiTuanRefreshViewHolder);
    }

    private void getDyList(String userId) {
        WdApp.getRetrofit().takeDyTask(userId).enqueue(this);
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        String body = response.body();
        if (response.isSuccessful()) {
            resolveData(body);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        showToast(getString(R.string.network_failure));
    }

    private void resolveData(String body) {
        if (body != null) {
            DyRootBean dyRootBean = GsonUtil.parseJsonWithGson(body, DyRootBean.class);
            if (Constant.NET_STATUS.equals(dyRootBean.getCode())) {
                DyMiddleBean rootBeanData = dyRootBean.getData();
                if (rootBeanData.getRegTasks() != null) {
                    mMessages.addAll(rootBeanData.getRegTasks());
                    if (mMessages == null || mMessages.size() <= 0) {
                        if (mBgaDouyinList != null) {
                            mBgaDouyinList.endRefreshing();
                        }
                        mLoadMoreAdapter.loadAllDataCompleted();
                        return;
                    }
                    mLoadMoreAdapter.loadCompleted();
                    mBgaDouyinList.endRefreshing();
                }
            }
        }

    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        Intent intent = new Intent(this, TaskDetailActivity.class);
        String taskId = mMessages.get(position).getTaskId();
        intent.putExtra(Constant.TASK_ID, taskId);
        intent.putExtra(Constant.TASK_FLAG, Constant.DY_FLAG);
        startActivity(intent);
    }

    @Override
    public void loadMore() {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                mLoadMoreAdapter.loadAllDataCompleted();
            }
        };
        handler.post(r);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMessages.clear();
        getDyList(mId);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
