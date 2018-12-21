package com.microsoft.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.base.BaseActivity;
import com.microsoft.constant.SolidData;
import com.microsoft.microsoftclient.R;
import com.microsoft.util.QRCodeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static dev.utils.app.image.BitmapUtils.drawableToBitmap;

/**
 * @author huiliu
 */
public class QrCodeActivity extends BaseActivity {

    public static final String DOWNLOAD_URL = "http://www.imooc.com/mobile/mukewang.apk";

    @BindView(R.id.iv_qrcode_share)
    ImageView mIvQrcodeShare;
    @BindView(R.id.tv_qr_code_rule)
    TextView mTvQrCodeRule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        ButterKnife.bind(this);
        initTopBar(this, "二维码", true);

        Bitmap qrCodeWithLogo5 = QRCodeUtil.createQRCodeWithLogo5(DOWNLOAD_URL, 500,
                drawableToBitmap(getResources().getDrawable(R.mipmap.ant_icon)));

        mIvQrcodeShare.setImageBitmap(qrCodeWithLogo5);
        Spanned spanned = Html.fromHtml(SolidData.qr_code_rule);
        mTvQrCodeRule.setText(spanned);
    }


}
