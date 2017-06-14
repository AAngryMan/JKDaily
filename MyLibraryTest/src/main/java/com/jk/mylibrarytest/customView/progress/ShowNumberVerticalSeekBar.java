package com.jk.mylibrarytest.customView.progress;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.jk.mylibrarytest.R;

/**
 * Created by Administrator on 2017/6/14.
 */

public class ShowNumberVerticalSeekBar extends RelativeLayout {
    public ShowNumberVerticalSeekBar(Context context) {
        super(context);
    }

    public ShowNumberVerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.seekbar_vertical,null);
    }

    public ShowNumberVerticalSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
