package com.betterzw.githubbrowser.activity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhengwu on 4/10/18.
 */
public class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected Resources resources;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        resources = mContext.getResources();
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);


        ButterKnife.bind(this);
    }
}
