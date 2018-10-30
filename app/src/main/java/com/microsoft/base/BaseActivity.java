package com.microsoft.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.microsoftclient.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void initTopBar(final Activity activity,String title_name){
        ImageView iv_title_arrow = (ImageView)activity.findViewById(R.id.iv_title_arrow);
        TextView tv_title_name=(TextView) activity.findViewById(R.id.tv_title_name);
        iv_title_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title_name.setText(title_name);
    }
}
