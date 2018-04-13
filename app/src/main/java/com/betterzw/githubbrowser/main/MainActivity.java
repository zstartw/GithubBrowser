package com.betterzw.githubbrowser.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betterzw.githubbrowser.R;
import com.betterzw.githubbrowser.activity.BaseActivity;
import com.betterzw.githubbrowser.api.Contributor;
import com.betterzw.githubbrowser.api.GitHubService;
import com.betterzw.githubbrowser.api.Repo;
import com.betterzw.githubbrowser.api.RepoSearchResponse;
import com.betterzw.githubbrowser.network.RetrofitFactory;
import com.betterzw.githubbrowser.utils.L;
import com.betterzw.githubbrowser.widget.CustomLoadMoreView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{

    private final String API_URL = "https://api.github.com";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;



    HomeAdapter homeAdapter;

    View emtpyView;
    View loadView;

    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        L.d("oncreate");


        //判断是否要显示splash activity
       /* if(Prefs.isSplashEnabled() && savedInstanceState == null){
            //start splash activity
        }*/


        init();
        getData();


//        val retrofit = Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .build()

        // Create a call instance for looking up Retrofit contributors.
      /*  Call<List<Contributor>> call = github.contributors("square", "retrofit");
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                for (Contributor contributor : response.body()) {
                    L.v("response:"+contributor.login+","+contributor.url+","+contributor.contributions);
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
            }
        });*/
    }

    /**
     * 初始化
     */
    private void init(){

        emtpyView = mInflater.inflate(R.layout.empty, null);
        loadView = mInflater.inflate(R.layout.loading, null);

        homeAdapter = new HomeAdapter(R.layout.home_item_view);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout.setOnRefreshListener(this);
        homeAdapter.setOnLoadMoreListener(this, recyclerView);

        homeAdapter.setEnableLoadMore(true);
        homeAdapter.setLoadMoreView(new CustomLoadMoreView());

        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    public void onLoadMoreRequested() {
        L.v("onLoadMore");
        swipeRefreshLayout.setEnabled(false);
        page++;

//        homeAdapter.loadMoreEnd(false);
        getData();
    }

    @Override
    public void onRefresh() {
        L.v("onRefresh");

        page = 1;
        getData();
    }


    /**
     * 获取数据
     */
    private void getData(){
        Retrofit retrofit = RetrofitFactory.newInstance(API_URL);
        // Create an instance of our GitHub API interface.
        GitHubService github = retrofit.create(GitHubService.class);

        Date date = new Date();

        long time = (date.getTime()/1000L - 7*3600*24)*1000L;

        String newWeekTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date(time));
        L.v("====="+newWeekTime);
        Call<RepoSearchResponse> searchResponseCall = github.searchRepos("android+created:"+newWeekTime, page);
        searchResponseCall.enqueue(new Callback<RepoSearchResponse>() {
            @Override
            public void onResponse(Call<RepoSearchResponse> call, Response<RepoSearchResponse> response) {
                L.v("response:success->"+response.body().getTotal());
//                L.v("response:success->"+response.body().getItems());

                ArrayList<Repo> mDataList = new ArrayList<>();
                for (Repo repo : response.body().getItems()){
                    L.v("repo:"+repo.fullName+","+repo.description+","+repo.stars);

                    mDataList.add(repo);
                }

                if (page == 1){
                    homeAdapter.setNewData(mDataList);
                }else{
                    homeAdapter.addData(mDataList);
                }

                homeAdapter.loadMoreComplete();
            }

            @Override
            public void onFailure(Call<RepoSearchResponse> call, Throwable t) {
                L.v("response:onFailure->");
                homeAdapter.loadMoreFail();
            }
        });
    }
}
