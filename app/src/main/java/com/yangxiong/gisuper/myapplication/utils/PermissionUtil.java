package com.yangxiong.gisuper.myapplication.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * function:简单Permission工具方法，具体获取权限可使用第三方库
 *
 * <p>
 * Created by Leo on 2018/10/16.
 */
public final class PermissionUtil {

    @NonNull
    public static String[] hasNotPermissions(Context context, String[] perms) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissions = new ArrayList<>();
            for(String perm : perms) {
                if(PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(context, perm)) {
                    permissions.add(perm);
                }
            }
            return permissions.toArray(new String[0]);
        } else {
            return new String[0];
        }
    }
}
