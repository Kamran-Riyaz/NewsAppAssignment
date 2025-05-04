package com.example.news_assignment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_assignment.data.model.NodesItem
import com.example.news_assignment.ui.util.formatTimestamp
import com.example.recyclerviewexample.databinding.RowSingleNewsItemBinding

class NewsRecyclerAdapter(private val newsItems: ArrayList<NodesItem>, private val context: Context) :
    RecyclerView.Adapter<NewsRecyclerAdapter.MyNewsItemViewHolder>() {

    inner class MyNewsItemViewHolder(val binding: RowSingleNewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(newsItem: NodesItem) {
            binding.articleTitle.text = newsItem.title
            binding.articleSummary.text = newsItem.summary
            binding.articleTypeBadge.text = newsItem.type
            newsItem.created?.let {
                binding.articleDate.text = formatTimestamp(it)
            }
            newsItem.image?.size?.let {
                if (it > 0) {
                    Glide.with(context).load(newsItem.image?.get(0)?.fileUrl).into(binding.articleImage)
                } else {
                    binding.articleImage.visibility = View.GONE
                    binding.articleTypeBadge.visibility = View.GONE
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNewsItemViewHolder {
        val binding =
            RowSingleNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyNewsItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsItems.size
    }

    override fun onBindViewHolder(holder: MyNewsItemViewHolder, position: Int) {
        val newsItem = newsItems.get(position)
        holder.setData(newsItem)
    }

    fun addData(newsItemsData: ArrayList<NodesItem>) {
        val oldSize = newsItems.size
        newsItems.addAll(newsItemsData)
        notifyItemRangeInserted(oldSize, newsItemsData.size)
    }

}