package com.arsenal.bill.util;

import android.app.Activity;

import java.util.Enumeration;
import java.util.Hashtable;

public class BaseActivityManager {

  // app 当前显示的 Activity
  public static Activity activeActivity;

  // Activity 栈，保存打开过的所有 Activity
  public static Hashtable<String, Activity> hashActivity = new Hashtable<>();

  /**
   * activity 启动时调用
   */
  public static void onAllActivityCreate(Activity activity) {
    hashActivity.put(activity.getClass().getSimpleName(), activity);
  }

  /**
   * activity 销毁时调用
   */
  public static void onAllActivityDestroy(Activity activity) {
    hashActivity.remove(activity.getClass().getSimpleName());
  }

  /**
   * app退出的相关操作
   */
  public static void doAppExit() {
    // 强制关闭所有页面
    Enumeration<Activity> emu = hashActivity.elements();
    while (emu.hasMoreElements()) {
      Activity ac = emu.nextElement();
      ac.finish();
    }
    // 清除已执行和正在执行的页面列表
    hashActivity.clear();
    BaseActivityManager.activeActivity = null;
    android.os.Process.killProcess(android.os.Process.myPid());
  }
}