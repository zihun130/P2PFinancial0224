package atguigu.com.p2pfinancial0224.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.List;

import atguigu.com.p2pfinancial0224.R;
import atguigu.com.p2pfinancial0224.base.BaseActivity;
import atguigu.com.p2pfinancial0224.common.ActivityManager;
import atguigu.com.p2pfinancial0224.common.AppNetConfig;
import atguigu.com.p2pfinancial0224.utils.BitmapUtils;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class SettingActivity extends BaseActivity {

    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.tv_user_change)
    TextView tvUserChange;
    @InjectView(R.id.btn_user_right)
    Button btnUserRight;
    @InjectView(R.id.btn_user_logout)
    Button btnUserLogout;

    String names[] = {"相册", "相机"};
    @InjectView(R.id.iv_user_icon)
    ImageView ivUserIcon;

    @Override
    public void initTitle() {
        super.initTitle();
        baseTitle.setText("头像设置");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Picasso.with(this)
                .load(AppNetConfig.BASE_URL+"images/tx.png")
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap bitmap) {
                        return BitmapUtils.getBitmap(bitmap);
                    }

                    @Override
                    public String key() {
                        return "";
                    }
                })
                .into(ivUserIcon);
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @OnClick({R.id.btn_user_right, R.id.btn_user_logout, R.id.tv_user_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_user_right:
                finish();
                break;
            case R.id.btn_user_logout:
                clearSp();
                ActivityManager.getInstance().removeAll();
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.tv_user_change:
                new AlertDialog.Builder(this)
                        .setTitle("选择资源")
                        .setItems(names, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0 :
                                       showPhotos();
                                        break;
                                    case 1 :
                                        showCamera();
                                        break;
                                }
                            }
                        })
                        .setCancelable(false)
                        .show();
                break;
        }
    }


    private void makeImage(String photoPath) {

        //展示图片
        Picasso.with(this)
                .load(new File(photoPath))
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap bitmap) {
                        return BitmapUtils.getBitmap(bitmap);
                    }

                    @Override
                    public String key() {
                        return "";
                    }
                })
                .into(ivUserIcon);
        //上传图片

        //保存到Sp中
        saveImage(photoPath);
    }



    private void showCamera() {
        GalleryFinal.openCamera(01, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {

                Log.d("image", "onHanlderSuccess: "
                        +resultList.get(0).getPhotoPath());
                makeImage(resultList.get(0).getPhotoPath());
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

                Log.d("image", "onHanlderFailure: "+errorMsg);

            }
        });

    }

    private void showPhotos() {
        GalleryFinal.openGallerySingle(02, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {

                Log.d("image", "onHanlderSuccess: "
                        +resultList.get(0).getPhotoPath());
                makeImage(resultList.get(0).getPhotoPath());

            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });
    }




}
