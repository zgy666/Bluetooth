package com.zgy.translate.activitys;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zgy.translate.R;
import com.zgy.translate.adapters.GuideAdapter;
import com.zgy.translate.adapters.interfaces.GuideAdapterInterface;
import com.zgy.translate.base.BaseActivity;
import com.zgy.translate.managers.UserMessageManager;
import com.zgy.translate.utils.RedirectUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends BaseActivity implements GuideAdapterInterface{

    @BindView(R.id.ag_rv) RecyclerView rv;
    private List<Integer> guideList;
    private GuideAdapter guideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        super.init();
    }

    @Override
    public void initView() {
        guideList = new ArrayList<>();
        guideList.add(R.mipmap.guide_1);
        guideList.add(R.mipmap.guide_2);
        guideList.add(R.mipmap.guide_3);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(llm);
        guideAdapter = new GuideAdapter(this, guideList, this);
        rv.setAdapter(guideAdapter);

    }

    @Override
    public void initEvent() {

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
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void endIndex() {
        UserMessageManager.saveLoginUser(this, "1");
        RedirectUtil.redirect(this, LoginActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(guideList != null){
            guideList.clear();
            guideList = null;
        }
        rv.setAdapter(null);
        rv.setLayoutManager(null);
        guideAdapter = null;

    }
}
