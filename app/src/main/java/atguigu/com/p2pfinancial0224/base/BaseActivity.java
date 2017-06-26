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
