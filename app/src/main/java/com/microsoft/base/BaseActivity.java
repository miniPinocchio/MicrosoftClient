package com.microsoft.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.microsoftclient.R;

/**
 * @author huiliu
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    /**
     *
     * @param activity
     * @param titleName 标题
     * @param show  true 显示返回箭头
     */
    public void initTopBar(final Activity activity, String titleName, boolean show) {
        ImageView ivTitleArrow = (ImageView) activity.findViewById(R.id.iv_title_arrow);
        if (!show){
            ivTitleArrow.setVisibility(View.GONE);
        }
        TextView tvTitleName = (TextView) activity.findViewById(R.id.tv_title_name);
        ivTitleArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitleName.setText(titleName);
    }

    public void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void startAct(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
