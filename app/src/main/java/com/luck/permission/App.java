package com.luck.permission;

import android.app.Application;

import com.luck.permission.permission.PermissionInit;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PermissionInit.getInstance().init(this);
    }
}
