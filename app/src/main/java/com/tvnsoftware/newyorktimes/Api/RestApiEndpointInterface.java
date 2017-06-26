package com.tvnsoftware.newyorktimes.Api;

import com.tvnsoftware.newyorktimes.model.Article;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by TamHH on 6/26/2017.
 */

public interface RestApiEndpointInterface {
    @GET("articlesearch.json")
    Call<Article> getArtical(@QueryMap Map<String, String> options);
}
