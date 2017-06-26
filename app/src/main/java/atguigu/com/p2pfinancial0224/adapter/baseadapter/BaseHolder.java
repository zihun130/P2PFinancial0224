package atguigu.com.p2pfinancial0224.adapter.baseadapter;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by sun on 2017/6/25.
 */

public abstract class BaseHolder<T> {
    private final View rootView;
    private T t;

    public BaseHolder(){
        rootView = initView();
        ButterKnife.inject(this,rootView);
        rootView.setTag(this);
    }


    public void setData(T t) {
        this.t = t;
        setContent(t);
    }

    public View getView(){
        return rootView;
    }

    protected abstract void setContent(T t);

    public abstract View initView();
}
