package com.bill.billdemo.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import com.bill.billdemo.App;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank on 16/5/26.
 */
public class SystemParameter {


    // 当前的包是否处于 debug 模式
    private static boolean DBG = false;
    /*系统版本*/
    private static String systemVersion;
    /*最大缓存  172 or 512*/
    private static int maxMemory;
    //应用程序已获得内存
    private static long totalMemory;
    //应用程序已获得内存中未使用内存
    private static long freeMemory;


    /**
     * 在 AndroidMainifest.xml 中最好不设置 android:debuggable 属性，而是由打包方式来决定其值，避免 debug 模式取值的偏差
     */
    public static void initDBG(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            DBG = ((info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 系统版本
     */
    public static String getSystemVersion() {
        if (systemVersion == null) {
            systemVersion = Build.VERSION.RELEASE;
        }
        return systemVersion;
    }

    public static boolean getDBG() {
        return DBG;
    }

    /**
     * @return 最大运行内存 KB
     */
    public static int getMaxMemory() {
        if (maxMemory == 0) {
            maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;
        }
        return maxMemory;
    }

    /**
     * @return 应用程序已获得内存 KB
     */

    public static long getTotalMemory() {
        if (totalMemory == 0l) {
            totalMemory = ((int) Runtime.getRuntime().totalMemory()) / 1024;
        }
        return totalMemory;
    }

    /**
     * @return 应用程序已获得内存中未使用内存 KB
     */
    public static long getFreeMemory() {
        if (freeMemory == 0l) {
            freeMemory = ((int) Runtime.getRuntime().freeMemory()) / 1024;
        }
        return freeMemory;
    }

    /**
     * 获取内置SD卡路径
     *
     * @return
     */
    public static String getInnerSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 获取外置SD卡路径
     *
     * @return 应该就一条记录或空
     */
    public static List<String> getExtSDCardPath() {
        List<String> lResult = new ArrayList<String>();
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("extSdCard")) {
                    String[] arr = line.split(" ");
                    String path = arr[1];
                    File file = new File(path);
                    if (file.isDirectory()) {
                        lResult.add(path);
                    }
                }
            }
            isr.close();
        } catch (Exception e) {
        }
        return lResult;
    }

    //版本名
    public static String getVersionName() {
        return getPackageInfo(App.Companion.getContext()).versionName;
    }

    //版本号
    public static int getVersionCode() {
        return getPackageInfo(App.Companion.getContext()).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }
}