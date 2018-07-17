package com.happ.novel.vc;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.happ.happlib.BaseFragment;
import com.happ.happlib.BaseVC;
import com.happ.novel.R;
import com.happ.novel.vc.fragments.FaXianFragment;
import com.happ.novel.vc.fragments.ShuChengFragment;
import com.happ.novel.vc.fragments.ShuJiaFragment;
import com.happ.novel.vc.fragments.WoDeFragment;

import java.util.List;

public class MainVC extends BaseVC implements View.OnClickListener {

    private FrameLayout mainContainer;

    private ImageView main_shujia_pic;
    private TextView main_shujia_txt;

    private ImageView main_shucheng_pic;
    private TextView main_shucheng_txt;

    private ImageView main_faxian_pic;
    private TextView main_faxian_txt;


    private ImageView main_wode_pic;
    private TextView main_wode_txt;

    @Override
    protected int getLayout() {
        return R.layout.a_main;
    }

    @Override
    protected void findViews() {
        mainContainer=(FrameLayout)findViewById(R.id.main_container);

        main_shujia_pic=(ImageView)findViewById(R.id.main_shujia_pic);
        main_shujia_txt=(TextView)findViewById(R.id.main_shujia_txt);
        main_shucheng_pic=(ImageView)findViewById(R.id.main_shucheng_pic);
        main_shucheng_txt=(TextView)findViewById(R.id.main_shucheng_txt);
        main_faxian_pic=(ImageView)findViewById(R.id.main_faxian_pic);
        main_faxian_txt=(TextView)findViewById(R.id.main_faxian_txt);
        main_wode_pic=(ImageView)findViewById(R.id.main_wode_pic);
        main_wode_txt=(TextView)findViewById(R.id.main_wode_txt);
    }

    @Override
    protected void bindListeners() {
        findViewById(R.id. main_shujia).setOnClickListener(this);
        findViewById(R.id. main_shucheng).setOnClickListener(this);
        findViewById(R.id. main_faxian).setOnClickListener(this);
        findViewById(R.id. main_wode).setOnClickListener(this);
    }


    private BaseFragment shujia,shucheng,faxian, wode;

    @Override
    protected void init() {
        shujia=new ShuJiaFragment();
        shucheng=new ShuChengFragment();
        faxian=new FaXianFragment();
        wode=new WoDeFragment();
        addFragement(shujia,shucheng,faxian,wode);
    }



    private void addFragement(BaseFragment shujia, BaseFragment shucheng, BaseFragment faxian, BaseFragment wode) {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_container, shujia, "shujia").show(shujia);
        transaction.add(R.id.main_container, shucheng, "shucheng").hide(shucheng);
        transaction.add(R.id.main_container, faxian, "faxian").hide(faxian);
        transaction.add(R.id.main_container, wode, "wode").hide(wode);
//        transaction.show(shujia);
//        transaction.hide(shucheng);
//        transaction.hide(faxian);
//        transaction.hide(wode);
        transaction.commit();
    }


    /***
     * 显示Fragment
     * @param fragmentName
     */
    private void showFragment(String fragmentName) {
        FragmentManager manager = getSupportFragmentManager();
        List<Fragment> list = manager.getFragments();
        if (list == null || list.size() == 0) {
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        for (Fragment item : list) {
            if (item.getTag().equals(fragmentName)) {
                transaction.show(item);
            } else {
                transaction.hide(item);
            }
        }
        transaction.commitAllowingStateLoss();
    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id. main_shujia:
                showFragment("shujia");
                break;
            case  R.id. main_shucheng:
                showFragment("shucheng");
                break;
            case R.id.main_faxian:
                showFragment("faxian");
                break;
            case R.id. main_wode:
                showFragment("wode");
                break;
        }
    }


    /***
     *
     */
    private void a(){

    }


}
