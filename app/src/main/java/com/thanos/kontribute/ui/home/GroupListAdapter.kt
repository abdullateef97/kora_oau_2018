package com.thanos.kontribute.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Group
import kotlinx.android.synthetic.main.item_group.view.*


class GroupListAdapter(private var groupList: ArrayList<Group>,
                       private var groupListListener: GroupListListener):
        RecyclerView.Adapter<GroupListAdapter.GroupListViewHolder>() {

    interface GroupListListener {
        fun onGroupSelected(group: Group)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupListViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_group, parent, false)
        return GroupListViewHolder(view)
    }

    override fun getItemCount(): Int = groupList.size

    override fun onBindViewHolder(holder: GroupListViewHolder, position: Int) {
        val group = groupList[position]
        holder.bind(group)
    }

    fun updateGroups(groups: ArrayList<Group>) {
        this.groupList = groups
        notifyDataSetChanged()
    }

    fun addGroup(group: Group) {
        this.groupList.add(group)
        notifyDataSetChanged()
    }

    inner class GroupListViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            groupListListener.onGroupSelected(groupList[adapterPosition])
        }

        fun bind(group: Group) {

            itemView.txtTitle.text = group.title

            val drawable = TextDrawable.builder()
                    .buildRect(group.title.substring(0,1), group.color)

            itemView.imgGroup.setImageDrawable(drawable)

        }

    }
}