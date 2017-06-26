package atguigu.com.p2pfinancial0224.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import atguigu.com.p2pfinancial0224.R;
import atguigu.com.p2pfinancial0224.adapter.baseadapter.ProductAdapter001;
import atguigu.com.p2pfinancial0224.bean.ProductBean;
import atguigu.com.p2pfinancial0224.view.ProgressView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/6/25.
 */

public class ImportAdapter001 extends ProductAdapter001<ProductBean.DataBean> {
    private final Context context;
    private final List<ProductBean.DataBean> datas;

    public ImportAdapter001(Context context, List<ProductBean.DataBean> data) {
        super(context, data);
        this.context = context;
        this.datas = data;
    }

    @Override
    protected View getChildView(int position, View convertView, ViewGroup parent, ProductBean.DataBean dataBean) {
        ProductAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.product_item, null);
            viewHolder=new ProductAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ProductAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.pName.setText(dataBean.getName());
        viewHolder.pMoney.setText(dataBean.getMoney());
        viewHolder.pYearlv.setText(dataBean.getYearRate());
        viewHolder.pSuodingdays.setText(dataBean.getSuodingDays());
        viewHolder.pMinzouzi.setText(dataBean.getMinTouMoney());
        viewHolder.pMinnum.setText(dataBean.getMemberNum());
        viewHolder.pProgresss.setSweeAngle(Integer.parseInt(dataBean.getProgress()));
        return convertView;
    }

    static class ViewHolder {
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

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
