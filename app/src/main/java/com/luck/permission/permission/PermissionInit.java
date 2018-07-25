package com.luck.permission.permission;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.Stack;

public final class PermissionInit {
    private Stack<Activity> store = new Stack<>();

    private PermissionInit() {
    }

    public static PermissionInit getInstance() {
        return Holder.INSTANCE;
    }

    public void init(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                store.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                store.remove(activity);
            }
        });
    }

    public Activity getTopActivity() {
        return store.lastElement();
    }

    private static class Holder {
        private static final PermissionInit INSTANCE = new PermissionInit();
    }
}
