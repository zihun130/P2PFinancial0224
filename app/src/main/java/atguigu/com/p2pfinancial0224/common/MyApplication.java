package atguigu.com.p2pfinancial0224.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

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

        initGallery();

        handler=new Handler();
        pid=android.os.Process.myPid();
        //初始化crashHandler
       // CrashHandler.getInstance().init(this);

    }


    /*
    * 配置gallery
    * */
    private void initGallery() {
        //设置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        ImageLoader imageloader = new PicassoImageLoader();
        CoreConfig coreConfig =
                new CoreConfig.Builder(this, imageloader, theme)
                        .setFunctionConfig(functionConfig).build();
        GalleryFinal.init(coreConfig);
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
