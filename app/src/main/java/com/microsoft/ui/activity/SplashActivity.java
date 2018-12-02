package com.microsoft.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.microsoft.base.BaseActivity;
import com.microsoft.base.UserService;
import com.microsoft.bean.LoginBean;
import com.microsoft.microsoftclient.R;

/**
 * 初始化页面
 *
 * @author huiliu
 */
public class SplashActivity extends BaseActivity {

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LoginBean userInfo = UserService.getUserInfo();
            if (userInfo != null) {
                startAct(MainActivity.class);
            } else {
                startAct(LoginActivity.class);
            }
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler.sendEmptyMessageDelayed(998, 2000);
    }
}
