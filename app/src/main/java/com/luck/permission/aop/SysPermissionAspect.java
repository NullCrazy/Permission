package com.luck.permission.aop;


import android.util.Log;

import com.luck.permission.annotation.Permission;
import com.luck.permission.permission.PermissionInit;
import com.luck.permission.permission.PermissionUtils;
import com.luck.permission.permission.PermissionsFragment;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @创建时间:2018/7/20 10:08.
 * @负责人: 雷兴国
 */
@Aspect
public class SysPermissionAspect {

    /*@Around("execution(@com.luck.permission.annotation.Permission * *(..)) && @annotation(Permission)")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, final Permission permission) {
        PermissionUtils.requestPermissionsResult(PermissionInit.getInstance().getTopActivity(),
                permission.value(), new PermissionsFragment.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        try {
                            joinPoint.proceed();//获得权限，执行原方法
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onPermissionDenied() {
                        Log.d("SysPermissionAspect", "拒绝了权限：" + permission.value());
                    }
                });
    }*/

}
