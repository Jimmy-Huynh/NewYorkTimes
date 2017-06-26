package com.tvnsoftware.newyorktimes.Api.restservice;

import android.content.Context;

import com.tvnsoftware.newyorktimes.Api.CommonInterface;
import com.tvnsoftware.newyorktimes.Api.RetrofitManager;
import com.tvnsoftware.newyorktimes.model.Article;
import com.tvnsoftware.newyorktimes.model.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TamHH on 6/26/2017.
 */

public class ArticalService extends BaseService<Map<String, String>, Article> {

    private Map<String, String> map;

    @Override
    public void request(Context context, CommonInterface.ModelResponse<Article> callBack) {
        RetrofitManager.getInstance().sendApiRequest(RetrofitManager.getInstance().getRestApiEndpointInterface().getArtical(map), callBack);
    }

    @Override
    public void setRequest(Map<String, String> r) {
        map = r;
    }
}
