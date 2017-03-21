package cn.com.qytx.workflow.service.impl;

import cn.com.qytx.platform.org.domain.UserInfo;

/**
     * Created by izerui.com on 14-5-6.
     */
public abstract class Authentication {

    static ThreadLocal<UserInfo> authenticatedUserIdThreadLocal = new ThreadLocal<UserInfo>();

    public static void setAuthor(UserInfo userInfo) {
        authenticatedUserIdThreadLocal.set(userInfo);
    }

    public static UserInfo getAuthor() {
        return authenticatedUserIdThreadLocal.get();
    }

}