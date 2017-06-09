package com.ljb.rxjava.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ljb.rxjava.kotlin.fragment.main.MainFragment
import com.ljb.rxjava.kotlin.utils.RxBus
import io.reactivex.subjects.PublishSubject

class MainActivity : AppCompatActivity() {

    val mPublishSubject: PublishSubject<String> by lazy { PublishSubject.create<String>() }
    
    val mRxBus: RxBus by lazy { RxBus() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    private fun initFragment() {
        val mainFragment = MainFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_content, mainFragment, mainFragment.javaClass.name)
                .commit()
    }
}

