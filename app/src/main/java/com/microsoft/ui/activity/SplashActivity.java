package com.microsoft.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

        //处理首次安装点击打开切到后台,点击桌面图标再回来重启的问题及通过应用宝唤起在特定条件下重走逻辑的问题
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            // Activity was brought to front and not created,
            // Thus finishing this will get us to the last viewed activity
            finish();
            return;
        }

//        Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
//        startActivity(intent);
//        finish();
    }
}
