package com.jrh.project.phonehelper.phonebook.activity;

import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.jrh.project.phonehelper.BaseActivity;
import com.jrh.project.phonehelper.R;
import com.jrh.project.phonehelper.common.event.EventMsg;
import com.jrh.project.phonehelper.common.widgets.ShowToast;

import butterknife.Bind;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class AddUserActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.et_new_name)
    EditText etNewName;
    @Bind(R.id.tv_user_num)
    TextView tvUserNum;
    @Bind(R.id.et_new_num)
    EditText etNewNum;
    @Bind(R.id.btn_sure)
    ButtonRectangle btnSure;
    Uri nameUri,numUri;
    /**
     * 插入失败重试次数
     */
    private int TRY_NUM=3;

    @Override
    public int getViewId() {
        return R.layout.activity_add_user;
    }

    @Override
    public void initView() {

        tvTitle.setText("添加联系人");

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.iv_back)
    public void goBack(){
        finish();
    }
    @OnClick(R.id.btn_sure)
    public void onBtnSureClicked(){

        String userName = etNewName.getText().toString().trim();
        String phoneNum = etNewNum.getText().toString().trim();
        if (TextUtils.isEmpty(userName)){
            ShowToast.showLongToast(this,"姓名不能为空");
            return;
        }else if(TextUtils.isEmpty(phoneNum)){
            ShowToast.showLongToast(this,"电话号码不能为空");
            return;
        }else{

            addContact(userName,phoneNum);
        }


    }


    /**
     * 增加联系人
     * @param name
     * @param phoneNum
     */
    public void addContact(String name, String phoneNum) {
        ContentValues values = new ContentValues();
        Uri rawContactUri = getContentResolver().insert(
                ContactsContract.RawContacts.CONTENT_URI, values);

        long rawContactId = ContentUris.parseId(rawContactUri);

        // 向data表插入数据
        if (name != "") {
            values.clear();
            values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
            values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
          nameUri= getContentResolver().insert(ContactsContract.Data.CONTENT_URI,
                    values);
        }
        // 向data表插入电话号码
        if (phoneNum != "") {
            values.clear();
            values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
            values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNum);
            values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
          numUri= getContentResolver().insert(ContactsContract.Data.CONTENT_URI,
                    values);
        }

        if (null!=nameUri&&null!=numUri){
            EventMsg eventMsg = new EventMsg();
            eventMsg.setType(EventMsg.ADD_USER);
            EventBus.getDefault().post(eventMsg);
            ShowToast.showLongToast(this,"添加成功");
            finish();
        }else{
            if (TRY_NUM>0){
                addContact(name,phoneNum);
                TRY_NUM--;
            }
        }

    }
}
