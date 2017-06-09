package com.ljb.rxjava.kotlin.protocol

import com.ljb.rxjava.kotlin.net.XgoHttpClient

/**
 * Created by L on 2017/6/8.
 */
class TestProtocol : BaseProtocol() {

    val URL = "http://integer.wang/init/json.shtml";

    //get
    fun testGetRequest() = createObservable(URL, XgoHttpClient.METHOD_GET)

    //post
    fun testPostRequest(params: Map<String, String>) = createObservable(URL, XgoHttpClient.METHOD_POST, params)


    //post
    fun testPutRequest(params: Map<String, String>) = createObservable(URL, XgoHttpClient.METHOD_PUT, params)

    //delete
    fun testDeleteRequest() = createObservable(URL, XgoHttpClient.METHOD_DELETE)


}