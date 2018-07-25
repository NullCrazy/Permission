package com.luck.permission.permission;

import android.app.Activity;
import android.app.FragmentManager;


public class PermissionUtils {
    private static final String TAG = "Permissions";

    public static void requestPermissionsResult(Activity activity, String[] permission,
                                                PermissionsFragment.OnPermissionListener callback) {
        PermissionsFragment permissionsFragment = getPermissionsFragment(activity);
        permissionsFragment.setOnPermissionListener(callback);
        permissionsFragment.requestPermissions(permission);
    }

    private static PermissionsFragment getPermissionsFragment(Activity activity) {
        PermissionsFragment permissionsFragment = (PermissionsFragment) activity.getFragmentManager().findFragmentByTag(TAG);
        boolean isNewInstance = permissionsFragment == null;
        if (isNewInstance) {
            permissionsFragment = PermissionsFragment.newInstance();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(permissionsFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return permissionsFragment;
    }
}