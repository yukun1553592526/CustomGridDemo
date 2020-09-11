package com.example.customgriddemo.model

import android.graphics.drawable.AdaptiveIconDrawable

class ContentBean : BaseBean {
    var drawable: Int = 0
    var clazz: Class<*>?
    var name: String
    var id: String

    constructor(drawable: Int, clazz: Class<*>?, name: String, id: String): super() {
        this.drawable = drawable
        this.clazz = clazz
        this.name = name
        this.id= id
    }

    override fun toString(): String {
        return "ContentBean(drawable=$drawable, clazz=$clazz, name='$name', id='$id')"
    }
}