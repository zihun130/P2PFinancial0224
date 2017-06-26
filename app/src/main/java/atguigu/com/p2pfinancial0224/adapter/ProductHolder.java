package atguigu.com.p2pfinancial0224.adapter;

import android.view.View;
import android.widget.TextView;

import atguigu.com.p2pfinancial0224.R;
import atguigu.com.p2pfinancial0224.adapter.baseadapter.BaseHolder;
import atguigu.com.p2pfinancial0224.bean.ProductBean;
import atguigu.com.p2pfinancial0224.utils.UIUtils;
import atguigu.com.p2pfinancial0224.view.ProgressView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/6/25.
 */

public class ProductHolder extends BaseHolder<ProductBean.DataBean> {
    @InjectView(R.id.p_name)
    TextView pName;
    @InjectView(R.id.p_money)
    TextView pMoney;
    @InjectView(R.id.p_yearlv)
    TextView pYearlv;
    @InjectView(R.id.p_suodingdays)
    TextView pSuodingdays;
    @InjectView(R.id.p_minzouzi)
    TextView pMinzouzi;
    @InjectView(R.id.p_minnum)
    TextView pMinnum;
    @InjectView(R.id.p_progresss)
    ProgressView pProgresss;

    @Override
    protected void setContent(ProductBean.DataBean dataBean) {
        pName.setText(dataBean.getName());
        pMoney.setText(dataBean.getMoney());
        pYearlv.setText(dataBean.getYearRate());
        pSuodingdays.setText(dataBean.getSuodingDays());
        pMinzouzi.setText(dataBean.getMinTouMoney());
        pMinnum.setText(dataBean.getMemberNum());
        pProgresss.setSweeAngle(Integer.parseInt(dataBean.getProgress()));
    }

    @Override
    public View initView() {
        View view= View.inflate(UIUtils.getContext(), R.layout.product_item, null);
        ButterKnife.inject(this,view);
        return view;
    }
}
