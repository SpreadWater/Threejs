package com.example.mvcdemo.model;

/**
 * @author SpreadWater  TODO(关于用户数据操作接口)
 * @Date 2021-03-22
 * @Time 10:17
 * @description
 */

public interface UserModel {

    /**
     * @Description TODO(获取用户数据)
     * @author SpreadWater
     * @parame userId 用户索引
     * @parame listener 回调接口
     */
    void getUserMsg(String userId , onUserListener listener);
}

