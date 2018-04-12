package com.betterzw.githubbrowser.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zhengwu on 4/11/18.
 */
public interface GitHubService {

//    https://api.github.com/repos/square/retrofit/contributors
    @GET("/repos/{owner}/{repo}/contributors") Call<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);


//    https://api.github.com/search/repositories?q=google&page=1
//    https://api.github.com/search/repositories?q=created:2008-07-04&page=1&order=stars&order=desc
    @GET("search/repositories")
    Call<RepoSearchResponse> searchRepos(@Query("q") String query, @Query("page") int page);



}
