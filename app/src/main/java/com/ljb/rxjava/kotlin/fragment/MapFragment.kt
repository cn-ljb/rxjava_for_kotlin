package com.ljb.rxjava.kotlin.fragment

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.rxjava.kotlin.R
import com.ljb.rxjava.kotlin.utils.RxUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_map.*

/**
 * Created by L on 2017/7/5.
 */
class MapFragment : Fragment() {

    private var mMapDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        RxUtils.dispose(mMapDisposable)
    }

    private fun initData() {
        val list = ArrayList<String>()
        list.add("1")
        list.add("2")
        list.add("3")

        tv_result.text = list.toString();

        demoMap(list)
    }

    private fun demoMap(list: ArrayList<String>) {
        mMapDisposable = Observable.just(list)
                .map {
                    updateList(it)
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    tv_result.text = it.toString()
                }, {
                    tv_result.text = it.message
                })
    }

    private fun updateList(list: ArrayList<String>): List<String> {
        list.add("A")
        list.add("B")
        list.add("C")
        return list
    }
}