package com.jrh.project.phonehelper.phonebook.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.widgets.Dialog;
import com.jrh.project.phonehelper.BaseActivity;
import com.jrh.project.phonehelper.R;
import com.jrh.project.phonehelper.common.widgets.ShowToast;
import com.jrh.project.phonehelper.phonebook.models.UserInfo;

import butterknife.Bind;
import butterknife.OnClick;

public class SendMsgActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.imagebtn)
    ImageView imagebtn;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.tv_user_num)
    TextView tvUserNum;
    @Bind(R.id.et_msgcontent)
    EditText etMsgcontent;
    @Bind(R.id.btn_cancle)
    ButtonRectangle btnCancle;
    @Bind(R.id.btn_send)
    ButtonRectangle btnSend;
    @Bind(R.id.bottom_lay)
    LinearLayout bottomLay;

    private UserInfo userInfo;
    private Dialog mDialog;

    @Override
    public int getViewId() {
        return R.layout.activity_send_msg;
    }

    @Override
    public void initView() {
        tvTitle.setText("发短信");

        getIntenData();
        if (userInfo != null) {
            tvUserName.setText(userInfo.getName());
            tvUserNum.setText(userInfo.getTelNum());
        }else{
           ShowToast.showLongToast(this,"改功能无法使用，请使用系统功能发短信");
           finish();
        }
    }


    @Override
    public void initData() {


    }

    private void getIntenData() {
        userInfo = getIntent().getParcelableExtra(UserInfo.USER_INFO);

    }

    @OnClick({R.id.iv_back, R.id.btn_cancle})
    public void goBack() {
        finish();
    }

    @OnClick(R.id.btn_send)
    public void sendMsg() {

        String msg = etMsgcontent.getText().toString().trim();
        if (!TextUtils.isEmpty(msg)){
            sendMsg(userInfo.getTelNum(),msg);
        }else {
            ShowToast.showShortToast(this,"请输入短信内容");
        }

    }

    private void sendMsg(String number, String message) {
        String SENT = "sms_sent";
        String DELIVERED = "sms_delivered";

        PendingIntent sentPI = PendingIntent.getActivity(this, 0, new Intent(SENT), 0);
        PendingIntent deliveredPI = PendingIntent.getActivity(this, 0, new Intent(DELIVERED), 0);

        registerReceiver(new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Log.i("====>", "Activity.RESULT_OK");
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Log.i("====>", "RESULT_ERROR_GENERIC_FAILURE");
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Log.i("====>", "RESULT_ERROR_NO_SERVICE");
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Log.i("====>", "RESULT_ERROR_NULL_PDU");
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Log.i("====>", "RESULT_ERROR_RADIO_OFF");
                        break;
                }
            }
        }, new IntentFilter(SENT));

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Log.i("====>", "RESULT_OK");
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.i("=====>", "RESULT_CANCELED");
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager smsm = SmsManager.getDefault();
        smsm.sendTextMessage(number, null, message, sentPI, deliveredPI);
    }

    /**
     * 调起系统发短信功能
     * @param phoneNumber 发送短信的接收号码
     * @param message     短信内容
     */
    public void SendSMS(String phoneNumber,String message){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
        intent.putExtra("sms_body", message);
        startActivity(intent);
    }
}
