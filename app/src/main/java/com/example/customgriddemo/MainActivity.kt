package com.example.customgriddemo

import android.app.Activity
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.icu.lang.UCharacter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customgriddemo.adapter.NestedAdapter
import com.example.customgriddemo.model.BaseBean
import com.example.customgriddemo.model.ContentBean
import com.example.customgriddemo.model.FooterBean
import com.example.customgriddemo.model.HeadBean
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private val mNestedRV: RecyclerView by BindView(R.id.nested_rv)
    private var json: String
    private lateinit var mNestedAdapter: NestedAdapter
    private var mData = ArrayList<BaseBean>()

    private fun <T : View> Activity.BindView(@IdRes id: Int): Lazy<T> = lazy {
        findViewById(id)
    }

    init {
        json = """{
                "data": [
                    {
                        "name": "A",
                        "id": "1201",
                        "child": [
                            {
                                "name": "A1",
                                "id": "120101"
                            },
                            {
                                "name": "A2",
                                "id": "120102"
                            },
                            {
                                "name": "A3",
                                "id": "120103"
                            },
                            {
                                "name": "A4",
                                "id": "120104"
                            },
                            {
                                "name": "A5",
                                "id": "120105"
                            }
                        ]
                    },
                    {
                        "name": "B",
                        "id": "1202",
                        "child": [
                            {
                                "name": "B1",
                                "id": "120201"
                            },
                            {
                                "name": "B2",
                                "id": "120202"
                            },
                            {
                                "name": "B3",
                                "id": "120203"
                            }
                        ]
                    },
                    {
                        "name": "C",
                        "id": "1001203",
                        "child": [
                            {
                                "name": "C1",
                                "id": "120301"
                            },
                            {
                                "name": "C2",
                                "id": "120302"
                            },
                            {
                                "name": "C3",
                                "id": "120303"
                            },
                            {
                                "name": "C4",
                                "id": "120304"
                            },
                            {
                                "name": "C5",
                                "id": "120305"
                            },
                            {
                                "name": "C6",
                                "id": "120306"
                            }
                        ]
                    },
                    {
                        "name": "D",
                        "id": "1001204",
                        "child": [
                            {
                                "name": "D1",
                                "id": "120401"
                            },
                            {
                                "name": "D2",
                                "id": "120402"
                            },
                            {
                                "name": "D3",
                                "id": "120403"
                            },
                            {
                                "name": "D4",
                                "id": "120404"
                            },
                            {
                                "name": "D5",
                                "id": "120405"
                            }
                        ]
                    },
                    {
                        "name": "E",
                        "id": "1001205",
                        "child": [
                            {
                                "name": "E1",
                                "id": "120501"
                            },
                            {
                                "name": "E2",
                                "id": "120502"
                            },
                            {
                                "name": "E3",
                                "id": "120503"
                            },
                            {
                                "name": "E4",
                                "id": "120504"
                            },
                            {
                                "name": "E5",
                                "id": "120505"
                            },
                            {
                                "name": "E6",
                                "id": "120506"
                            },
                            {
                                "name": "E7",
                                "id": "120507"
                            }
                        ]
                    }
                ],
                "responseCode": "0000",
                "isSuccess": true,
                "responseMsg": "ok"
            }
            
            """
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initData() {
        val nestedBean = Gson().fromJson(json, NestedBean::class.java)
        val dataBeanList = ArrayList<HeadBean>()
        for (dataItem in nestedBean.getData()!!) {
            val childBeanList = ArrayList<ContentBean>()
            val headBean = HeadBean()
            headBean.id = dataItem?.getId()!!
            headBean.name = dataItem.getName()!!
            for (child in dataItem.getChild()!!) {
                val contentBean = ContentBean(R.mipmap.ic_content_logo, null, child?.name!!, child.id!!)
                childBeanList.add(contentBean)
            }
            headBean.contentList = childBeanList
            dataBeanList.add(headBean)
        }
        Log.e("yukun", "MainActivity: nestedBean = ${nestedBean}\n" +
                "dataBeanList = $dataBeanList" )
        mData.addAll(convert2BaseBean(dataBeanList))
        mNestedAdapter.notifyDataSetChanged()
    }

    private fun convert2BaseBean(dataBeanList: ArrayList<HeadBean>): List<BaseBean> {
        val list = ArrayList<BaseBean>()
        for (dataBeanItem in dataBeanList) {
            list.add(dataBeanItem)
            list.addAll(dataBeanItem.contentList)
            list.add(FooterBean())
        }
        return list
    }

    private fun initView() {
        mNestedAdapter = NestedAdapter(this, mData)

        val gridLayoutManager = GridLayoutManager(this, 4)
        gridLayoutManager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (mData[position] is HeadBean) {
                    return gridLayoutManager.spanCount
                } else if (mData[position] is ContentBean) {
                    return 1
                } else if (mData[position] is FooterBean) {
                    return gridLayoutManager.spanCount
                }
                return 1
            }
        }
        mNestedRV.layoutManager = gridLayoutManager
        mNestedRV.adapter = mNestedAdapter
        mNestedAdapter.setOnItemClickListener(object : NestedAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                Toast.makeText(
                    this@MainActivity,
                    (mData[position] as ContentBean).name,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


}