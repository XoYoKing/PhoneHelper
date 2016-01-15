package com.jrh.project.phonehelper.phonebook.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jrh.project.phonehelper.BaseListAdapter;
import com.jrh.project.phonehelper.R;
import com.jrh.project.phonehelper.common.TextConfig;
import com.jrh.project.phonehelper.phonebook.models.UserInfo;

import butterknife.Bind;

/**
 * Description:
 * Created by JiaRH on 2015/12/28.
 */
public class PhoneBookLvAdapter extends BaseListAdapter<UserInfo> {


    public PhoneBookLvAdapter(Context ctx) {
        super(ctx);
    }


    @Override
    protected ViewHolder createViewHolder(View root) {
        return new MyHodler(root);
    }

    @Override
    protected void fillView(View root, UserInfo item, ViewHolder holder, int position) {

        if (item == null) return;
        MyHodler h = (MyHodler) holder;
        h.tvName.setText(item.getName());
        h.tvNum.setText(item.getTelNum());
        h.tvName.setTextSize(TextConfig.getInstance(ctx).getTextSize());
        h.tvNum.setTextSize(TextConfig.getInstance(ctx).getTextSize());
    }

    @Override
    protected int getItemViewId() {
        return R.layout.item_phone_book_lv;
    }

    class MyHodler extends ViewHolder {

        public MyHodler(View view) {
            super(view);
        }

        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_tel)
        TextView tvNum;

    }


}
