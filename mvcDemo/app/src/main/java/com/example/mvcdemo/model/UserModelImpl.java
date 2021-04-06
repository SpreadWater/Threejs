package com.example.mvcdemo.model;

import android.text.TextUtils;

import static com.example.mvcdemo.model.onUserListener.USERID_IS_EMPTY;
import static com.example.mvcdemo.model.onUserListener.USER_IS_INVALID;

/**
 * @author SpreadWater
 * @Date 2021-03-22
 * @Time 10:20
 * @description TODO(用户操作具体实现类)
 */

public class UserModelImpl implements UserModel {

    @Override
    public void getUserMsg(String userId, final onUserListener listener) {

        if (TextUtils.isEmpty(userId)) {
            listener.onError(USERID_IS_EMPTY);
            return;
        }

        if (listener == null) return;

        listener.onBefore();
        //这里模拟网络耗时操作,实际开发中绝对不要这样直接new Thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                User user = new User();
                user.setUserId("007");
                user.setUserName("RedRock");
                user.setAge("21");
                user.setSex("1");
                user.setJob("码农");

                if (user == null) {
                    listener.onError(USER_IS_INVALID);
                } else {
                    listener.onSuccess(user);
                }

            }
        }).start();

    }

}

