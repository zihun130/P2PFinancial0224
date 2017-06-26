package atguigu.com.p2pfinancial0224.adapter;

import android.content.Context;

import java.util.List;

import atguigu.com.p2pfinancial0224.adapter.baseadapter.BaseHolder;
import atguigu.com.p2pfinancial0224.adapter.baseadapter.ProductAdapter003;
import atguigu.com.p2pfinancial0224.bean.ProductBean;

/**
 * Created by sun on 2017/6/25.
 */

public class ImportAdapter003 extends ProductAdapter003<ProductBean.DataBean> {
    private final Context context;
    private final List<ProductBean.DataBean> datas;

    public ImportAdapter003(Context context, List<ProductBean.DataBean> data) {
        super(context, data);
        this.context=context;
        this.datas=data;
    }

    @Override
    protected BaseHolder getViewHolder() {
        return new ProductHolder();
    }
}
