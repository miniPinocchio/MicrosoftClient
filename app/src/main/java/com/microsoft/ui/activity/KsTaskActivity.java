package com.microsoft.ui.activity;

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
import com.microsoft.dapter.TaskKsListAdapter;
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
 * 快手任务列表
 *
 * @author huiliu
 */
public class KsTaskActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate, View.OnClickListener, LoadMoreAdapter.LoadMoreApi, Callback<String> {

    @BindView(R.id.iv_title_arrow)
    ImageView mIvTitleArrow;
    @BindView(R.id.tv_title_name)
    TextView mTvTitleName;
    @BindView(R.id.rv_ks_list)
    RecyclerView mRvKsList;
    @BindView(R.id.bga_ks_list)
    BGARefreshLayout mBgaKsList;
    private String id; //用户id
    private ArrayList<DyListBean> mMessages;
    private LoadMoreAdapter mLoadMoreAdapter;
    private TaskKsListAdapter mAdapter;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ks_task);
        ButterKnife.bind(this);
        initTopBar(this, "快手任务", true);
        LoginBean userInfo = UserService.getUserInfo();
        if (userInfo != null) {
            mId = userInfo.getId();
            getKsList(mId);
        }
        initView();
        initRefreshLayout();

    }

    private void initView() {
        mMessages = new ArrayList<>();
        mAdapter = new TaskKsListAdapter(this, mMessages, this);
        mLoadMoreAdapter = new LoadMoreAdapter(this, mAdapter);
        mLoadMoreAdapter.setLoadMoreListener(this);
        mRvKsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvKsList.setAdapter(mLoadMoreAdapter);
    }

    private void initRefreshLayout() {
        // 为BGARefreshLayout 设置代理
        mBgaKsList.setDelegate(this);
        BGAMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new BGAMeiTuanRefreshViewHolder(this, true);
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);
        mBgaKsList.setRefreshViewHolder(meiTuanRefreshViewHolder);
    }

    private void getKsList(String userId) {
        WdApp.getRetrofit().takeKsTask(userId).enqueue(this);
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
                        if (mBgaKsList != null) {
                            mBgaKsList.endRefreshing();
                        }
                        mLoadMoreAdapter.loadAllDataCompleted();
                        return;
                    }
                    mLoadMoreAdapter.loadCompleted();
                    mBgaKsList.endRefreshing();
                }
            }
        }

    }

    @Override
    public void onClick(View v) {

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
        getKsList(mId);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
