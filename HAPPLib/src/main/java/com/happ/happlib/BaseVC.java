package com.happ.happlib;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.happ.happlib.mvp.IView;

public  class BaseVC extends AppCompatActivity implements  HHandler.HandlerListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HHandler.getInstance().addHandleListener(this);
        setContentView(getLayout());
        findViews();
        bindListeners();
        init();
    }

    @Override
    protected void onDestroy() {
        HHandler.getInstance().removeHandleListener(this);
        super.onDestroy();
    }

    protected  int getLayout(){
        return 0;
    }

    /***
     * 查找View
     */
    protected  void findViews(){

    }

    /***
     * 绑定点击事件
     */
    protected void bindListeners(){

    }


    /***
     *初始化
     */
    protected void init(){

    }



    @Override
    public void handleMessage(boolean mainThread, Message msg) {

    }
}
