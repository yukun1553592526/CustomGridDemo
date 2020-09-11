package com.example.customgriddemo

import java.util.*

class NestedBean{
    private var responseCode: String? = null
    private var isSuccess = false
    private var responseMsg: String? = null
    private var data: List<DataBean?>? = null

    fun getResponseCode(): String? {
        return if (responseCode == null) "" else responseCode
    }

    fun setResponseCode(responseCode: String?) {
        this.responseCode = responseCode
    }

    fun isSuccess(): Boolean {
        return isSuccess
    }

    fun setSuccess(success: Boolean) {
        isSuccess = success
    }

    fun getResponseMsg(): String? {
        return if (responseMsg == null) "" else responseMsg
    }

    fun setResponseMsg(responseMsg: String?) {
        this.responseMsg = responseMsg
    }

    fun getData(): List<DataBean?>? {
        return if (data == null) {
            ArrayList()
        } else data
    }

    fun setData(data: List<DataBean?>?) {
        this.data = data
    }

    override fun toString(): String {
        return "NestedBean(responseCode=$responseCode, isSuccess=$isSuccess, responseMsg=$responseMsg, data=$data)"
    }

    public class DataBean {
        /**
         * name : A
         * id : 1201
         * child : [{"name":"A1","id":"120101"},{"name":"A2","id":"120102"},{"name":"A3","id":"120103"},{"name":"A4","id":"120104"},{"name":"A5","id":"120105"}]
         */
        private var name: String? = null
        private var id: String? = null
        private var child: List<ChildBean?>? = null

        fun getName(): String? {
            return if (name == null) "" else name
        }

        fun setName(name: String?) {
            this.name = name
        }

        fun getId(): String? {
            return if (id == null) "" else id
        }

        fun setId(id: String?) {
            this.id = id
        }

        fun getChild(): List<ChildBean?>? {
            return if (child == null) {
                ArrayList()
            } else child
        }

        fun setChild(child: List<ChildBean?>?) {
            this.child = child
        }

        override fun toString(): String {
            return "DataBean(name=$name, id=$id, child=$child)"
        }

        class ChildBean {
            /**
             * name : A1
             * id : 120101
             */
            var name: String? = null
                get() = if (field == null) "" else field
            var id: String? = null
                get() = field ?: ""

            override fun toString(): String {
                return "ChildBean(name=$name, id=$id"
            }
        }

    }
}