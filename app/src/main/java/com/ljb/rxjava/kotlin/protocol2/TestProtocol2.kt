package com.ljb.rxjava.kotlin.protocol

import com.ljb.rxjava.kotlin.model.Data
import com.ljb.rxjava.kotlin.net.XgoHttpClient

/**
 * Created by L on 2017/6/8.
 */
class TestProtocol2 : BaseProtocol2() {

    val URL = "http://integer.wang/init/json.shtml"

    //get
    fun testGetRequest() = createObservable(URL, XgoHttpClient.METHOD_GET, Data::class.java)

    //post
    fun testPostRequest(params: Map<String, String>) = createObservable(URL, XgoHttpClient.METHOD_POST, params, Data::class.java)

    //put
    fun testPutRequest(params: Map<String, String>) = createObservable(URL, XgoHttpClient.METHOD_PUT, params, Data::class.java)

    //delete
    fun testDeleteRequest() = createObservable(URL, XgoHttpClient.METHOD_DELETE, Data::class.java)


}