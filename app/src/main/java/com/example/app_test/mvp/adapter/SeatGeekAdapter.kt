package com.example.app_test.mvp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.app_test.R
import com.example.app_test.data.EventsData
import com.example.app_test.data.SeatGeekData
import com.example.app_test.mvp.adapter.viewHolder.ProgressBar
import com.example.app_test.mvp.adapter.viewHolder.SeatGeek

class SeatGeekAdapter(val events: ArrayList<EventsData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            1 -> ProgressBar(inflater.inflate(R.layout.card_view_progress, parent, false))
            else -> SeatGeek(inflater.inflate(R.layout.card_view_seat_geek, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SeatGeek) {
            val event = events[position]
            holder.bind(event)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (events[position].isLoadMore)
            1
        else
            0
    }

    override fun getItemCount(): Int = events.size

    fun loadMore(it: SeatGeekData) {
        events.removeAt(events.size - 1)
        notifyItemRemoved(events.size - 1)

        events.addAll(it.events)
        notifyDataSetChanged()
    }

    fun setProgress(it: EventsData){
        events.add(it)
        notifyDataSetChanged()
    }
}