package atguigu.com.p2pfinancial0224.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import atguigu.com.p2pfinancial0224.R;
import atguigu.com.p2pfinancial0224.base.BaseActivity;
import atguigu.com.p2pfinancial0224.common.AppNetConfig;
import atguigu.com.p2pfinancial0224.utils.HttpClientUtil;
import butterknife.InjectView;

public class RegitsterActivity extends BaseActivity {


    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.et_register_number)
    EditText etRegisterNumber;
    @InjectView(R.id.et_register_name)
    EditText etRegisterName;
    @InjectView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @InjectView(R.id.et_register_pwdagain)
    EditText etRegisterPwdagain;
    @InjectView(R.id.btn_register)
    Button btnRegister;

    @Override
    public void initTitle() {
        super.initTitle();
        baseTitle.setText("注 册");
        baseBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void initListener() {
        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etRegisterName.getText().toString().trim();
                String number = etRegisterNumber.getText().toString().trim();
                String pwd = etRegisterPwd.getText().toString().trim();
                String again = etRegisterPwdagain.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    showToast("用户名不能为空");
                    return;
                }

                if(TextUtils.isEmpty(number)){
                    showToast("手机号码不能为空");
                    return;
                }

                if(!TextUtils.isEmpty(pwd)){
                    if(!pwd.equals(again)){
                        showToast("两次输入的密码不一致");
                        return;
                    }
                }else {
                    showToast("密码不能为空");
                    return;
                }


                Map<String, String> map=new HashMap<String, String>();
                map.put("name",name);
                map.put("phone",number);
                map.put("password",pwd);

                HttpClientUtil.getInstance().post(AppNetConfig.REGISTER, map, new HttpClientUtil.OnHttpClientListener() {
                    @Override
                    public void onSuccess(String json) {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            boolean isExist = jsonObject.getBoolean("isExist");
                            if(isExist){
                                showToast("用户已存在");
                            }else {
                                showToast("注册成功");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(String message) {

                    }
                });

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_regitster;
    }

}
