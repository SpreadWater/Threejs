package com.example.mvcdemo.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mvcdemo.R;
import com.example.mvcdemo.model.User;
import com.example.mvcdemo.model.UserModel;
import com.example.mvcdemo.model.UserModelImpl;
import com.example.mvcdemo.model.onUserListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, onUserListener {

    private String TAG = MainActivity.class.getSimpleName();

    private final int REQUEST_SUCCESS = 1;
    private final int REQUEST_ERROR = 2;

    private String userID = "12";

    private ProgressDialog dialog;
    private Button btn_getuser;
    private TextView tv_msg;

    private UserModel userModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        finView();
        initListener();
    }

    private void finView() {
        btn_getuser = (Button) findViewById(R.id.btn_getuser);
        tv_msg = (TextView) findViewById(R.id.tv_msg);
    }

    private void initListener() {
        btn_getuser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (userModel == null) {
            userModel = new UserModelImpl();
        }
        userModel.getUserMsg(userID,this);
    }

    @Override
    public void onBefore() {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
        }
        dialog.show();
    }

    /**
     * 这是是在子线程回调，不能直接更新View
     * @param user
     */
    @Override
    public void onSuccess(User user) {
        Message message = handler.obtainMessage();
        message.obj = user;
        message.what = REQUEST_SUCCESS;
        handler.sendMessage(message);
    }

    /**
     * 这是是在子线程回调，不能直接更新View
     * @param errorID
     */
    @Override
    public void onError(int errorID) {
        handler.sendEmptyMessage(REQUEST_ERROR);
    }

    Handler handler = new Handler(){
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case REQUEST_SUCCESS:
                    User user = (User) msg.obj;
                    tv_msg.append("姓名："+user.getUserName()+"\n");
                    tv_msg.append("年龄："+user.getAge()+"\n");
                    tv_msg.append("职业："+user.getJob());
                    dialog.dismiss();
                    break;
                case REQUEST_ERROR:
                    tv_msg.setText("");
                    dialog.dismiss();
                    break;
            }
        }
    };

}
