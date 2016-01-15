package com.jrh.project.phonehelper.common.widgets;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jrh.project.phonehelper.BaseDialog;
import com.jrh.project.phonehelper.R;
import com.jrh.project.phonehelper.common.TextConfig;
import com.jrh.project.phonehelper.common.adapter.ListDialogAdapter;
import com.jrh.project.phonehelper.common.listener.IListOnItemClickListener;
import com.jrh.project.phonehelper.phonebook.models.PhoneBookOpration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Description:
 * Created by JiaRH on 2015/12/30.10:41
 */
public class CommonDialog extends BaseDialog {



    ListDialogAdapter mAdapter;
    List<PhoneBookOpration> oprations;
    IListOnItemClickListener onItemClickListener;

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.imagebtn)
    ImageView imagebtn;
    @Bind(R.id.listview)
    ListView mLv;


    public CommonDialog(Context context, int theme) {
        super(context, R.style.MaskDialog);
        oprations = new ArrayList<>();
    }

    @Override
    protected BaseDialog.DTYPE setDialogType() {
        this.type=DTYPE.LIST_DIALOG;
        return DTYPE.LIST_DIALOG;
    }


    @Override
    public void setUpViews(View root) {

        if (type==DTYPE.LIST_DIALOG){
            ivBack.setVisibility(View.GONE);
        }
        mAdapter = new ListDialogAdapter(context);

    }

    @Override
    public void setUpListener() {

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClicked(parent, view, position, id);
                }
            }
        });
    }

    public void setOnItemClickListener(IListOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setTvTitle(String title) {
        if (!TextUtils.isEmpty(title)){
            tvTitle.setText(title);
            tvTitle.setTextSize(TextConfig.getInstance(context).getTextSize());
        }
    }

    @Override
    public boolean getCancelableOnclick() {
        return true;
    }

    @Override
    public int getGravity() {
        return Gravity.CENTER;
    }

    @Override
    public int getRootId() {
        return R.layout.layout_common_dialog;
    }

    @Override
    public int getAnimatStly() {
        return 0;
    }

    @Override
    public ViewGroup.LayoutParams getILayoutParams() {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return lp;
    }

    public void setOprations(List<PhoneBookOpration> oprations) {
        this.oprations.addAll(oprations);
        mAdapter.setList(oprations);
        mLv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
