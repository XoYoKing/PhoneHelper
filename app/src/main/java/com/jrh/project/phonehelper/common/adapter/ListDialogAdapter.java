package com.jrh.project.phonehelper.common.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jrh.project.phonehelper.BaseListAdapter;
import com.jrh.project.phonehelper.R;
import com.jrh.project.phonehelper.common.TextConfig;
import com.jrh.project.phonehelper.phonebook.models.PhoneBookOpration;

import butterknife.Bind;

/**
 * Description:
 * Created by JiaRH on 2015/12/30.10:53
 */
public class ListDialogAdapter extends BaseListAdapter<PhoneBookOpration> {

    public ListDialogAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected ViewHolder createViewHolder(View root) {
        return new Myhodler(root);
    }

    @Override
    protected void fillView(View root, PhoneBookOpration item, ViewHolder holder, int position) {
        if (item == null) return;
        Myhodler h = (Myhodler) holder;
        h.tvItem.setText(item.getOpName());
        h.tvItem.setTextSize(TextConfig.getInstance(ctx).getTextSize());
    }

    @Override
    protected int getItemViewId() {
        return R.layout.layout_textview;
    }

    class Myhodler extends ViewHolder {

        public Myhodler(View view) {
            super(view);
        }

        @Bind(R.id.tv_tv)
        TextView tvItem;
    }
}
