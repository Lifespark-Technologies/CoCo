package com.example.myapplication.autostartPermissions;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class autostarter {

    /***
     * Xiaomi
     */
    private String BRAND_XIAOMI = "xiaomi";
    private String BRAND_XIAOMI_REDMI = "redmi";
    private String PACKAGE_XIAOMI_MAIN = "com.miui.securitycenter";
    private String PACKAGE_XIAOMI_COMPONENT = "com.miui.permcenter.autostart.AutoStartManagementActivity";

    /***
     * Letv
     */
    private String BRAND_LETV = "letv";
    private String PACKAGE_LETV_MAIN = "com.letv.android.letvsafe";
    private String PACKAGE_LETV_COMPONENT = "com.letv.android.letvsafe.AutobootManageActivity";

    /***
     * ASUS ROG
     */
    private String BRAND_ASUS = "asus";
    private String PACKAGE_ASUS_MAIN = "com.asus.mobilemanager";
    private String PACKAGE_ASUS_COMPONENT = "com.asus.mobilemanager.powersaver.PowerSaverSettings";
    private String PACKAGE_ASUS_COMPONENT_FALLBACK = "com.asus.mobilemanager.autostart.AutoStartActivity";

    /***
     * Honor
     */
    private String BRAND_HONOR = "honor";
    private String PACKAGE_HONOR_MAIN = "com.huawei.systemmanager";
    private String PACKAGE_HONOR_COMPONENT = "com.huawei.systemmanager.optimize.process.ProtectActivity";

    /***
     * Huawei
     */
    private String BRAND_HUAWEI = "huawei";
    private String PACKAGE_HUAWEI_MAIN = "com.huawei.systemmanager";
    private String PACKAGE_HUAWEI_COMPONENT = "com.huawei.systemmanager.optimize.process.ProtectActivity";
    private String PACKAGE_HUAWEI_COMPONENT_FALLBACK = "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity";

    /**
     * Oppo
     */
    private String BRAND_OPPO = "oppo";
    private String PACKAGE_OPPO_MAIN = "com.coloros.safecenter";
    private String PACKAGE_OPPO_FALLBACK = "com.oppo.safe";
    private String PACKAGE_OPPO_COMPONENT = "com.coloros.safecenter.permission.startup.StartupAppListActivity";
    private String PACKAGE_OPPO_COMPONENT_FALLBACK = "com.oppo.safe.permission.startup.StartupAppListActivity";
    private String PACKAGE_OPPO_COMPONENT_FALLBACK_A = "com.coloros.safecenter.startupapp.StartupAppListActivity";

    /**
     * Vivo
     */

    private String BRAND_VIVO = "vivo";
    private String PACKAGE_VIVO_MAIN = "com.iqoo.secure";
    private String PACKAGE_VIVO_FALLBACK = "com.vivo.permissionmanager";
    private String PACKAGE_VIVO_COMPONENT = "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity";
    private String PACKAGE_VIVO_COMPONENT_FALLBACK = "com.vivo.permissionmanager.activity.BgStartUpManagerActivity";
    private String PACKAGE_VIVO_COMPONENT_FALLBACK_A = "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager";

    /**
     * Nokia
     */

    private String BRAND_NOKIA = "nokia";
    private String PACKAGE_NOKIA_MAIN = "com.evenwell.powersaving.g3";
    private String PACKAGE_NOKIA_COMPONENT = "com.evenwell.powersaving.g3.exception.PowerSaverExceptionActivity";

    /***
     * Samsung
     */
    private String BRAND_SAMSUNG = "samsung";
    private String PACKAGE_SAMSUNG_MAIN = "com.samsung.android.lool";
    private String PACKAGE_SAMSUNG_COMPONENT = "com.samsung.android.sm.ui.battery.BatteryActivity";

    /***
     * One plus
     */
    private String BRAND_ONE_PLUS = "oneplus";
    private String PACKAGE_ONE_PLUS_MAIN = "com.oneplus.security";
    private String PACKAGE_ONE_PLUS_COMPONENT = "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity";

    List<String> PACKAGES_TO_CHECK_FOR_PERMISSIONS = new ArrayList<String>(
            Arrays.asList(PACKAGE_ASUS_MAIN, PACKAGE_XIAOMI_MAIN, PACKAGE_LETV_MAIN, PACKAGE_HONOR_MAIN, PACKAGE_OPPO_MAIN,
                    PACKAGE_OPPO_FALLBACK, PACKAGE_VIVO_MAIN, PACKAGE_VIVO_FALLBACK, PACKAGE_NOKIA_MAIN, PACKAGE_SAMSUNG_MAIN, PACKAGE_ONE_PLUS_MAIN)
    );

    public void getAutoStartPermission(Context context) {
        
        if(Build.BRAND.toLowerCase().equals(BRAND_XIAOMI) || Build.BRAND.toLowerCase().equalsIgnoreCase(BRAND_XIAOMI_REDMI)) {
            autostartXiaomi(context);
        }
        
        if(Build.BRAND.toLowerCase().equalsIgnoreCase(BRAND_ASUS)) {
            autostartAsus(context);
        }

        if(Build.BRAND.toLowerCase().equalsIgnoreCase(BRAND_LETV)) {
            autostartLetv(context);
        }

        if(Build.BRAND.toLowerCase().equalsIgnoreCase(BRAND_HONOR)) {
            autostartHonor(context);
        }

        if(Build.BRAND.toLowerCase().equalsIgnoreCase(BRAND_HUAWEI)) {
            autostartHuwei(context);
        }

        if(Build.BRAND.toLowerCase().equalsIgnoreCase(BRAND_OPPO)) {
            autostartOppo(context);
        }

        if(Build.BRAND.toLowerCase().equalsIgnoreCase(BRAND_VIVO)) {
            autostartVivo(context);
        }

        if(Build.BRAND.toLowerCase().equalsIgnoreCase(BRAND_SAMSUNG)) {
            autostartSamsung(context);
        }

        if(Build.BRAND.toLowerCase().equalsIgnoreCase(BRAND_NOKIA)) {
            autostartNokia(context);
        }

        if(Build.BRAND.toLowerCase().equalsIgnoreCase(BRAND_ONE_PLUS)) {
            autostartOneplus(context);
        }
        
    }

    //to check if your device has auto start permissions or not.
    public boolean isAutoStartPermissionAvailable(Context context) {

        List<ApplicationInfo> packages;
        PackageManager pm = context.getPackageManager();
        packages = pm.getInstalledApplications(0);

        for(ApplicationInfo packageInfo : packages) {
            if (PACKAGES_TO_CHECK_FOR_PERMISSIONS.contains(packageInfo.packageName))
                return true;
        }
        return false;
    }

    private boolean autostartVivo(Context context) {

        if (isPackageExists(context, PACKAGE_VIVO_MAIN) || isPackageExists(context, PACKAGE_VIVO_FALLBACK)) {
            try {
                startIntent(context, PACKAGE_VIVO_MAIN, PACKAGE_VIVO_COMPONENT);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    startIntent(context, PACKAGE_VIVO_FALLBACK, PACKAGE_VIVO_COMPONENT_FALLBACK);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        startIntent(context, PACKAGE_VIVO_MAIN, PACKAGE_VIVO_COMPONENT_FALLBACK_A);
                    } catch (Exception exx) {
                        exx.printStackTrace();
                        return false;
                    }
                }
            }
        } else {
            return false;
        }

        return true;
    }

    private boolean autostartSamsung(Context context) {

        if (isPackageExists(context, PACKAGE_SAMSUNG_MAIN)) {
            try {
                startIntent(context, PACKAGE_SAMSUNG_MAIN, PACKAGE_SAMSUNG_COMPONENT);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    private boolean autostartNokia(Context context) {

        if (isPackageExists(context, PACKAGE_NOKIA_MAIN)) {
            try {
                startIntent(context, PACKAGE_NOKIA_MAIN, PACKAGE_NOKIA_COMPONENT);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    private boolean autostartOneplus(Context context) {

        if (isPackageExists(context, PACKAGE_ONE_PLUS_MAIN)) {
            try {
                startIntent(context, PACKAGE_ONE_PLUS_MAIN, PACKAGE_ONE_PLUS_COMPONENT);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    private boolean autostartOppo(Context context) {
        if (isPackageExists(context, PACKAGE_OPPO_MAIN) || isPackageExists(context, PACKAGE_OPPO_FALLBACK)) {
            try {
                startIntent(context, PACKAGE_OPPO_MAIN, PACKAGE_OPPO_COMPONENT);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    startIntent(context, PACKAGE_OPPO_FALLBACK, PACKAGE_OPPO_COMPONENT_FALLBACK);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        startIntent(context, PACKAGE_OPPO_MAIN, PACKAGE_OPPO_COMPONENT_FALLBACK_A);
                    } catch (Exception exx) {
                        exx.printStackTrace();
                        return false;
                    }
                }
            }
        } else {
            return false;
        }

        return true;
    }

    private boolean autostartHuwei(Context context) {
        if (isPackageExists(context, PACKAGE_HUAWEI_MAIN)) {
            try {
                startIntent(context, PACKAGE_HUAWEI_MAIN, PACKAGE_HUAWEI_COMPONENT);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    startIntent(context, PACKAGE_HUAWEI_MAIN, PACKAGE_HUAWEI_COMPONENT_FALLBACK);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    private boolean autostartHonor(Context context) {

        if (isPackageExists(context, PACKAGE_HONOR_MAIN)) {
            try {
                startIntent(context, PACKAGE_HONOR_MAIN, PACKAGE_HONOR_COMPONENT);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    private boolean autostartLetv(Context context) {

        if (isPackageExists(context, PACKAGE_LETV_MAIN)) {
            try {
                startIntent(context, PACKAGE_LETV_MAIN, PACKAGE_LETV_COMPONENT);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    private boolean autostartAsus(Context context) {

        if (isPackageExists(context, PACKAGE_ASUS_MAIN)) {
            try {
                startIntent(context, PACKAGE_ASUS_MAIN, PACKAGE_ASUS_COMPONENT);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    startIntent(context, PACKAGE_ASUS_MAIN, PACKAGE_ASUS_COMPONENT_FALLBACK);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    private boolean autostartXiaomi(Context context) {

        if (isPackageExists(context, PACKAGE_XIAOMI_MAIN)) {
            try {
                startIntent(context, PACKAGE_XIAOMI_MAIN, PACKAGE_XIAOMI_COMPONENT);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    private void startIntent(Context context, String packageName, String componentName) {

        try {

            Intent intent = new Intent();
            intent.setComponent(new ComponentName(packageName, componentName));
            context.startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private boolean isPackageExists(Context context, String targetPackage) {

        List<ApplicationInfo> packages;
        PackageManager pm = context.getPackageManager();
        packages = pm.getInstalledApplications(0);

        for(ApplicationInfo packageInfo : packages) {
            if (packageInfo.packageName.equals(targetPackage))
                return true;
        }
        return false;
    }
}
