package com.arsenal.bill.net

public interface CaidouApiCallBack<T> {
    fun onSuccess(data: T?);
    fun onFailure(t: Throwable);
    fun onComplete();
}
