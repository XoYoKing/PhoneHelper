package com.jrh.project.phonehelper.phonebook.presenter;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jrh.project.phonehelper.BasePresenter;
import com.jrh.project.phonehelper.common.TextConfig;
import com.jrh.project.phonehelper.common.listener.IListOnItemClickListener;
import com.jrh.project.phonehelper.common.widgets.CommonDialog;
import com.jrh.project.phonehelper.home.utils.ViewUtils;
import com.jrh.project.phonehelper.phonebook.activity.SendMsgActivity;
import com.jrh.project.phonehelper.phonebook.adapter.PhoneBookLvAdapter;
import com.jrh.project.phonehelper.phonebook.config.PhoneBookConfig;
import com.jrh.project.phonehelper.phonebook.models.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:获取电话本信息
 * Created by JiaRH on 2015/12/28.17:06
 */
public class PhoneBookPresenter extends BasePresenter implements AdapterView.OnItemClickListener {


    List<UserInfo> userDatas;
    private ListView mListView;
    private PhoneBookLvAdapter mAdapter;
    private CommonDialog dialog;

    public PhoneBookPresenter(Context mContext) {
        super(mContext);
    }

    public PhoneBookPresenter(Context mContext, ListView mListView) {
        super(mContext);
        userDatas = new ArrayList<>();
        this.mListView = mListView;
        initData();
    }

    private void initData() {
        mAdapter = new PhoneBookLvAdapter(mContext);
        mAdapter.setList(userDatas);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);

    }

    public void adjustSize(int size){
        TextConfig.getInstance(mContext).setTextSize(size);
        mAdapter.clear();
        mAdapter = new PhoneBookLvAdapter(mContext);
        getAllUsers();
        mAdapter.setList(userDatas);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mAdapter.notifyDataSetChanged();
    }
    //读取通讯录的全部的联系人
    //需要先在raw_contact表中遍历id，并根据id到data表中获取数据
    public    List<UserInfo>  getAllUsers() {
        userDatas.clear();
        //uri = content://com.android.contacts/contacts
        Uri uri = Uri.parse("content://com.android.contacts/contacts"); //访问raw_contacts表
        ContentResolver resolver = mContext.getContentResolver();
        //获得_id属性
        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Contacts.Data._ID}, null, null, null);
        while (cursor.moveToNext()) {
            StringBuilder buf = new StringBuilder();
            UserInfo info = new UserInfo();
            //获得id并且在data中寻找数据
            int id = cursor.getInt(0);
            buf.append("id=" + id);
            info.setId(id + "");
            uri = Uri.parse("content://com.android.contacts/contacts/" + id + "/data");
            //data1存储各个记录的总数据，mimetype存放记录的类型，如电话、email等
            Cursor cursor2 = resolver.query(uri, new String[]{ContactsContract.Contacts.Data.DATA1, ContactsContract.Contacts.Data.MIMETYPE}, null, null, null);
            while (cursor2.moveToNext()) {
                String data = cursor2.getString(cursor2.getColumnIndex("data1"));
                if (cursor2.getString(cursor2.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/name")) {       //如果是名字
                    buf.append(",name=" + data);
                    info.setName(data);
                } else if (cursor2.getString(cursor2.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/phone_v2")) {  //如果是电话
                    buf.append(",phone=" + data);
                    info.setTelNum(data);
                } else if (cursor2.getString(cursor2.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/email_v2")) {  //如果是email
                    buf.append(",email=" + data);
                } else if (cursor2.getString(cursor2.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/postal-address_v2")) { //如果是地址
                    buf.append(",address=" + data);
                    info.setAddress(data);
                } else if (cursor2.getString(cursor2.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/organization")) {  //如果是组织
                    buf.append(",organization=" + data);
                }
            }
            userDatas.add(info);

            mAdapter.notifyDataSetChanged();

            String str = buf.toString();
//            Log.i("Contacts", str);
        }
            return  userDatas;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showDialog(view, position);
    }

    private void showDialog(View view, final int oposition) {
        if (dialog == null) {
            dialog = new CommonDialog(mContext, 0);
            dialog.setTvTitle("想要干啥呢？");

            dialog.setOprations(PhoneBookConfig.getOprarions());
            dialog.setOnItemClickListener(new IListOnItemClickListener() {
                @Override
                public void onItemClicked(AdapterView<?> parent, View view, int position, long id) {
                    switch (PhoneBookConfig.getOprarions().get(position)) {
                        case CALL:
                            doCall(oposition);
                            break;
//                        case SEND_MSG:
//                            doSendMsg(oposition);
//                            break;
                        case DELETE:
                            doDelete(oposition);
                            break;
                        case CANCLE:
                            doCancle();
                            break;
//                        case UPDATE_USER:
//                            doUpdate(oposition);
//                            break;
                        default:
                            break;
                    }
                    closeDialog();
                }



            });

        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    private void doCancle() {
        closeDialog();
    }

    /**
     * 修改联系人
     * @param oposition
     */
    private void doUpdate(int oposition) {
    }

    /**
     * 删除
     * @param oposition
     */
    private void doDelete(int oposition) {
        deleteContact(Long.parseLong(userDatas.get(oposition).getId()));
        userDatas.remove(oposition);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 打电话
     * @param position
     */
    private void doCall(int position) {
        Intent phoneIntent = new Intent(
                "android.intent.action.CALL", Uri.parse("tel:"
                + userDatas.get(position).getTelNum()));
        mContext.startActivity(phoneIntent);
    }
    /**
     * 发短信
     */
    private void doSendMsg(int position) {
        Bundle b = new Bundle();
        b.putParcelable(UserInfo.USER_INFO,userDatas.get(position));
        ViewUtils.startActivity(mContext, SendMsgActivity.class,b,Intent.FLAG_ACTIVITY_SINGLE_TOP);
    }

    private void closeDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog=null;
        }

    }

    /**
     * 增加联系人
     * @param name
     * @param phoneNum
     */
    public void addContact(String name, String phoneNum) {
        ContentValues values = new ContentValues();
        Uri rawContactUri = mContext.getContentResolver().insert(
                ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);

        // 向data表插入数据
        if (name != "") {
            values.clear();
            values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
            values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
            mContext.getContentResolver().insert(ContactsContract.Data.CONTENT_URI,
                    values);
        }
        // 向data表插入电话号码
        if (phoneNum != "") {
            values.clear();
            values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
            values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNum);
            values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
            mContext.getContentResolver().insert(ContactsContract.Data.CONTENT_URI,
                    values);
        }
    }

    // 删除联系人
    public void deleteContact(long rawContactId) {
        mContext.getContentResolver().delete(
                ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI,
                        rawContactId), null, null);
    }

    // 更新联系人
    public void updataCotact(long rawContactId) {
        ContentValues values = new ContentValues();

        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "13800138000");
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        String where = ContactsContract.Data.RAW_CONTACT_ID + "=? AND "
                + ContactsContract.Data.MIMETYPE + "=?";
        String[] selectionArgs = new String[] { String.valueOf(rawContactId),
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE };
        mContext.getContentResolver().update(ContactsContract.Data.CONTENT_URI, values,
                where, selectionArgs);
    }

}
