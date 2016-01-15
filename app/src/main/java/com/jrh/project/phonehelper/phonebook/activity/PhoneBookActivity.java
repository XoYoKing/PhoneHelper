package com.jrh.project.phonehelper.phonebook.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFloat;
import com.jrh.project.phonehelper.BaseActivity;
import com.jrh.project.phonehelper.R;
import com.jrh.project.phonehelper.common.TextConfig;
import com.jrh.project.phonehelper.common.event.EventMsg;
import com.jrh.project.phonehelper.home.utils.ViewUtils;
import com.jrh.project.phonehelper.phonebook.models.UserInfo;
import com.jrh.project.phonehelper.phonebook.presenter.PhoneBookPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


/**
 * 电话本
 */
public class PhoneBookActivity extends BaseActivity {

    private static final int REQUEST_CODE = 0;
    @Bind(R.id.listview)
    ListView listview;
    List<UserInfo> userDatas;
    @Bind(R.id.iv_back)
    ImageView ivBack;

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.btn_adjust_jian)
    ButtonFloat btnAdjustJian;
    @Bind(R.id.btn_adjust_add)
    ButtonFloat btnAdjustAdd;
    @Bind(R.id.empty_view)
    TextView emptyView;

    private PhoneBookPresenter phoneBookPresenter;

    int textSize = 20;
    boolean isADD = true;

    @Override
    public int getViewId() {
        return R.layout.activity_phone_book;
    }

    @Override
    public void initView() {

        EventBus.getDefault().register(this);
        tvTitle.setText("联系人");
        tvRight.setText("新建");
        changeSize();
        tvRight.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.GONE);
    }

    private void changeSize() {
        int size = TextConfig.getInstance(this).getTextSize();
        tvTitle.setTextSize(size > 25 ? 25 : size);
        tvRight.setTextSize(size > 25 ? 25 : size);
    }

    @Override
    public void initData() {
        userDatas = new ArrayList<>();
        getUsers();
    }

    private void getUsers() {

        /** 需要权限检查*/
        int checkSelfWritePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS);
        int checkSelfReadPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);

        if (checkSelfWritePermission != PackageManager.PERMISSION_GRANTED || checkSelfReadPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS}, REQUEST_CODE);
        } else {

            notifyRefresh();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //获取数据
                    notifyRefresh();
                } else {
                    Toast.makeText(PhoneBookActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void notifyRefresh() {
        if (phoneBookPresenter == null) {
            phoneBookPresenter = new PhoneBookPresenter(this, listview);
        }
        if (phoneBookPresenter.getAllUsers().size() <= 0) {
            emptyView.setVisibility(View.VISIBLE);
            listview.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
        }

    }

    @OnClick(R.id.iv_back)
    public void goBack() {
        finish();
    }

    @OnClick(R.id.tv_right)
    public void addContact() {

        ViewUtils.startActivity(this, AddUserActivity.class);
    }

    public void onEventMainThread(EventMsg eventMsg) {

        if (eventMsg != null) {
            if (eventMsg.getType() == EventMsg.ADD_USER) {
                notifyRefresh();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.btn_adjust_jian, R.id.btn_adjust_add})
    public void onAdjustClicked() {

        if (isADD) {
            textSize += 2;

        } else {
            textSize -= 2;
        }
        if (textSize > 30) {
            textSize = 30;
            isADD = false;
            btnAdjustJian.setVisibility(View.VISIBLE);
            btnAdjustAdd.setVisibility(View.GONE);
        } else if (textSize < 16) {
            isADD = true;
            textSize = 16;
            btnAdjustAdd.setVisibility(View.VISIBLE);
            btnAdjustJian.setVisibility(View.GONE);
        }

        changeSize();
        phoneBookPresenter.adjustSize(textSize);

    }


}
