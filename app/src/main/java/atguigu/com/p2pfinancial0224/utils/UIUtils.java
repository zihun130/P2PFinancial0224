package atguigu.com.p2pfinancial0224.utils;

import android.content.Context;
import android.graphics.Color;

import java.util.Random;

import atguigu.com.p2pfinancial0224.common.MyApplication;

/**
 * Created by sun on 2017/6/21.
 */

public class UIUtils {
    public static Context getContext(){
        return MyApplication.context;
    }

    public static void runOnUIThread(Runnable runnable) {
        if(MyApplication.getPid()==android.os.Process.myTid()){
            runnable.run();
        }else {
            MyApplication.getHandler().post(runnable);
        }
    }


    public static int getColor(){
        Random random = new Random();
        int red = random.nextInt(100)+50;
        int green = random.nextInt(100)+50;
        int blue = random.nextInt(100)+50;

        return Color.rgb(red,green,blue);
    }
}
