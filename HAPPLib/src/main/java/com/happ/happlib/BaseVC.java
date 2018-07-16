package com.happ.happlib;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.happ.happlib.mvp.IView;

public  class BaseVC extends AppCompatActivity implements IView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        findViews();
        bindListeners();
        init();
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
    public Context getContent() {
        return this;
    }
}
