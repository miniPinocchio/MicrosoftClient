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
import android.widget.TextView;

import com.microsoft.WdApp;
import com.microsoft.base.BaseActivity;
import com.microsoft.bean.MobileCode;
import com.microsoft.constant.Constant;
import com.microsoft.microsoftclient.R;
import com.microsoft.util.GsonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 注册
 *
 * @author huiliu
 */
public class RegisterActivity extends BaseActivity implements Callback<String> {

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
    @BindView(R.id.tv_title_name)
    TextView mTvTitleName;
    private int mNetType;

    private int timeCountdown = Constant.JUDGE_CODE_TIME;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            timeCountdown--;
            if (timeCountdown < 0) {
                timeCountdown = Constant.JUDGE_CODE_TIME;
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
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mTvTitleName.setText("注册");
    }


    /**
     * 倒计时
     */
    private void mBtnAuthCodeCountDown() {
        handler.sendEmptyMessage(0);
    }


    @OnClick({R.id.delete_usn, R.id.delete_psd, R.id.btn_auth_code, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.delete_usn:

                break;
            case R.id.delete_psd:

                break;
            case R.id.btn_auth_code:
                mBtnAuthCodeCountDown();
                sendCode();
                break;
            case R.id.register:
                toRegister();
                break;
            default:
                break;
        }
    }

    private void toRegister() {
        String username = mUsernameEt.getEditableText().toString();
        if (TextUtils.isEmpty(username)) {
            showToast("请输入手机号码");
            mRegister.setEnabled(true);
            mRegister.setText("注册");
            return;
        }
        String password = mPasswordEt.getEditableText().toString();
        if (TextUtils.isEmpty(password) || password.length() > 16 || password.length() < 6) {
            showToast("请输入6-16位密码");
            mRegister.setEnabled(true);
            mRegister.setText("注册");
            return;
        }
        String code = mCodeEt.getEditableText().toString();
        if (TextUtils.isEmpty(code)) {
            showToast("请输入验证码");
            mRegister.setEnabled(true);
            mRegister.setText("注册");
            return;
        }
        mNetType = 2;
        WdApp.getRetrofit().takeRegister(username, password, code).enqueue(this);
    }

    /**
     * 获取服务器返回的验证码，用作本地校验
     */
    private void sendCode() {
        mNetType = 1;
        String username = mUsernameEt.getEditableText().toString();
        if (TextUtils.isEmpty(username.trim())) {
            showToast("请输入手机号码");
            return;
        }
        WdApp.getRetrofit().takeMobileCode(username).enqueue(this);
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        String body = response.body();
        if (response.isSuccessful()) {
            resolveData(body);
        }
    }

    private void resolveData(String body) {
        if (body != null) {
            if (mNetType == 1) {
                MobileCode mobileCode = GsonUtil.parseJsonWithGson(body, MobileCode.class);
                if (Constant.NET_STATUS.equals(mobileCode.getCode())) {
                    showToast(mobileCode.getMsg());
                }else {
                    showToast(mobileCode.getMsg());
                }
            } else if (mNetType == 2) {
                MobileCode mobileCode = GsonUtil.parseJsonWithGson(body, MobileCode.class);
                if (Constant.NET_STATUS.equals(mobileCode.getCode())) {
                    showToast(mobileCode.getMsg());
                    startAct(MainActivity.class);
                }else {
                    showToast(mobileCode.getMsg());
                }
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        showToast(getString(R.string.network_failure));
    }
}
