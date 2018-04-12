package com.betterzw.githubbrowser.network;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.betterzw.githubbrowser.GithubBrowserApp;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by zhengwu on 4/11/18.
 */
public class OkHttpConnectionFactory {
    private static final String CACHE_DIR_NAME = "okhttp-cache";
    private static final long NET_CACHE_SIZE = 64 * 1024 * 1024;
    @VisibleForTesting
    @NonNull
    public static final Cache NET_CACHE = new Cache(new File(GithubBrowserApp.getInstance().getCacheDir(),
            CACHE_DIR_NAME), NET_CACHE_SIZE);
    private static final long SAVED_PAGE_CACHE_SIZE = NET_CACHE_SIZE * 1024;
    @NonNull public static final Cache SAVE_CACHE = new Cache(new File(GithubBrowserApp.getInstance().getFilesDir(),
            CACHE_DIR_NAME), SAVED_PAGE_CACHE_SIZE);

    @NonNull private static final OkHttpClient CLIENT = createClient();

    @NonNull public static OkHttpClient getClient() {
        return CLIENT;
    }

    @NonNull
    private static OkHttpClient createClient() {
        return new OkHttpClient.Builder()
                .cache(NET_CACHE)
                .build();
    }

    private OkHttpConnectionFactory() {
    }
}
