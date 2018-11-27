package com.microsoft;

import android.app.Application;

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
    }

    public static NetInterface getRetrofit() {
        return ApiServiceFactory.getApi();
    }

    public static WdApp getInstance() {
        return instance;
    }


}
