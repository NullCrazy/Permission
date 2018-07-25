package com.luck.permission;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.luck.permission.annotation.Permission;

/**
 * @创建时间:2018/7/19 17:04.
 * @负责人: 雷兴国
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRequest();
            }
        });
        findViewById(R.id.btn_group).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* testGroupPermission();*/
            }
        });
    }

    @Permission(Manifest.permission.CAMERA)
    private void testRequest() {
        Log.d("MainActivity", "获得了相机权限");
    }

    /*@Permission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE})
    private void testGroupPermission() {
        Log.d("MainActivity", "获得了组合权限");
    }*/
}
