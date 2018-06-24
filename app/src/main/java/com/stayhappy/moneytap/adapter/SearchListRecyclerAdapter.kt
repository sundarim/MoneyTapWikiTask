package com.stayhappy.moneytap.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.stayhappy.moneytap.R
import com.stayhappy.moneytap.model.Page
import com.stayhappy.moneytap.model.Wikipedia
import kotlinx.android.synthetic.main.item_wikipedia.view.*
import java.text.FieldPosition

class SearchListRecyclerAdapter : RecyclerView.Adapter<SearchListRecyclerAdapter.SearchViewHolder>() {

    private var wikiList: MutableList<Page> = mutableListOf()

    interface onSearchItemClick{
        fun onSearchItemClicked(title : String){
        }
    }
    private lateinit var mOnSearchItemClickListener :onSearchItemClick

    fun setOnSearchItemClickListener(listener : onSearchItemClick){
       mOnSearchItemClickListener = listener
    }

    fun setData(list : MutableList<Page>){
        wikiList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
            SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_wikipedia, parent, false))

    override fun getItemCount(): Int {
        return wikiList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindItems(wikiList[position])
    }


    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item: Page) {
           itemView.textview_title.text = item.title
            itemView.textview_description.text = item.terms?.description.toString()
            Picasso.with(itemView.context).load(item.thumbnail?.source).placeholder(R.mipmap.ic_launcher).
                    into(this.itemView.imageView)

            itemView.setOnClickListener {
                item.title?.let { it1 -> mOnSearchItemClickListener.onSearchItemClicked(it1) }
            }


        }

    }
}