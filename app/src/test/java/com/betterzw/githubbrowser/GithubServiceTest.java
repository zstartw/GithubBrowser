package com.betterzw.githubbrowser;

import com.betterzw.githubbrowser.api.GitHubService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.Assert.assertEquals;

/**
 * Created by zhengwu on 4/11/18.
 */
@RunWith(JUnit4.class)
public class GithubServiceTest {


    private GitHubService service;
    private MockWebServer mockWebServer;

    @Before
    public void createService() throws IOException{
        mockWebServer = new MockWebServer();

        service = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(GitHubService.class);
    }

    @After
    public void stopService() throws IOException{
        mockWebServer.shutdown();
    }

    @Test
    public void getContributors() throws IOException, InterruptedException {
        enqueueResponse("contributors.json");
    }

    private void enqueueResponse(String fileName) throws IOException {
        enqueueResponse(fileName, Collections.<String, String>emptyMap());
    }

    private void enqueueResponse(String fileName, Map<String, String> headers) throws IOException {
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("api-response/" + fileName);
        BufferedSource source = Okio.buffer(Okio.source(inputStream));
        MockResponse mockResponse = new MockResponse();
        for (Map.Entry<String, String> header : headers.entrySet()) {
            mockResponse.addHeader(header.getKey(), header.getValue());
        }
        mockWebServer.enqueue(mockResponse
                .setBody(source.readString(StandardCharsets.UTF_8)));
    }
}
