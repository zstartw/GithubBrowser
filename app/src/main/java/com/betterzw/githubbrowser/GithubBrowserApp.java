package com.betterzw.githubbrowser;

import android.app.Application;
import android.util.Log;

import com.betterzw.githubbrowser.utils.L;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by zhengwu on 4/10/18.
 */
public class GithubBrowserApp extends Application{

    private static GithubBrowserApp INSTANCE;

    public GithubBrowserApp(){
        INSTANCE = this;
    }

    public static GithubBrowserApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}
