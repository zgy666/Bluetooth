package com.zgy.translate.activitys;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zgy.translate.R;
import com.zgy.translate.adapters.QuestionAdapter;
import com.zgy.translate.base.BaseActivity;
import com.zgy.translate.widget.CommonBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionActivity extends BaseActivity implements CommonBar.CommonBarInterface{

    @BindView(R.id.aq_rv_questionList)
    RecyclerView rv_question;
    @BindView(R.id.aq_cb)
    CommonBar cb_ComBar;

    private QuestionAdapter questionAdapter;
    private List<Integer> helpList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);
        super.init();
    }

    @Override
    public void initView() {
        cb_ComBar.setBarInterface(this);
        helpList = new ArrayList<>();
        helpList.add(R.mipmap.guide_1);
        helpList.add(R.mipmap.guide_2);
        helpList.add(R.mipmap.guide_3);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_question.setLayoutManager(llm);
        questionAdapter = new QuestionAdapter(this, helpList);
        rv_question.setAdapter(questionAdapter);

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void checkLeftIcon() {
        finish();
    }

    @Override
    public void checkRightIcon() {

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
    protected void onDestroy() {
        super.onDestroy();

        questionAdapter = null;
        rv_question.setLayoutManager(null);
        rv_question.setAdapter(null);
        if(helpList != null){
            helpList.clear();
            helpList = null;
        }
    }
}
