package com.example.news_assignment.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_assignment.ui.adapter.NewsRecyclerAdapter
import com.example.news_assignment.ui.viewmodel.NewsViewModel
import com.example.news_assignment.data.model.NodesItem
import com.example.recyclerviewexample.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var binding: MainActivityBinding
    private lateinit var adapter: NewsRecyclerAdapter
    private var newsItems: ArrayList<NodesItem> = arrayListOf()
    private var  isLoading = false
    private var currentPage = 1
    private var isLastPage = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        fetchLatestNews(currentPage)
        setUpObservers()
    }

    fun setUpObservers() {
        viewModel.newsData.observe(this) { response->
            if(response.success) {
                Log.d("Response ", response.toString())
                newsItems.addAll(response.data?.data?.nodes as ArrayList<NodesItem>)
                adapter.addData(newsItems)
                isLastPage = response.data?.data?.totalPages == currentPage
                isLoading = false
                binding.progressBar.visibility = View.GONE

            } else {
                Log.d("Error ", response.toString())
            }

        }
    }

    private fun setUpRecyclerView() {
        adapter = NewsRecyclerAdapter(newsItems, this)
        binding.rvNews.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNews.layoutManager = linearLayoutManager
        binding.rvNews.addOnScrollListener(object: PaginationScrollListener(linearLayoutManager){
            override fun loadMoreItems() {
                isLoading = true
                currentPage++
                fetchLatestNews(currentPage)
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })
    }
    fun fetchLatestNews(page: Int) {
        binding.progressBar.visibility = View.VISIBLE
        isLoading = true
        viewModel.getLatestNews(page)
    }
}

abstract class PaginationScrollListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    abstract fun loadMoreItems()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                && firstVisibleItemPosition >= 0
            ) {
                loadMoreItems()
            }
        }
    }
}