package com.jrh.project.phonehelper;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.ButterKnife;

public abstract class BaseDialog extends Dialog {

	public Context context;
	public  DTYPE type;
	MaterialDialog dialog;
	public enum DTYPE{
		LIST_DIALOG
	}
	public BaseDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
		initContext(context);
		setDialogType();
		setUpViews(conervtView);
		setUpListener();
	}

	protected abstract DTYPE setDialogType();

	public BaseDialog(Context context) {
		super(context);
		this.context = context;
		initContext(context);
		setUpViews(conervtView);
		setUpListener();
	}

	public View conervtView;

	void initContext(Context context) {
		conervtView = View.inflate(context, getRootId(), null);
		ButterKnife.bind(this,conervtView);
		this.setContentView(conervtView, getILayoutParams());
		this.setCancelable(getCancelableOnclick());
		if (getAnimatStly() != 0) {
			getWindow().setWindowAnimations(getAnimatStly());
		}
		getWindow().setGravity(
				getGravity() == 0 ? Gravity.CENTER : getGravity());
	}

	public abstract void setUpViews(View root);

	public abstract void setUpListener();

	public abstract boolean getCancelableOnclick();

	public abstract int getGravity();

	public abstract int getRootId();

	public abstract int getAnimatStly();

	public abstract LayoutParams getILayoutParams();
}
