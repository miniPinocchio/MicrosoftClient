package com.microsoft.ui.activity;

import android.os.Bundle;

import com.microsoft.base.BaseActivity;
import com.microsoft.microsoftclient.R;

/**
 * @author huiliu
 */
public class MessageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initTopBar(this, "系统消息", true);
    }
}
