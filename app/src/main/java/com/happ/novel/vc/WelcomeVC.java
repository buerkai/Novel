package com.happ.novel.vc;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import com.happ.happlib.BaseVC;
import com.happ.novel.R;


/***
 * 欢迎页面
 */
public class WelcomeVC extends BaseVC implements View.OnClickListener {


    @Override
    protected int getLayout() {
        return R.layout.a_welcome;
    }

    @Override
    protected void bindListeners() {
        findViewById(R.id.test).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.test) {
            Intent intent = new Intent(this, MainVC.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, v, "test").toBundle());
            } else {
                startActivity(intent);
            }

        }
    }
}
