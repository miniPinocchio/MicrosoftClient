package com.microsoft.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.microsoft.base.BaseActivity;
import com.microsoft.constant.MainConstant;
import com.microsoft.microsoftclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册
 *
 * @author huiliu
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.username_et)
    EditText mUsernameEt;
    @BindView(R.id.delete_usn)
    ImageView mDeleteUsn;
    @BindView(R.id.password_et)
    EditText mPasswordEt;
    @BindView(R.id.delete_psd)
    ImageView mDeletePsd;
    @BindView(R.id.code_et)
    EditText mCodeEt;
    @BindView(R.id.btn_auth_code)
    Button mBtnAuthCode;
    @BindView(R.id.register)
    Button mRegister;

    /**
     * 倒计时
     */
    private void mBtnAuthCodeCountDown() {
        handler.sendEmptyMessage(0);
    }

    private int timeCountdown = MainConstant.JUDGE_CODE_TIME;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            timeCountdown--;
            if (timeCountdown < 0) {
                timeCountdown = MainConstant.JUDGE_CODE_TIME;
                mBtnAuthCode.setClickable(true);
                mBtnAuthCode.setText("发送验证码");
                mBtnAuthCode.setBackgroundResource(R.drawable.tob_cornor_btn);
                mBtnAuthCode.setTextColor(0xff666666);
            } else {
                mBtnAuthCode.setBackgroundResource(R.drawable.toc_login_grey);
                mBtnAuthCode.setText("重新发送：" + timeCountdown);
                sendEmptyMessageDelayed(0, 1000);
                mBtnAuthCode.setClickable(false);
            }
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.delete_usn, R.id.delete_psd, R.id.btn_auth_code, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.delete_usn:
                break;
            case R.id.delete_psd:
                break;
            case R.id.btn_auth_code:
                break;
            case R.id.register:
                break;
                default:break;
        }
    }

    /**
     * 获取服务器返回的验证码，用作本地校验
     */
    private void sendCode() {
        String username = mUsernameEt.getEditableText().toString();
        if (TextUtils.isEmpty(username.trim())) {
            showToast("请输入手机号码");
        }
    }

    
}
