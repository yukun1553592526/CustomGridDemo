package com.example.customgriddemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.customgriddemo.R
import com.example.customgriddemo.model.BaseBean
import com.example.customgriddemo.model.ContentBean
import com.example.customgriddemo.model.FooterBean
import com.example.customgriddemo.model.HeadBean

class NestedAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private var mContext: Context
    private var mDataList: List<Any>
    private var mInflater: LayoutInflater
    private lateinit var mOnItemClickListener: OnItemClickListener

    private val TYPE_HEAD = 1
    private val TYPE_CONTENT = 2
    private val TYPE_FOOT = 3

    constructor(context: Context, dataList: List<Any>) {
        mContext = context
        mDataList = dataList
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == TYPE_HEAD) {
            return HeaderViewHolder(mInflater.inflate(R.layout.adapter_item_header, parent, false))
        } else if (viewType == TYPE_CONTENT) {
            return ContentViewHolder(mInflater.inflate(R.layout.adapter_item_content, parent, false))
        } else {
            return FooterViewHolder(mInflater.inflate(R.layout.adapter_item_footer, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            (holder as HeaderViewHolder).setHeaderData(mDataList[position] as HeadBean)
        } else if (holder is ContentViewHolder) {
            holder.setContentData(mContext, mDataList[position] as ContentBean)
            holder.itemView.setOnClickListener {
                mOnItemClickListener.onItemClick(it, holder.getAdapterPosition())
            }
        } else if (holder is FooterViewHolder) {
            (holder as FooterViewHolder).mView.setVisibility(if (position == mDataList!!.size - 1) View.VISIBLE else View.GONE)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (mDataList[position] is HeadBean) {
            return TYPE_HEAD
        } else if (mDataList[position] is ContentBean) {
            return TYPE_CONTENT
        } else if (mDataList[position] is FooterBean) {
            return TYPE_FOOT
        }
        return TYPE_HEAD
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    fun setOnItemClickListener(mOnItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headTitleTV: TextView

        init {
            headTitleTV = itemView.findViewById(R.id.adapter_item_header_tv)
        }

        fun setHeaderData(headBean: HeadBean) { headTitleTV.text = headBean.name }
    }

    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemContentIV: ImageView
        private val itemContentTV: TextView

        init {
            itemContentIV = itemView.findViewById(R.id.adapter_item_content_iv)
            itemContentTV = itemView.findViewById(R.id.adapter_item_content_tv)
        }

        fun setContentData(context: Context, contentBean: ContentBean) {
            itemContentIV.setImageDrawable(ContextCompat.getDrawable(context, contentBean.drawable))
            itemContentTV.text = contentBean.name
        }
    }

    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mView: View

        init {
            mView = itemView.findViewById(R.id.view1)
        }
    }
}