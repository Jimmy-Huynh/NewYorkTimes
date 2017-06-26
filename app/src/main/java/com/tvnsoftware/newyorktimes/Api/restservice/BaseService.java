package com.tvnsoftware.newyorktimes.Api.restservice;

import android.content.Context;

import com.tvnsoftware.newyorktimes.Api.CommonInterface;

/**
 * Created by TamHH on 6/26/2017.
 */

public abstract class BaseService<Req, Res> {
    public abstract void request(Context context, CommonInterface.ModelResponse<Res> callBack);

    public abstract void setRequest(Req r);
}
