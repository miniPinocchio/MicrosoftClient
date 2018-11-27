package com.microsoft.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.WdApp;
import com.microsoft.microsoftclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author huiliu
 * 抖音任务
 */
public class DyTaskActivity extends AppCompatActivity implements Callback<String> {

    @BindView(R.id.iv_title_arrow)
    ImageView mIvTitleArrow;
    @BindView(R.id.tv_title_name)
    TextView mTvTitleName;
    @BindView(R.id.rv_douyin_list)
    RecyclerView mRvDouyinList;
    private String id; //用户id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dytask);
        ButterKnife.bind(this);

    }

    private void getDyList(String userId) {
        WdApp.getRetrofit().takeDyTask(userId).enqueue(this);
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }
}
