package com.microsoft;

import android.app.Application;

import com.microquation.linkedme.android.LinkedME;
import com.microsoft.microsoftclient.BuildConfig;
import com.microsoft.netconfig.ApiServiceFactory;
import com.microsoft.netconfig.DataLayer;
import com.microsoft.netconfig.NetInterface;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 *
 * @author huiliu
 * @date 2018/11/19
 *
 * @email liu594545591@126.com
 * @introduce
 */
public class WdApp extends Application {
    private static WdApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DataLayer.init(this);
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
        // 初始化SDK
        LinkedME.getInstance(this);

        if (BuildConfig.DEBUG) {
            //设置debug模式下打印LinkedME日志
            LinkedME.getInstance().setDebug();
        }
        //初始时请设置为false
        LinkedME.getInstance().setImmediate(false);
//        设置处理跳转逻辑的中转页，MiddleActivity详见后续配置
//        LinkedME.getInstance().setHandleActivity(MiddleActivity.class.getName());
    }

    public static NetInterface getRetrofit() {
        return ApiServiceFactory.getApi();
    }

    public static WdApp getInstance() {
        return instance;
    }


}
