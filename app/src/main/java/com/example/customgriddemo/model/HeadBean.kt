package com.example.customgriddemo.model

class HeadBean : BaseBean() {
    lateinit var id: String
    lateinit var name: String
    lateinit var contentList: List<ContentBean>
    override fun toString(): String {
        return "HeadBean(id='$id', name='$name', contentList=$contentList)"
    }
}