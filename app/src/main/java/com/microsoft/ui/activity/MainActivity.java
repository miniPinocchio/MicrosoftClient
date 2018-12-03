package com.microsoft.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.microsoft.base.BaseActivity;
import com.microsoft.bean.HostTab;
import com.microsoft.microsoftclient.R;
import com.microsoft.ui.fragment.MineFragment;
import com.microsoft.ui.fragment.TaskFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页
 *
 * @author huiliu
 */
public class MainActivity extends BaseActivity {


    @BindView(android.R.id.tabcontent)
    FrameLayout mTabcontent;
    @BindView(R.id.fl_main_content)
    FrameLayout mFlMainContent;
    @BindView(R.id.main_tabhost)
    FragmentTabHost mMainTabhost;

    private int[] mTitles = {R.string.earning, R.string.mine};
    private int[] mImages = {
            R.drawable.selector_task_img,
            R.drawable.selector_mine_img
    };
    private List<HostTab> mTabs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        LayoutInflater mInflater = LayoutInflater.from(this);

        mMainTabhost.setup(this, getSupportFragmentManager(), R.id.fl_main_content);
        mMainTabhost.getTabWidget().setDividerDrawable(null);

        HostTab tabNome = new HostTab(mTitles[0], R.drawable.selector_task_img, TaskFragment.class);
        HostTab tabAddress = new HostTab(mTitles[1], R.drawable.selector_mine_img, MineFragment.class);

        mTabs.add(tabNome);
        mTabs.add(tabAddress);

        for (int i = 0; i < mTabs.size(); i++) {
            View view = mInflater.inflate(R.layout.tab_item, null);

            ImageView image = (ImageView) view.findViewById(R.id.image);
            TextView title = (TextView) view.findViewById(R.id.title);

            image.setImageResource(mImages[i]);
            title.setText(mTitles[i]);

            mMainTabhost.addTab(mMainTabhost.newTabSpec(getString(mTabs.get(i).getTitle())).setIndicator(view),
                    mTabs.get(i).getFragment(), null);
        }

        mMainTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mMainTabhost.setCurrentTab(0);

    }

}
