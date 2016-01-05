package com.jrh.project.phonehelper.home.activity;

import android.widget.TextView;

import com.jrh.project.phonehelper.BaseActivity;
import com.jrh.project.phonehelper.R;
import com.jrh.project.phonehelper.home.utils.ViewUtils;
import com.jrh.project.phonehelper.phonebook.activity.PhoneBookActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Bind(R.id.tv_phone_book)
    TextView tvPhoneBook;

    @Override
    public int getViewId() {
        return R.layout.activity_main_view;
    }

    @Override
    public void initView() {

    }

    @OnClick(R.id.tv_phone_book)
    public  void onPhoneBookClicked(){

        ViewUtils.startActivity(this, PhoneBookActivity.class);
    }
    @Override
    public void initData() {

    }


}
