package com.microsoft.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.microsoftclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author huiliu
 * 抖音任务
 */
public class DyTaskActivity extends AppCompatActivity {

    @BindView(R.id.iv_title_arrow)
    ImageView mIvTitleArrow;
    @BindView(R.id.tv_title_name)
    TextView mTvTitleName;
    @BindView(R.id.rv_douyin_list)
    RecyclerView mRvDouyinList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dytask);
        ButterKnife.bind(this);

    }
}
