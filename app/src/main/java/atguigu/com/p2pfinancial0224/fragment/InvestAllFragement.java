package atguigu.com.p2pfinancial0224.fragment;

import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;

import atguigu.com.p2pfinancial0224.R;
import atguigu.com.p2pfinancial0224.adapter.ImportAdapter003;
import atguigu.com.p2pfinancial0224.base.BaseFragment;
import atguigu.com.p2pfinancial0224.bean.ProductBean;
import atguigu.com.p2pfinancial0224.common.AppNetConfig;
import butterknife.InjectView;

/**
 * Created by sun on 2017/6/24.
 */

public class InvestAllFragement extends BaseFragment {

    @InjectView(R.id.lv)
    ListView lv;

    @Override
    public void initview() {
        View view = View.inflate(context, getLayoutID(), null);
    }

    @Override
    public void setContent(String json) {
        super.setContent(json);
        ProductBean productBean = JSON.parseObject(json, ProductBean.class);
        lv.setAdapter(new ImportAdapter003(context,productBean.getData()));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_all;
    }

    @Override
    public String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }

}
