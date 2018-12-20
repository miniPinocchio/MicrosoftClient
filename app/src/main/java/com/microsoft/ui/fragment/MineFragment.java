package com.microsoft.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.microsoft.base.BaseFragment;
import com.microsoft.base.UserService;
import com.microsoft.bean.LoginBean;
import com.microsoft.microsoftclient.R;
import com.microsoft.ui.activity.CashActivity;
import com.microsoft.ui.activity.CustomerActivity;
import com.microsoft.ui.activity.MessageActivity;
import com.microsoft.ui.activity.PropertyActivity;
import com.microsoft.ui.activity.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author huiliu
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.iv_mine_setting)
    ImageView mIvMineSetting;
    @BindView(R.id.iv_mine_user_photo)
    RoundedImageView mIvMineUserPhoto;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_phone)
    TextView mTvUserPhone;
    @BindView(R.id.tv_user_grade)
    TextView mTvUserGrade;
    @BindView(R.id.btn_my_property)
    Button mBtnMyProperty;
    @BindView(R.id.btn_scan_code)
    Button mBtnScanCode;
    @BindView(R.id.iv_task_record)
    ImageView mIvTaskRecord;
    @BindView(R.id.rl_task_record)
    RelativeLayout mRlTaskRecord;
    @BindView(R.id.iv_task_customer)
    ImageView mIvTaskCustomer;
    @BindView(R.id.rl_task_customer)
    RelativeLayout mRlTaskCustomer;
    @BindView(R.id.iv_task_my_message)
    ImageView mIvTaskMyMessage;
    @BindView(R.id.rl_task_my_message)
    RelativeLayout mRlTaskMyMessage;
    @BindView(R.id.iv_task_version_update)
    ImageView mIvTaskVersionUpdate;
    @BindView(R.id.rl_task_version_update)
    RelativeLayout mRlTaskVersionUpdate;
    Unbinder unbinder;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        LoginBean userInfo = UserService.getUserInfo();

        mTvUserName.setText(userInfo.getName() != null ? userInfo.getName() : "");
        mTvUserPhone.setText(userInfo.getMobile());
        mTvUserGrade.setText(userInfo.getGrade());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_mine_setting, R.id.tv_user_grade, R.id.btn_my_property, R.id.btn_scan_code, R.id.rl_task_record, R.id.rl_task_customer, R.id.rl_task_my_message, R.id.rl_task_version_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_setting://设置
                startAct(SettingActivity.class);
                break;
            case R.id.tv_user_grade://会员等级
                break;
            case R.id.btn_my_property://资产
                startAct(PropertyActivity.class);
                break;
            case R.id.btn_scan_code://二维码
                startAct(CashActivity.class);
                break;
            case R.id.rl_task_record://任务明细
                startAct(PropertyActivity.class);
                break;
            case R.id.rl_task_customer://客服中心
                startAct(CustomerActivity.class);
                break;
            case R.id.rl_task_my_message://我的消息
                startAct(MessageActivity.class);
                break;
            case R.id.rl_task_version_update://升级
                break;
            default:
                break;
        }
    }
}
