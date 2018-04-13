package com.betterzw.githubbrowser.main;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.betterzw.githubbrowser.R;
import com.betterzw.githubbrowser.api.Repo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by zhengwu on 4/11/18.
 */
public class HomeAdapter extends BaseQuickAdapter<Repo, BaseViewHolder> {

    public HomeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Repo item) {

        helper.setText(R.id.titleTV, item.fullName);
        helper.setText(R.id.descriptionTV, item.description);

        helper.setText(R.id.startTV, mContext.getString(R.string.format_star, item.stars));
        helper.setText(R.id.forkTV, mContext.getString(R.string.format_fork, item.forks));
        helper.setText(R.id.languageTV, item.language);

        SimpleDraweeView draweeView = helper.getView(R.id.icon_iv);
        draweeView.setImageURI(item.owner.avatorUrl);

    }
}