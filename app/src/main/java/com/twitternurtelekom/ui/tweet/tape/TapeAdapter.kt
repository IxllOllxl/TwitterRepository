package com.twitternurtelekom.ui.tweet.tape

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import com.twitternurtelekom.R
import com.twitternurtelekom.TWITTER_DATE_FORMAT
import com.twitternurtelekom.model.Tweet
import com.twitternurtelekom.ui.BaseViewHolder
import com.twitternurtelekom.utils.textCirclePlaceholder
import kotlinx.android.synthetic.main.item_tweet.view.*
import java.text.SimpleDateFormat
import java.util.*

class TapeAdapter constructor(private val listener: TapeListener) :
        PagedListAdapter<Tweet, RecyclerView.ViewHolder>(diffCallback) {
    private val format = SimpleDateFormat(TWITTER_DATE_FORMAT, Locale.ENGLISH)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tweet, parent, false)
        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BaseViewHolder) {
            val view = holder.itemView
            getItem(position)?.let {
                view.name.text = it.user?.name
                view.content.text = it.text
                view.date.text = DateUtils.getRelativeTimeSpanString(format.parse(it.createdAt).time,
                        System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_SHOW_YEAR)
                view.avatar.textCirclePlaceholder(it.user?.name)
                view.avatar.setImageURI(it.user?.avatar)
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffCallback<Tweet>() {
            override fun areContentsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
                var same = false
                if (oldItem.text == newItem.text
                        && oldItem.createdAt == newItem.createdAt) same = true
                return same
            }

            override fun areItemsTheSame(oldItem: Tweet, newItem: Tweet) = oldItem.id == newItem.id
        }
    }
}
