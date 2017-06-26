package atguigu.com.p2pfinancial0224.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import atguigu.com.p2pfinancial0224.R;
import atguigu.com.p2pfinancial0224.bean.ProductBean;
import atguigu.com.p2pfinancial0224.view.ProgressView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sun on 2017/6/25.
 */

public class ProductAdapter extends BaseAdapter {
    private final Context context;
    private final List<ProductBean.DataBean> datas;

    public ProductAdapter(Context context, List<ProductBean.DataBean> data) {
        this.context = context;
        this.datas = data;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.product_item, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        ProductBean.DataBean dataBean = datas.get(position);
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
