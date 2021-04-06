package com.example.mvcdemo.model;

/**
 * @author SpreadWater TODO(用户操作回调接口)
 * @Date 2021-03-22
 * @Time 10:18
 * @description
 */

public interface onUserListener {

    public static final int USERID_IS_EMPTY = 1;
    public static final int USER_IS_INVALID = 2;

    void onBefore();

    void onSuccess(User user);

    void onError(int errorID);
}
