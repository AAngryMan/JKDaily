package com.jk.mylibrarytest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/6/11.
 */

public class MainActivity_Library extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_library);

    }
    public void showToast(Context context){
        Toast.makeText(context,"from MainActivity Library",Toast.LENGTH_SHORT).show();
    }


}
