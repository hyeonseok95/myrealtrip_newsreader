package kr.hs.newsreader.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SummaryArticle(
    val imageUrl: String?,
    val title: String?,
    val content: String?,
    val keyword: List<String>,
    val linkUrl: String?
) : Parcelable