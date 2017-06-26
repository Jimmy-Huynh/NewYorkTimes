package com.tvnsoftware.newyorktimes.Api;

/**
 * Created by TamHH on 6/26/2017.
 */

public class CommonInterface<T> {
    public interface ModelResponse<T> {
        void onSuccess(T result);

        void onFail();
    }
}
