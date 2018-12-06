package com.microsoft.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.microsoft.base.BaseActivity;
import com.microsoft.microsoftclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author huiliu
 */
public class CustomerActivity extends BaseActivity {

    @BindView(R.id.rv_customer_center)
    RecyclerView mRvCustomerCenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        ButterKnife.bind(this);
        initTopBar(this, "客服中心", true);


    }


}
