package com.jk.jkdaily;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jk.mylibrarytest.MainActivity_Library;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_test_library).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity_Library mainActivity_library = new MainActivity_Library();
                mainActivity_library.showToast(MainActivity.this);
            }
        });
    }
}
