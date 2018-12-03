package com.microsoft.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.microsoft.base.BaseFragment;
import com.microsoft.microsoftclient.R;
import com.microsoft.ui.activity.DyTaskActivity;
import com.microsoft.ui.activity.KsTaskActivity;

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


    @BindView(R.id.iv_dy_tsk)
    ImageView mIvDyTsk;
    @BindView(R.id.iv_ks_tsk)
    ImageView mIvKsTsk;
    Unbinder unbinder;

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
        return view;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
