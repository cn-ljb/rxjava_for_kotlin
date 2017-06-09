package com.ljb.rxjava.kotlin.model

/**
 * Created by L on 2017/6/8.
 */

data class Data(var blogCatalogId: Int, var createTime: Long, var downNum: Int, var isUse: Int, var name: String) {

    override fun toString(): String {
        return "Data{" +
                "blogCatalogId=" + blogCatalogId +
                ", createTime=" + createTime +
                ", downNum=" + downNum +
                ", isUse=" + isUse +
                ", name='" + name + '\'' +
                '}'
    }
}
