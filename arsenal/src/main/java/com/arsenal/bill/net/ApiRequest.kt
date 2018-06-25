package com.arsenal.bill.net

import com.arsenal.bill.retrofit.BaseRequestInfo

class ApiRequest<T : BaseRequestInfo>(var c: T?, callback: CaidouApiCallBack<IResp>){

}
