package com.istore.twitsplit.twitlist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.istore.twitsplit.R
import com.istore.twitsplit.db.Twit

class TwitListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<TwitListAdapter.TwitViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var twits = emptyList<Twit>()

    inner class TwitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val twitItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwitViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return TwitViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TwitViewHolder, position: Int) {
        val current = twits[position]
        holder.twitItemView.text = current.twit
    }

    internal fun setTwits(twits: List<Twit>) {
        this.twits = twits
        notifyDataSetChanged()
    }

    override fun getItemCount() = twits.size
}


