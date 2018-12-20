package com.microsoft.ui.activity.share;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.microquation.linkedme.android.LinkedME;

/**
 * @author huiliu
 */
public class UriSchemeProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 唤起自身
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        assert intent != null;
        intent.setFlags(getIntent().getFlags());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // App打开后无广告展示及登录等条件限制，直接在此处调用以下方法跳转到具体页面，若有条件限制请参考Demo
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            LinkedME.getInstance().setImmediate(true);
        }
        // 防止跳转后一直停留在该页面
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // 请重写改方法并且设置该Activity的launchmode为singleTask
        setIntent(intent);
    }

}