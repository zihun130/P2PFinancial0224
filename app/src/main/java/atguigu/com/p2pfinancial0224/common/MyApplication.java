package atguigu.com.p2pfinancial0224.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by sun on 2017/6/21.
 */

public class MyApplication extends Application {

    public static Context getContext() {
        return context;
    }
    public static Context context;
    private static Handler handler;
    private static int pid;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        handler=new Handler();
        pid=android.os.Process.myPid();
        //初始化crashHandler
       // CrashHandler.getInstance().init(this);

    }

    public static int getPid(){
        return pid;
    }
    public static Handler getHandler(){
        return handler;
    }
    //饿汉式单例
    /*public MyApplication (){};
    public static MyApplication myApplication=new MyApplication();
    public static MyApplication getInstance(){
        return myApplication;
    }*/
   //懒汉式单例
    /*public MyApplication(){}
    public static MyApplication myApplication=null;
    public static MyApplication getInstance(){
        if(myApplication!=null){
            myApplication=new MyApplication();
        }
        return myApplication;
    }*/
  //双重锁校验
   /* private volatile static MyApplication instance;
    private MyApplication(){}
    private static MyApplication getInstance(){
        if(instance!=null){
            synchronized (MyApplication.class){
                if(instance!=null){
                    instance=new MyApplication();
                }
            }
        }
        return instance;
    }
*/

   //静态内部类
   /* private MyApplication(){}
    private static MyApplication getInstance(){
        return StaticSingletonHolder.instance;
    }
    private static class StaticSingletonHolder {
        private static MyApplication instance=new MyApplication();
    }*/

}
