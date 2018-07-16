package com.happ.novel.vc;

import android.Manifest;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.happ.happlib.BaseVC;
import com.happ.happlib.HHandler;
import com.happ.happlib.utils.HPermissionUtil;
import com.happ.novel.R;

import java.util.ArrayList;
import java.util.List;




/***
 * 欢迎页面
 */
public class WelcomeVC extends BaseVC implements View.OnClickListener, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {


    private TextView note;
    private ValueAnimator animator;


    @Override
    protected int getLayout() {
        return R.layout.a_welcome;
    }

    @Override
    protected void bindListeners() {
        note = (TextView) findViewById(R.id.app_note);
    }

    @Override
    protected void init() {
        animator = ValueAnimator.ofFloat(24, 12, 20).setDuration(2000);
        animator.setStartDelay(500);
        animator.addUpdateListener(this);
        animator.addListener(this);
        animator.start();
    }

    private boolean hasPermissions=false;
    /***
     * 申请权限
     */
    private void requestPermission() {
        List<String> permissionItems = new ArrayList<String>();
        permissionItems.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissionItems.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (HPermissionUtil.checkPermissions(this, permissionItems)) {
            hasPermissions=true;
            if(!animator.isRunning()){
                gotoMain();
            }
        }
    }

    private void gotoMain(){
        Intent intent = new Intent(this, MainVC.class);
        startActivity(intent);
        HHandler.getInstance().sendMessage(false, 1000, 1);
    }
    @Override
    public void onClick(View v) {
    }

    @Override
    public void onAnimationStart(Animator animation) {
        requestPermission();
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if(hasPermissions){
            gotoMain();
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float fontSize = (Float) animation.getAnimatedValue();
        note.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
    }

    @Override
    public void handleMessage(boolean mainThread, Message msg) {
        if (msg.what == 1) {
            finish();
        }
    }

    private int permissionCheckCount = 0;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        this.permissionCheckCount++;
        if (requestCode == 1) {
            if (this.permissionCheckCount > 10) {
//                DialogUtil.getInstance(this).showConfirmDialog("获取权限失败",
//                        "应用正常运行权限申请失败,应用不可用,请到权限设置处修改");
            } else {
                requestPermission();
            }
        }
    }
}
