package atguigu.com.p2pfinancial0224.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import atguigu.com.p2pfinancial0224.R;
import atguigu.com.p2pfinancial0224.base.BaseFragment;
import atguigu.com.p2pfinancial0224.utils.UIUtils;
import butterknife.InjectView;

/**
 * Created by sun on 2017/6/24.
 */

public class InvestHotFragment extends BaseFragment {


    @InjectView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    private String[] datas = new String[]{
            "新手福利计划", "财神道90天计划", "硅谷钱包计划",
            "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划",
            "大学老师购买车辆", "屌丝下海经商计划"
    };

    @Override
    public void initview() {
        View view = View.inflate(context, getLayoutID(), null);
    }

    @Override
    protected void initData() {
       idFlowlayout.setAdapter(new TagAdapter<String>(datas){

           @Override
           public View getView(FlowLayout parent, int position, String s) {
               final TextView textView=new TextView(context);
               textView.setTextSize(25);
               textView.setTextColor(Color.WHITE);
               Drawable drawable = getResources().getDrawable(R.drawable.hot_shape);
               textView.setBackgroundDrawable(drawable);

               GradientDrawable gd= (GradientDrawable) textView.getBackground();

               gd.setColor(UIUtils.getColor());

               textView.setText(s);
               textView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Toast.makeText(context,textView.getText().toString(), Toast.LENGTH_SHORT).show();
                   }
               });

               return textView;
           }
       });

        idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                return false;
            }
        });
    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_hot;
    }

    @Override
    public String getChildUrl() {
        return null;
    }

}
