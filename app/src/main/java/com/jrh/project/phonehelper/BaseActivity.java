package com.jrh.project.phonehelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jrh.project.phonehelper.common.umeng.EventCountAction;
import com.umeng.update.UmengUpdateAgent;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewId());
        ButterKnife.bind(this);
        UmengUpdateAgent.update(this);
        initView();
        initData();
    }


    public abstract int getViewId();

    public abstract void initView();

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventCountAction.onActivityPauseCount(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventCountAction.onActivityResumCount(this);
    }
}
