package com.example.terminal.util;

import com.example.terminal.base.BaseActivity;
import com.example.terminal.listener.OnPermissionListener;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;

import java.util.List;

public class PermissionsUtils {

    private BaseActivity mActivity ;
    private XXPermissions xxPermissions ;

    private PermissionsUtils(BaseActivity mActivity){
        this.mActivity = mActivity ;
        if(xxPermissions == null)
            xxPermissions = XXPermissions.with(mActivity);
    }

    public static PermissionsUtils getInstance(BaseActivity mActivity){
        return new PermissionsUtils(mActivity);
    }

    /**
     * 获取多个权限
     * @param permissions
     * @param onPermission
     */
    public void getPermissions(String[] permissions , OnPermissionListener onPermission){
        if(XXPermissions.isHasPermission(mActivity , permissions)){
            onPermission.havePermission();
        }else {
            xxPermissions.permission(permissions).request(new OnPermission() {
                @Override
                public void hasPermission(List<String> granted, boolean isAll) {
                    onPermission.hasPermission(granted , isAll);
                }

                @Override
                public void noPermission(List<String> denied, boolean quick) {
                    onPermission.noPermission(denied , quick);
                }
            });
        }
    }


    /**
     * 获取多个权限
     * @param permissions
     * @param onPermission
     */
    public void getPermissions(String permissions , OnPermissionListener onPermission){
        if(XXPermissions.isHasPermission(mActivity , permissions)){
            onPermission.havePermission();
        }else {
            xxPermissions.permission(permissions).request(new OnPermission() {
                @Override
                public void hasPermission(List<String> granted, boolean isAll) {
                    onPermission.hasPermission(granted , isAll);
                }

                @Override
                public void noPermission(List<String> denied, boolean quick) {
                    onPermission.noPermission(denied , quick);
                }
            });
        }
    }
}
