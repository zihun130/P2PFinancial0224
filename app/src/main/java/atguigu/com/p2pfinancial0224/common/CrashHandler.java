package atguigu.com.p2pfinancial0224.common;

import android.os.Build;
import android.os.Looper;
import android.widget.Toast;

import atguigu.com.p2pfinancial0224.utils.UIUtils;

/**
 * Created by sun on 2017/6/21.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler{

    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    private CrashHandler(){}

    private static CrashHandler crashHandler=null;

    public static CrashHandler getInstance(){
        if(crashHandler==null){
            crashHandler=new CrashHandler();
        }
        return crashHandler;
    }



    public void init(MyApplication myApplication){
        uncaughtExceptionHandler=Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        new Thread(){
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Toast.makeText(UIUtils.getContext(), "系统崩溃了,请重新登录", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        collectionException(e);

        try {
            Thread.sleep(2000);
            ActivityManager.getInstance().removeCurrent();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);


        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private void collectionException(Throwable e) {
        String message = e.getMessage();

        String message00 = Build.DEVICE;

        new Thread(){
            @Override
            public void run() {
                super.run();


            }
        }.start();
    }


}
