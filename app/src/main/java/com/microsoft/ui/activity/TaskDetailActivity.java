package com.microsoft.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.microsoft.WdApp;
import com.microsoft.base.BaseActivity;
import com.microsoft.base.UserService;
import com.microsoft.bean.LoginBean;
import com.microsoft.bean.RootBean;
import com.microsoft.bean.TaskDetailBean;
import com.microsoft.bean.TaskDetailMiddleBean;
import com.microsoft.bean.TaskDetailRootBean;
import com.microsoft.constant.Constant;
import com.microsoft.microsoftclient.R;
import com.microsoft.util.GsonUtil;
import com.microsoft.util.MyImageGetter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author huiliu
 */
public class TaskDetailActivity extends BaseActivity implements Callback<String> {

    @BindView(R.id.tv_title_name)
    TextView mTvTitleName;
    @BindView(R.id.tv_detail_create_time)
    TextView mTvDetailCreateTime;
    @BindView(R.id.tv_detail_reward)
    TextView mTvDetailReward;
    @BindView(R.id.tv_detail_do_time)
    TextView mTvDetailDoTime;
    @BindView(R.id.tv_detail_check_time)
    TextView mTvDetailCheckTime;
    @BindView(R.id.tv_content_detail)
    TextView mTvContentDetail;
    @BindView(R.id.et_input_number_douyin)
    EditText mEtInputNumberDouyin;
    @BindView(R.id.tv_select_image1)
    TextView mTvSelectImage1;
    @BindView(R.id.tv_select_image2)
    TextView mTvSelectImage2;
    @BindView(R.id.btn_commit_task)
    Button mBtnCommitTask;
    private LoginBean mUserInfo;
    public static final int REQUEST_CODE = 1;
    private int mImageTag;
    public static final int IMAGE_TAG_SECOND = 2;
    public static final int IMAGE_TAG_ONE = 1;
    private String mSelectImage1;
    private String mSelectImage2;
    private String mUserId;
    private String mTaskId;
    private int mNetType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        ButterKnife.bind(this);
        initTopBar(this, "影音任务详情", true);
        mUserInfo = UserService.getUserInfo();
        mUserId = mUserInfo.getId();
        Intent intent = getIntent();
        if (intent != null) {
            mTaskId = intent.getStringExtra(Constant.TASK_ID);
            getDetailData(mTaskId, mUserId);
        }
    }


    private void getDetailData(String taskId, String userId) {
        mNetType = 1;
        WdApp.getRetrofit().takeTaskDetail(taskId, userId).enqueue(this);
    }

    @OnClick({R.id.tv_select_image1, R.id.tv_select_image2, R.id.btn_commit_task})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_select_image1:
                mImageTag = IMAGE_TAG_ONE;
                selectImage();
                break;
            case R.id.tv_select_image2:
                mImageTag = IMAGE_TAG_SECOND;
                selectImage();
                break;
            case R.id.btn_commit_task:
                uploadImage();
                break;
            default:
                break;
        }
    }


    private void selectImage() {
        //单选
        ImageSelectorUtils.openPhoto(this, REQUEST_CODE, true, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);
            if (mImageTag == IMAGE_TAG_ONE) {
                mSelectImage1 = images.get(0);
                mTvSelectImage1.setText(mSelectImage1);
            } else if (mImageTag == IMAGE_TAG_SECOND) {
                mSelectImage2 = images.get(0);
                mTvSelectImage2.setText(mSelectImage2);
            }
        }

        /**
         * 是否是来自于相机拍照的图片，
         * 只有本次调用相机拍出来的照片，返回时才为true。
         * 当为true时，图片返回的结果有且只有一张图片。
         */
        boolean isCameraImage = data.getBooleanExtra(Constant.IS_CAMERA_IMAGE, false);
    }

    /**
     * 上传图片
     */
    private void uploadImage() {
        String etString = mEtInputNumberDouyin.getText().toString();
        if (TextUtils.isEmpty(etString)) {
            showToast("请填写号码");
            return;
        }
        if (!TextUtils.isEmpty(mSelectImage1) && !TextUtils.isEmpty(mSelectImage2)) {
            compassImage();
        }
    }

    /**
     * 上传
     */
    private void compassImage() {
        mNetType = IMAGE_TAG_SECOND;
        File imageFile1 = new File(mSelectImage1);
        File imageFile2 = new File(mSelectImage2);
        //这里采用的Compressor图片压缩

        File file1 = getFile(imageFile1);
        File file2 = getFile(imageFile2);
        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
        HashMap<String, RequestBody> bodyHashMap = new HashMap<>();
        bodyHashMap.put("image1\";filename=\"" + file1.getName(), requestFile1);
        bodyHashMap.put("image2\";filename=\"" + file2.getName(), requestFile2);

        WdApp.getRetrofit().takeCommiteTask(mUserId, mTaskId, bodyHashMap).enqueue(this);

    }

    private File getFile(File imageFile) {
        File file = null;
        try {
            file = new Compressor(this)
                    .setMaxWidth(720)
                    .setMaxHeight(1080)
                    .setQuality(80)
                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .compressToFile(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        String body = response.body();
        if (response.isSuccessful()) {
            resolveData(body);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        showToast(getString(R.string.network_failure));
    }

    private void resolveData(String body) {
        if (body != null) {
            if (mNetType == IMAGE_TAG_ONE) {
                TaskDetailRootBean detailRootBean = GsonUtil.parseJsonWithGson(body, TaskDetailRootBean.class);
                if (Constant.NET_STATUS.equals(detailRootBean.getCode())) {
                    TaskDetailMiddleBean detailMiddleBean = detailRootBean.getData();
                    TaskDetailBean taskDetailBean = detailMiddleBean.getTaskDetailBean();
                    if (taskDetailBean != null) {
                        MyImageGetter getter = new MyImageGetter(this, mTvContentDetail);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Spanned spanned = Html.fromHtml(taskDetailBean.getContent(), Html.FROM_HTML_MODE_LEGACY, getter, null);
                            mTvContentDetail.setText(spanned);
                        } else {
                            Spanned spanned = Html.fromHtml(taskDetailBean.getContent(), getter, null);
                            mTvContentDetail.setText(spanned);
                        }
                    }
                } else {
                    showToast(detailRootBean.getMsg());
                }

            } else if (mNetType == IMAGE_TAG_SECOND) {
                RootBean rootBean = GsonUtil.parseJsonWithGson(body, RootBean.class);
                if (Constant.NET_STATUS.equals(rootBean.getCode())) {
                    showToast(rootBean.getMsg());
                    finish();
                } else {
                    showToast(rootBean.getMsg());
                }
            }
        }
    }


}
