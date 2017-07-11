package com.jk.jkdaily;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.pb_vertical_simple_shape)
    ShowNumberVerticalSeekBar mProgressBar;
    @BindView(R.id.pdv_path_drawing_view)
    PathDrawingView mPathDrawingView;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Bundle data = msg.getData();
                    int moveX = data.getInt("moveX");
                    int moveY = data.getInt("moveY");
                    mPathDrawingView.drawHeartHate(moveX, moveY);
                    break;

            }

        }
    };
    private Point screenMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        screenMetrics = getScreenMetrics(this);


    }

    @OnClick({R.id.btn_recover,R.id.btn_start_draw})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_recover:
                mPathDrawingView.clear();

            break;

            case R.id.btn_start_draw:
                mPathDrawingView.setStrokeWidth(2);
                createArray();
                break;

        }
    }

    /**
     * 创建一个数组
     */
    List<Integer> listX = new ArrayList<>();
    List<Integer> listY = new ArrayList<>();
     int moveX = 0;
    private void createArray(){
        final Random rand = new Random();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Message message = new Message();
                Bundle bundle = new Bundle();
                if (moveX > screenMetrics.x){
                    moveX = screenMetrics.x/2;
                }else{
                    moveX+=10;
                }
                bundle.putInt("moveX",moveX);
                bundle.putInt("moveY", rand.nextInt(500));
                message.setData(bundle);
                mHandler.sendMessage(message);
                mHandler.postDelayed(this,50);
            }
        },10);

    }

    public Point getScreenMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);

    }
    @Override
    protected void onResume() {
        super.onResume();

    }



}
