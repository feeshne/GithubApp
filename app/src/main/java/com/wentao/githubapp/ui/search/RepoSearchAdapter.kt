package com.wentao.githubapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wentao.githubapp.R
import com.wentao.githubapp.ui.search.model.ItemsViewModel

class RepoSearchAdapter :
    RecyclerView.Adapter<RepoSearchAdapter.ViewHolder>() {

    lateinit var mList: List<ItemsViewModel>

    fun setData(list: List<ItemsViewModel>?) {
        if (list == null || list.isEmpty()) {
            return
        }
        this.mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_card_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mList.isEmpty()) {
            return
        }
        val itemsViewModel = mList[position]

        holder.repoNameText.text = itemsViewModel.repoName
        holder.repoDescText.text = itemsViewModel.desc

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val repoNameText: TextView = itemView.findViewById(R.id.text_repo_name)
        val repoDescText: TextView = itemView.findViewById(R.id.text_repo_desc)
    }
}