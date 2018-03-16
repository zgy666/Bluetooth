package com.zgy.translate.activitys;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zgy.translate.BuildConfig;
import com.zgy.translate.R;
import com.zgy.translate.base.BaseActivity;
import com.zgy.translate.utils.ConfigUtil;
import com.zgy.translate.utils.RedirectUtil;
import com.zgy.translate.widget.CommonBar;
import com.zgy.translate.widget.CommonBar.CommonBarInterface;
import com.zgy.translate.widget.CommonNav;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity implements CommonBarInterface{

    @BindView(R.id.aa_cb) CommonBar commonBar;
    @BindView(R.id.aa_cn_update) CommonNav cn_build; //当前版本号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        super.init();
    }

    @Override
    public void initView() {
        cn_build.setRightTitle("V" + BuildConfig.VERSION_NAME);
    }

    @Override
    public void initEvent() {
        commonBar.setBarInterface(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void blueOff() {

    }

    @Override
    public void disConnected() {

    }

    @Override
    public void connected() {

    }

    @Override
    public void disNetConnected() {

    }

    @Override
    public void netConnected() {

    }

    @Override
    public void checkLeftIcon() {
        finish();
    }

    @Override
    public void checkRightIcon() {

    }

    @OnClick(R.id.aa_cn_update) void update(){
        ConfigUtil.showToask(this, "没有最新版本");
    }

    @OnClick(R.id.aa_cn_feed) void feed(){
        RedirectUtil.redirect(this, FeedBackActivity.class);
    }

}
