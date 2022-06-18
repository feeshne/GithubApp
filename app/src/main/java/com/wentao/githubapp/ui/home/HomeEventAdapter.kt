package com.wentao.githubapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wentao.githubapp.R
import com.wentao.githubapp.ui.home.model.ItemsViewModel

class HomeEventAdapter :
    RecyclerView.Adapter<HomeEventAdapter.ViewHolder>() {

    var mList: List<ItemsViewModel> = arrayListOf()

    fun setData(list: List<ItemsViewModel>?) {
        if (list == null || list.isEmpty()) {
            return
        }
        this.mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_card_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mList.isEmpty()) {
            return
        }
        val itemsViewModel = mList[position]

        holder.eventAuthorText.text = itemsViewModel.authorName
        holder.eventDescText.text = itemsViewModel.desc
        Glide.with(holder.authorAvatar).load(itemsViewModel.avatar).into(holder.authorAvatar)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val eventAuthorText: TextView = itemView.findViewById(R.id.text_event_author)
        val eventDescText: TextView = itemView.findViewById(R.id.text_event_desc)
        val authorAvatar: ImageView = itemView.findViewById(R.id.image_avatar)
    }
}