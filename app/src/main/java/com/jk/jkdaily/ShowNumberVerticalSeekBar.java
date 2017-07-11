package com.jk.jkdaily;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/14.
 */

public class ShowNumberVerticalSeekBar extends RelativeLayout {
    @BindView(R.id.iv_show_number)
    ImageView mIvShowNumber;
    @BindView(R.id.pb_vertical)
    ProgressBar mPbVertical;
    @BindView(R.id.rl_view_container)
    RelativeLayout mRlViewContainer;


    private int containerHeight;
    private RelativeLayout.LayoutParams mShowNumberParams;


    public ShowNumberVerticalSeekBar(Context context) {
        this(context,null);
    }



    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        containerHeight = mRlViewContainer.getHeight();

        mShowNumberParams = (LayoutParams) mIvShowNumber.getLayoutParams();

    }

    public ShowNumberVerticalSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        View inflate = LayoutInflater.from(context).inflate(R.layout.seekbar_vertical, this,true);
        ButterKnife.bind(this,inflate);
        mIvShowNumber = (ImageView) findViewById(R.id.iv_show_number);
        mRlViewContainer = (RelativeLayout) findViewById(R.id.rl_view_container);
        mPbVertical = (ProgressBar) findViewById(R.id.pb_vertical);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int positionY = (int) event.getY();
        if (positionY > containerHeight){
            positionY = containerHeight;
        }
        if (positionY < 0){
            positionY = 0;
        }

        mPbVertical.setProgress((containerHeight-positionY)*100/containerHeight);
        mShowNumberParams.topMargin = positionY;

        mIvShowNumber.setLayoutParams(mShowNumberParams);


        return true;
    }
    int bottom;

    public ShowNumberVerticalSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
