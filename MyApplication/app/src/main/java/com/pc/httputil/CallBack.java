package com.pc.httputil;

/**
 * Created by wenbinbin on 2018/7/14.
 */

public interface CallBack<T> {
    void Success(T t);
    void Fail(String err);
}
