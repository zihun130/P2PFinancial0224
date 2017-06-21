package atguigu.com.p2pfinancial0224.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by sun on 2017/6/21.
 */

public class MyApplication extends Application {

    public static Context getContext() {
        return context;
    }
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化上下文
        context = this;
        //初始化crashHandler
        CrashHandler.getInstance().init(this);

    }
}
