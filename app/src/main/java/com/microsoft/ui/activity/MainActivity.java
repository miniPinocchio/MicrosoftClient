package com.microsoft.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.microsoft.base.BaseActivity;
import com.microsoft.microsoftclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页
 *
 * @author huiliu
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_dy_tsk)
    ImageView ivDyTsk;
    @BindView(R.id.iv_ks_tsk)
    ImageView ivKsTsk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTopBar(this, getResources().getString(R.string.dy_ks));
    }

    @OnClick({R.id.iv_dy_tsk, R.id.iv_ks_tsk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_dy_tsk://抖音投票点赞任务
                startAct(DyTaskActivity.class);
                break;
            case R.id.iv_ks_tsk://快手投票点赞任务
                startAct(KsTaskActivity.class);
                break;
            default:
                break;
        }
    }
}
