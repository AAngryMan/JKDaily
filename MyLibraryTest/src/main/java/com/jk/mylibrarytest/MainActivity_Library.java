package com.jk.mylibrarytest;

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

    }
    public void showToast(){
        Toast.makeText(this,"from MainActivity Library",Toast.LENGTH_SHORT).show();
    }


}
