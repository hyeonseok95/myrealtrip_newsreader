package kr.hs.newsreader.presentation.newslist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kr.hs.newsreader.R
import kr.hs.newsreader.model.SummaryArticle
import kr.hs.newsreader.presentation.newslist.NewsListNavigator

class NewsListRecyclerViewAdapter(
    private val newsListNavigator: NewsListNavigator
) :
    RecyclerView.Adapter<NewsListRecyclerViewViewHolder>() {
    private val itemList = mutableListOf<SummaryArticle>()
    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: NewsListRecyclerViewViewHolder, position: Int) {
        val item = itemList[position]
        holder.image.load(item.imageUrl)
        holder.title.text = item.title
        holder.content.text = item.content

        try {
            holder.tags[0].text = item.keyword[0]
            holder.tags[1].text = item.keyword[1]
            holder.tags[2].text = item.keyword[2]

        } catch (exception: Exception) {

        }

        holder.itemView.setOnClickListener {
            newsListNavigator.gotoDetail(item.linkUrl ?: return@setOnClickListener, item)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsListRecyclerViewViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recycler_item_news_list, parent, false)
        return NewsListRecyclerViewViewHolder(view)
    }

    fun addItems(summaryArticle: List<SummaryArticle>) {
        summaryArticle.forEach { itemList.add(it) }
        newsListNavigator.hideLoading()
        notifyDataSetChanged()
    }

    fun clearItems() {
        itemList.clear()
        notifyDataSetChanged()
    }
}