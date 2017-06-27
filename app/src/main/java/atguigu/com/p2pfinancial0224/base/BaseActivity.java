package atguigu.com.p2pfinancial0224.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import atguigu.com.p2pfinancial0224.common.ActivityManager;
import butterknife.ButterKnife;

/**
 * Created by sun on 2017/6/26.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static final String LOGINBEAN = "loginbean";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.inject(this);

        ActivityManager.getInstance().addActivity(this);

        initView();
        initData();
        initListener();
        initTitle();
    }

    public void initTitle() {
        
    }

    public void showToast(String message){
        Toast.makeText(BaseActivity.this,message, Toast.LENGTH_SHORT).show();
    }

    public void saveUser(LoginBean bean){
        SharedPreferences sp=getSharedPreferences(LOGINBEAN,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        edit.putString("name",bean.getName());
        edit.putString("imageurl",bean.getImageurl());
        edit.putString("iscredit",bean.getIscredit());
        edit.putString("phone",bean.getPhone());
        edit.commit();
    }

    public LoginBean getUser(){
        SharedPreferences sp=getSharedPreferences(LOGINBEAN,MODE_PRIVATE);
        LoginBean loginBean = new LoginBean();
        loginBean.setName(sp.getString("name","admin"));
        loginBean.setImageurl(sp.getString("imageurl",""));
        loginBean.setIscredit(sp.getString("iscredit",""));
        loginBean.setPhone(sp.getString("phone",""));
        return loginBean;
    }


    public void clearSp(){
        //清除Sp文件（清除的是sp内容）
        SharedPreferences sp = getSharedPreferences(LOGINBEAN, MODE_PRIVATE);
        sp.edit().clear().commit();
    }


    public void saveImage(String image){
        SharedPreferences sp = getSharedPreferences(LOGINBEAN, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("imageurl",image);
        edit.putBoolean("isFile",true);
        edit.commit();
    }

    public String getImage(){
        SharedPreferences sp = getSharedPreferences(LOGINBEAN, MODE_PRIVATE);
        boolean isFile = sp.getBoolean("isFile",false);
        if(isFile){
            return sp.getString("imageurl","");
        }else {
            return "";
        }
    }

    public abstract void initListener();

    public abstract void initData();

    public abstract void initView();

    public abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
