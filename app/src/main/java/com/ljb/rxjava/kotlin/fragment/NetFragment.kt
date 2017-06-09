package com.ljb.rxjava.kotlin.fragment

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.rxjava.kotlin.R
import com.ljb.rxjava.kotlin.protocol.TestProtocol
import com.ljb.rxjava.kotlin.utils.RxUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_net.*


/**
 * Created by L on 2017/6/8.
 */
class NetFragment : Fragment(), View.OnClickListener {

    //懒属性，使用时初始化
    val mTestProtocol: TestProtocol by lazy { TestProtocol() }

    private var mGetDisposable: Disposable? = null
    private var mPostDisposable: Disposable? = null
    private var mPutDisposable: Disposable? = null
    private var mDelDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_net, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_get.setOnClickListener(this)
        btn_post.setOnClickListener(this)
        btn_put.setOnClickListener(this)
        btn_delete.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        RxUtils.dispose(mGetDisposable)
        RxUtils.dispose(mPostDisposable)
        RxUtils.dispose(mPutDisposable)
        RxUtils.dispose(mDelDisposable)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_get -> requestGet()
            R.id.btn_post -> requestPost()
            R.id.btn_put -> requestPut()
            R.id.btn_delete -> requestDel()
        }
    }

    private fun requestDel() {
        RxUtils.dispose(mDelDisposable)
        mDelDisposable = mTestProtocol.testDeleteRequest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    tv_result.text = "del result: $it"
                }, {
                    tv_result.text = "del error:${it.message}"
                })
    }


    private fun requestPut() {
        RxUtils.dispose(mPutDisposable)
        mPutDisposable = mTestProtocol.testPutRequest(mapOf("name" to "Athena"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    tv_result.text = "put result: $it"
                }, {
                    tv_result.text = "put error:${it.message}"
                })
    }

    private fun requestPost() {
        RxUtils.dispose(mPostDisposable)
        mPostDisposable = mTestProtocol.testPostRequest(mapOf("name" to "Zeus"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    tv_result.text = "post result: $it"
                }, {
                    tv_result.text = "post error:${it.message}"
                })
    }


    private fun requestGet() {
        RxUtils.dispose(mGetDisposable)
        mGetDisposable = mTestProtocol.testGetRequest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    tv_result.text = "get result: $it"
                }, {
                    tv_result.text = "get error:${it.message}"
                })
    }


}