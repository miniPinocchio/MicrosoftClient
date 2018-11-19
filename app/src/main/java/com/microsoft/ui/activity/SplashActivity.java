package com.microsoft.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.microsoft.microsoftclient.R;

/**
 * 初始化页面
 * @author huiliu
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}
