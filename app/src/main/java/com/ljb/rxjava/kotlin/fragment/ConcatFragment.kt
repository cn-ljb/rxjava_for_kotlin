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
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_concat.*

/**
 * concat
 * 可以将多个Observables的输出合并，就好像它们是一个单个的Observable一样
 * Created by L on 2017/6/9.
 */
class ConcatFragment : Fragment() {

    private var mConcatDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_concat, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        concatDemo()
    }

    override fun onDestroy() {
        super.onDestroy()
        RxUtils.dispose(mConcatDisposable)
    }


    private fun concatDemo() {
        mConcatDisposable = Observable.concat(
                queryContactsFromLocation(),
                queryContactsForNet())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    initPage(it)
                }, {
                    it.printStackTrace()
                })
    }

    private fun initPage(list: List<Contacter>) {
        view_load.visibility = View.GONE
        lv_list.adapter = ArrayAdapter(activity, R.layout.item_list, R.id.tv_text, list)
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
            SystemClock.sleep(1000)
            val contacts = ArrayList<Contacter>()
            contacts.add(Contacter("location:张三"))
            contacts.add(Contacter("location:李四"))
            contacts.add(Contacter("location:王五"))
            it.onNext(contacts)
            it.onComplete()
        }
    }
}