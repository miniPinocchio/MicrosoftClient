package com.microsoft.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.microsoft.base.BaseFragment;
import com.microsoft.microsoftclient.R;
import com.microsoft.ui.activity.DyTaskActivity;
import com.microsoft.ui.activity.KsTaskActivity;
import com.microsoft.ui.activity.QrCodeActivity;
import com.microsoft.widget.MarqueeTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author huiliu
 */
public class TaskFragment extends BaseFragment {


    @BindView(R.id.ll_dy_task)
    LinearLayout mLlDyTask;
    @BindView(R.id.ll_ks_task)
    LinearLayout mLlKsTask;
    @BindView(R.id.ll_more_task)
    LinearLayout mLlMoreTask;
    Unbinder unbinder;
    @BindView(R.id.tv_share_money)
    TextView mTvShareMoney;
    @BindView(R.id.marquee_notification)
    MarqueeTextView mMarqueeNotification;

    private String[] marquees = {"张**:在本平台提现200.00元", "王**:在本平台提现500.00元", "刘**:在本平台提现300.00元"};

    public TaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        unbinder = ButterKnife.bind(this, view);
        initTopBar(view, "任务", false);
        initView();
        return view;
    }

    private void initView() {
        mMarqueeNotification.setTextArrays(marquees);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_dy_task, R.id.ll_ks_task, R.id.ll_more_task, R.id.tv_share_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_dy_task://抖音投票点赞任务
                startAct(DyTaskActivity.class);
                break;
            case R.id.ll_ks_task://快手投票点赞任务
                startAct(KsTaskActivity.class);
                break;
            case R.id.ll_more_task:
                showToast("敬请期待 更多功能.");
                break;
            case R.id.tv_share_money://分享
                startAct(QrCodeActivity.class);
                break;
            default:
                break;
        }
    }

}