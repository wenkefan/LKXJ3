package com.fwk.lkxj3.android.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ViewPagers extends ViewPager {

	public ViewPagers(Context context) {
		super(context);

	}

	public ViewPagers(Context context, AttributeSet att) {
		// TODO Auto-generated constructor stub
		super(context, att);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		try {
			return super.onInterceptTouchEvent(arg0);
		} catch (Exception e) {
			// ignore it
		}
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		try {
			return super.onTouchEvent(arg0);
		} catch (IllegalArgumentException ex) {
			// ignore it
		}
		return false;
	}

}
