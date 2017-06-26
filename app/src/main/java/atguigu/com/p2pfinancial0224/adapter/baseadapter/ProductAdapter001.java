package atguigu.com.p2pfinancial0224.adapter.baseadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by sun on 2017/6/25.
 */

public abstract class ProductAdapter001<T> extends BaseAdapter {
    private final Context context;
    private final List<T> datas;

    public ProductAdapter001(Context context, List<T> data) {
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
       return getChildView(position,convertView,parent,datas.get(position));
    }

    protected abstract View getChildView(int position,
                                         View convertView,
                                         ViewGroup parent,
                                         T t);


}
