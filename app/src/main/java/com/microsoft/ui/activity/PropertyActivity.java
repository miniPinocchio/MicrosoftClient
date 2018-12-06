package com.microsoft.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.microsoft.WdApp;
import com.microsoft.base.BaseActivity;
import com.microsoft.base.LoadMoreAdapter;
import com.microsoft.base.UserService;
import com.microsoft.bean.LoginBean;
import com.microsoft.bean.PropertyBean;
import com.microsoft.bean.PropertyListBean;
import com.microsoft.bean.PropertyRoot;
import com.microsoft.constant.Constant;
import com.microsoft.dapter.TaskListAdapter;
import com.microsoft.microsoftclient.R;
import com.microsoft.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author liuhui
 * introduce  收益明细
 */
public class PropertyActivity extends BaseActivity implements Callback<String>, LoadMoreAdapter.LoadMoreApi, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.tv_txt_user_balance)
    TextView mTvTxtUserBalance;
    @BindView(R.id.tv_user_balance)
    TextView mTvUserBalance;
    @BindView(R.id.tv_user_get_money)
    TextView mTvUserGetMoney;
    @BindView(R.id.tv_balance_detail)
    TextView mTvBalanceDetail;
    @BindView(R.id.rv_balance_detail)
    RecyclerView mRvBalanceDetail;
    @BindView(R.id.bga_balance_detail)
    BGARefreshLayout mBgaBalanceDetail;

    private ArrayList<PropertyBean> mMessages;
    private LoadMoreAdapter mLoadMoreAdapter;
    private TaskListAdapter mAdapter;
    private int mNetType;

    int page = 0;
    int size = 10;
    private LoginBean mUserInfo;
    private String mUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        ButterKnife.bind(this);
        initTopBar(this, "收益明细", true);
        mUserInfo = UserService.getUserInfo();
        mUserId = mUserInfo.getId();
        int money = mUserInfo.getMoney();
        mTvUserBalance.setText(String.valueOf(money));
        initView();
        initRefreshLayout();
        getTaskList(mUserId);
    }

    private void getTaskList(String userId) {
        mNetType = 1;
        WdApp.getRetrofit().takeMyTask(userId, "", page, size).enqueue(this);
    }


    private void initView() {
        mMessages = new ArrayList<>();
        mAdapter = new TaskListAdapter(this, mMessages);
        mLoadMoreAdapter = new LoadMoreAdapter(this, mAdapter);
        mLoadMoreAdapter.setLoadMoreListener(this);
        mRvBalanceDetail.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvBalanceDetail.setAdapter(mLoadMoreAdapter);
    }

    private void initRefreshLayout() {
        // 为BGARefreshLayout 设置代理
        mBgaBalanceDetail.setDelegate(this);
        BGAMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new BGAMeiTuanRefreshViewHolder(this, true);
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);
        mBgaBalanceDetail.setRefreshViewHolder(meiTuanRefreshViewHolder);
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

    @Override
    public void loadMore() {
        page += 1;
        getTaskList(mUserId);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        page = 0;
        mMessages.clear();
        getTaskList(mUserId);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    private void resolveData(String body) {
        if (body != null) {
            if (mNetType == 1) {
                PropertyRoot propertyRoot = GsonUtil.parseJsonWithGson(body, PropertyRoot.class);
                if (Constant.NET_STATUS.equals(propertyRoot.getCode())) {
                    PropertyListBean propertyRootData = propertyRoot.getData();
                    List<PropertyBean> logs = propertyRootData.getLogs();
                    if (logs != null && logs.size() > 0) {
                        mMessages.addAll(logs);
                        if (mMessages == null || mMessages.size() <= 0) {
                            if (mBgaBalanceDetail != null) {
                                mBgaBalanceDetail.endRefreshing();
                            }
                            mLoadMoreAdapter.loadAllDataCompleted();
                            return;
                        }
                        mLoadMoreAdapter.loadCompleted();
                        mBgaBalanceDetail.endRefreshing();
                    } else {
                        mLoadMoreAdapter.loadAllDataCompleted();
                        mBgaBalanceDetail.endRefreshing();
                    }
                } else {
                    showToast(propertyRoot.getMsg());
                    if (mBgaBalanceDetail != null) {
                        mBgaBalanceDetail.endRefreshing();
                    }
                    mLoadMoreAdapter.loadAllDataCompleted();
                }
            }
        }

    }
}
