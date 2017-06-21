package atguigu.com.p2pfinancial0224.common;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by sun on 2017/6/21.
 */
//创建一个新的activity栈,管理activtity
public class ActivityManager {

    private ActivityManager(){};
    private static ActivityManager activityManager=new ActivityManager();
    public static ActivityManager getInstance(){
        return activityManager;
    }

    private Stack<Activity> stack=new Stack<>();


    public void addActivity(Activity activity){
         if(activity!=null){
             stack.add(activity);
         }
    }


    public void removeActivity(Activity activity){
        if(activity!=null){
            for(int i = 0; i <stack.size() ; i++) {
                Activity currentActivity = stack.get(i);
               //添加了多个相同activity时,会全部删除
                if(currentActivity.getClass().equals(activity.getClass())){
                    currentActivity.finish();
                    stack.remove(currentActivity);
                }
                //删除指定的地址的对象activity
                /*if(currentActivity==activity){
                    currentActivity.finish();
                    stack.remove(currentActivity);
                }*/
            }
        }
    }


    public void removeCurrent(){
        Activity activity = stack.lastElement();
        activity.finish();
        stack.remove(activity);
    }

    public void removeAll(){
        for(int i = stack.size()-1; i>=0 ; i--) {
            Activity activity = stack.get(i);
            activity.finish();
            stack.remove(activity);
        }
    }


    public int size(){
        return stack.size();
    }
}
