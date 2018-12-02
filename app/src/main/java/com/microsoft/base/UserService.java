package com.microsoft.base;

import com.microsoft.bean.LoginBean;
import com.microsoft.constant.Constant;
import com.microsoft.util.GsonUtil;
import com.microsoft.util.SharePreferenceManager;

/**
 * @author Created by huiliu on 2018/11/27.
 * @email liu594545591@126.com
 * @introduce
 */
public class UserService {

    /**
     * 获取用户信息
     *
     * @return loginBean
     */
    public static LoginBean getUserInfo() {
        String userJson = (String) SharePreferenceManager.getInstance().getInfoFromSp(Constant.SP_USER_INFO, "");
        LoginBean loginBean = GsonUtil.parseJsonWithGson(userJson, LoginBean.class);
        if (loginBean == null) {
            return null;
        }
        return loginBean;
    }
}
