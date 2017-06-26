package atguigu.com.p2pfinancial0224.fragment;

import android.view.View;
import android.widget.TextView;

import atguigu.com.p2pfinancial0224.R;
import atguigu.com.p2pfinancial0224.base.BaseFragment;
import atguigu.com.p2pfinancial0224.utils.DensityUtil;
import atguigu.com.p2pfinancial0224.utils.UIUtils;
import atguigu.com.p2pfinancial0224.view.randomLayout.StellarMap;
import butterknife.InjectView;

/**
 * Created by sun on 2017/6/24.
 */

public class InvestRecommFragment extends BaseFragment {
    @InjectView(R.id.stellar_map)
    StellarMap stellarMap;

    private String[] datas = new String[]{
            "新手福利计划", "财神道90天计划", "硅谷钱包计划",
            "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划",
            "大学老师购买车辆", "屌丝下海经商计划"
    };

    @Override
    public void initview() {
        View view = View.inflate(context, getLayoutID(), null);

        stellarMap.setAdapter(new MyAdapter());
        stellarMap.setGroup(0,true);
        stellarMap.setInnerPadding(DensityUtil.dip2px(context,10),DensityUtil.dip2px(context,10),
                DensityUtil.dip2px(context,10),DensityUtil.dip2px(context,10));

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_recommand;
    }

    @Override
    public String getChildUrl() {
        return null;
    }


    private class MyAdapter implements StellarMap.Adapter {
        @Override
        public int getGroupCount() {
            int num = datas.length / 7;

            if (num % 7 != 0){
                num+=1;
            }
            return num;
        }

        @Override
        public int getCount(int group) {
            if (datas.length % 7 == 0){
                return 7;
            }else{
                if(group == datas.length / 7){
                    return datas.length % 7;
                }else{
                    return 7;
                }
            }
        }

        @Override
        public View getView(int group, int position, View convertView) {
            TextView textView = new TextView(getActivity());
            textView.setText(datas[group*7+position]);

            textView.setTextColor(UIUtils.getColor());
            return textView;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            if (group == 0){
                return 1;
            }else{
                return 0;
            }
        }
    }
}
