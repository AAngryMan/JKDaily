package com.jk.jkdaily;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pb_vertical_simple_shape)
    ShowNumberVerticalSeekBar mProgressBar;

    private  Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    int progress = (int) msg.obj;

                    break;

            }
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mProgressBar = (ShowNumberVerticalSeekBar) findViewById(R.id.pb_vertical_simple_shape);

    }
     int progress = 0;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                if (progress == 100){
                    progress = 0;
                }
                progress +=1;
                message.obj = progress;
                message.what = 0;
                mHandler.sendMessage(message);

                mHandler.postDelayed(this,100);
            }
        },100);
    }

    @Override
    protected void onResume() {
        super.onResume();



    }



}
