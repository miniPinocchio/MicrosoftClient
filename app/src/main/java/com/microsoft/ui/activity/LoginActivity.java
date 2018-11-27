package com.microsoft.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import com.microsoft.WdApp;
import com.microsoft.base.BaseActivity;
import com.microsoft.microsoftclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登陆
 *
 * @author huiliu
 */
public class LoginActivity extends BaseActivity implements Callback<String> {

    @BindView(R.id.space)
    Space mSpace;
    @BindView(R.id.tv_logo_name)
    TextView mTvLogoName;
    @BindView(R.id.username_et)
    EditText mUsernameEt;
    @BindView(R.id.delete_usn)
    ImageView mDeleteUsn;
    @BindView(R.id.password_et)
    EditText mPasswordEt;
    @BindView(R.id.delete_psd)
    ImageView mDeletePsd;
    @BindView(R.id.forget_tv)
    TextView mForgetTv;
    @BindView(R.id.login_btn)
    Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        mPasswordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mDeletePsd.setVisibility(View.VISIBLE);
                } else {
                    mDeletePsd.setVisibility(View.INVISIBLE);
                }

            }
        });

        mUsernameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    mDeleteUsn.setVisibility(View.VISIBLE);
                } else {
                    mDeleteUsn.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @OnClick({R.id.delete_usn, R.id.delete_psd, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.delete_usn:
                mUsernameEt.setText("");
                break;
            case R.id.delete_psd:
                mPasswordEt.setText("");
                break;
            case R.id.login_btn:
                toLogin();
                break;
            default:
                break;
        }
    }

    /**
     * 登陆
     */
    private void toLogin() {
        String username = mUsernameEt.getText().toString();
        String password = mPasswordEt.getText().toString();
        if (!TextUtils.isEmpty(username)) {
            showToast("账号未填写");
            return;
        }

        if (!TextUtils.isEmpty(password)) {
            showToast("密码未填写");
            return;
        }

        WdApp.getRetrofit().takeLogin(username, password).enqueue(this);
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        String data = response.body();
        if (response.isSuccessful()) {
            resolveData(data);
        }
    }


    @Override
    public void onFailure(Call<String> call, Throwable t) {
        showToast("网络异常，获取数据失败");
    }

    private void resolveData(String data) {

    }
}
