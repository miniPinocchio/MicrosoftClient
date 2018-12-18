package com.microsoft.base;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.WdApp;
import com.microsoft.microsoftclient.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * @author huiliu
 */
public class BaseFragment extends Fragment {


    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false);
    }


    /**
     *
     * @param view
     * @param titleName  标题
     * @param show  yes显示返回
     */
    public void initTopBar(final View view, String titleName,boolean show){
        ImageView ivTitleArrow = (ImageView)view.findViewById(R.id.iv_title_arrow);
        if (!show){
            ivTitleArrow.setVisibility(View.GONE);
        }
        TextView tvTitleName=(TextView) view.findViewById(R.id.tv_title_name);
        ivTitleArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).finish();
            }
        });
        tvTitleName.setText(titleName);
    }

    public void startAct(Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
    }

    public void showToast(String text) {
        Toast.makeText(WdApp.getInstance().getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
