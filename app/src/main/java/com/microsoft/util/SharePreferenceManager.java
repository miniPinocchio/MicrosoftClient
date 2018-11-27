package com.microsoft.util;


import com.microsoft.WdApp;

/**
 * @author lh
 * @desc 登陆信息管理sp调用类
 */
public class SharePreferenceManager {
    static SharePreferenceManager spInfoManager;

    public static SharePreferenceManager getInstance() {
        if (spInfoManager == null) {
            synchronized (SharePreferenceManager.class) {
                if (spInfoManager == null) {
                    spInfoManager = new SharePreferenceManager();
                }
            }
        }
        return spInfoManager;
    }

    private SharePreferenceManager() {

    }

    /**
     * @author lh
     * @date 2017/11/10
     * @desc 保存相关的信息到sp中
     */
    public void saveInfoToSp(String key, Object value) {
        SpUtils.putValueToSp(WdApp.getInstance(), key, value);
    }

    /**
     * @author lh
     * @date 2017/11/10
     * @desc 从sp中相关的信息
     */
    public Object getInfoFromSp(String key, Object defaultValue) {
        return SpUtils.getValueFromSp(WdApp.getInstance(), key, defaultValue);
    }

    /**
     * @author lh
     * @date 2017/12/8
     * @desc 清除指定的key值
     */
    public void clearInfoFromSp(String key) {
        SpUtils.removeKey(WdApp.getInstance(), key);
    }

    /**
     * @author lh
     * @date 2017/12/8
     * @desc 清除全部的sp值
     */
    public void clearAllInfoFromSp() {
        SpUtils.removeAll(WdApp.getInstance());
    }

}
