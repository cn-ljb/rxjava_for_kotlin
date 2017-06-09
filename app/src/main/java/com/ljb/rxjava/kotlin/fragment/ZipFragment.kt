package com.ljb.rxjava.kotlin.fragment

import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.ljb.rxjava.kotlin.R
import com.ljb.rxjava.kotlin.model.Contacter
import com.ljb.rxjava.kotlin.utils.RxUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_zip.*

/**
 * Created by L on 2017/6/9.
 */
class ZipFragment : Fragment() {

    private var mZipDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_zip, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getContactData()
    }

    override fun onDestroy() {
        super.onDestroy()
        RxUtils.dispose(mZipDisposable)
    }


    private fun getContactData() {
        mZipDisposable = Observable.zip(
                queryContactsFromLocation(),
                queryContactsForNet(),
                BiFunction<List<Contacter>, List<Contacter>, List<Contacter>> { t1, t2 -> zipList(t1, t2) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    initPage(it)
                }, {
                    it.printStackTrace()
                })
    }

    private fun zipList(list1: List<Contacter>, list2: List<Contacter>): List<Contacter> {
        val list = ArrayList<Contacter>()
        list.addAll(list1)
        list.addAll(list2)
        return list
    }

    private fun initPage(list: List<Contacter>?) {
        view_load.visibility = View.GONE
        lv_list.adapter = ArrayAdapter<Contacter>(activity, R.layout.item_list, R.id.tv_text, list)
    }


    /**
     * 模拟网络数据
     * */
    private fun queryContactsForNet(): Observable<List<Contacter>> {
        return Observable.create {
            SystemClock.sleep(3000)
            val contacts = ArrayList<Contacter>()
            contacts.add(Contacter("net:Zeus"))
            contacts.add(Contacter("net:Athena"))
            contacts.add(Contacter("net:Prometheus"))
            it.onNext(contacts)
            it.onComplete()
        }
    }

    /**
     * 模拟本地数据
     * */
    private fun queryContactsFromLocation(): Observable<List<Contacter>> {
        return Observable.create {
            val contacts = ArrayList<Contacter>()
            contacts.add(Contacter("location:张三"))
            contacts.add(Contacter("location:李四"))
            contacts.add(Contacter("location:王五"))
            it.onNext(contacts)
            it.onComplete()
        }
    }

}