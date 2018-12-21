package com.microsoft.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.microsoft.base.BaseActivity;
import com.microsoft.microsoftclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author huiliu
 * 提现
 */
public class CashActivity extends BaseActivity {

    @BindView(R.id.sp_cash_type)
    Spinner mSpCashType;
    @BindView(R.id.et_cash_account)
    EditText mEtCashAccount;
    @BindView(R.id.et_cash_amount)
    EditText mEtCashAmount;
    @BindView(R.id.btn_pull_cash)
    Button mBtnPullCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);
        ButterKnife.bind(this);
        initTopBar(this, "提现", true);
        initView();
        initEvent();
    }

    private void initEvent() {
        mSpCashType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView() {
        mSpCashType.setDropDownWidth(400); //下拉宽度
        mSpCashType.setDropDownHorizontalOffset(100); //下拉的横向偏移
        mSpCashType.setDropDownVerticalOffset(100); //下拉的纵向偏移
        //mSpCashType.setBackgroundColor(AppUtil.getColor(instance,R.color.wx_bg_gray)); //下拉的背景色
        //spinner mode ： dropdown or dialog , just edit in layout xml
        //mSpCashType.setPrompt("Spinner Title"); //弹出框标题，在dialog下有效


        String[] spinnerItems = {"微信", "支付宝"};
        //自定义选择填充后的字体样式
        //只能是textview样式，否则报错：ArrayAdapter requires the resource ID to be a TextView
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                R.layout.item_select, spinnerItems);
        //自定义下拉的字体样式
        spinnerAdapter.setDropDownViewResource(R.layout.item_drop);
        //这个在不同的Theme下，显示的效果是不同的
        //spinnerAdapter.setDropDownViewTheme(Theme.LIGHT);
        mSpCashType.setAdapter(spinnerAdapter);
        mSpCashType.setSelection(0);
    }

    /**
     * Spinner自定义样式
     * 1、Spinner内的TextView样式：item_select
     * 2、Spinner下拉中每个item的TextView样式：item_drop
     * 3、Spinner下拉框样式，属性设置
     */
    public void ChangeSpinner(View v) {


    }


    @OnClick(R.id.btn_pull_cash)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pull_cash:
                break;
            default:
                break;
        }
    }


}
