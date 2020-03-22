package kr.hs.newsreader.presentation.newslist.adapter

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item_news_list.view.*

class NewsListRecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.image
    val title: TextView = itemView.title
    val content: TextView = itemView.content
    val tags: List<TextView> =
        listOf(itemView.tag1, itemView.tag2, itemView.tag3).map { it as TextView }
}