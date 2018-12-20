package com.microsoft.ui.activity;

import android.os.Bundle;

import com.microsoft.base.BaseActivity;
import com.microsoft.microsoftclient.R;

/**
 * @author huiliu
 * 提现
 */
public class CashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);
        initTopBar(this, "提现", true);
    }
}
