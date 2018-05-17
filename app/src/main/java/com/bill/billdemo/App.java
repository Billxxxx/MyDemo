package com.bill.billdemo;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
//            ARouter.openLog();     // 打印日志
//            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
//        ARouter.init(this); // 尽可能早，推荐在Application中初始化

        //初始化Stetho调试工具
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
        Thread.setDefaultUncaughtExceptionHandler(new UnCatchExceptionHandler(this));
    }

    public static App getInstance() {
        return instance;
    }

}
