package com.microsoft.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.microsoft.base.BaseActivity;
import com.microsoft.microsoftclient.R;
import com.microsoft.util.ApkUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author huiliu
 */
public class CustomerActivity extends BaseActivity {


    @BindView(R.id.ll_service_first)
    LinearLayout mLlServiceFirst;
    @BindView(R.id.ll_service_second)
    LinearLayout mLlServiceSecond;

    public static final String QQ_PACKAGE = "com.tencent.mobileqq";
    public static final String QQ1 = "568995152";
    public static final String QQ2 = "37735306";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        ButterKnife.bind(this);
        initTopBar(this, "客服中心", true);
    }


    @OnClick({R.id.ll_service_first, R.id.ll_service_second})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_service_first:
                openQqChat(QQ_PACKAGE, QQ1);
                break;
            case R.id.ll_service_second:
                openQqChat(QQ_PACKAGE, QQ2);
                break;
            default:
                break;
        }
    }

    private void openQqChat(String qq, String qqNumber) {
        boolean available = ApkUtil.isAvailable(this, qq);
        if (available) {
            String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber;
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
        } else {
            showToast("请安装手机qq或tim");
        }
    }
}
