package com.luck.permission.permission;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionsFragment extends Fragment {
    private static final int PERMISSIONS_REQUEST_CODE = 42;
    private OnPermissionListener mOnPermissionListener;

    public static PermissionsFragment newInstance() {
        Bundle args = new Bundle();
        PermissionsFragment fragment = new PermissionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @TargetApi(Build.VERSION_CODES.M)
    void requestPermissions(@NonNull String[] permissions) {
        getActivity().requestPermissions(permissions, PERMISSIONS_REQUEST_CODE);
        if (checkPermissions(getActivity(), permissions)) {
            if (mOnPermissionListener != null) {
                mOnPermissionListener.onPermissionGranted();
            }
        } else {
            List<String> deniedPermissions = getDeniedPermissions(getActivity(), permissions);
            if (deniedPermissions.size() > 0) {
                requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]),
                        PERMISSIONS_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != PERMISSIONS_REQUEST_CODE) return;
        if (verifyPermissions(grantResults)) {
            if (mOnPermissionListener != null)
                mOnPermissionListener.onPermissionGranted();
        } else {
            if (mOnPermissionListener != null)
                mOnPermissionListener.onPermissionDenied();
        }
    }

    /**
     * 验证权限是否都已经授权
     */
    private static boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取权限列表中所有需要授权的权限
     *
     * @param context     上下文
     * @param permissions 权限列表
     * @return
     */
    private static List<String> getDeniedPermissions(Context context, String... permissions) {
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permission);
            }
        }
        return deniedPermissions;
    }

    /**
     * 检查所有的权限是否已经被授权
     *
     * @param permissions 权限列表
     * @return
     */
    private static boolean checkPermissions(Context context, String... permissions) {
        if (isOverMarshmallow()) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断当前手机API版本是否 >= 6.0
     */
    private static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public void setOnPermissionListener(OnPermissionListener onPermissionListener) {
        this.mOnPermissionListener = onPermissionListener;
    }

    public interface OnPermissionListener {
        void onPermissionGranted();

        void onPermissionDenied();
    }

}
