package com.thanos.kontribute.ui.group_detail.members

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Member
import kotlinx.android.synthetic.main.item_members.view.*

class MembersListAdapter(private var members: ArrayList<Member>,
                         private var membersListListener: MembersListAdapter.MembersListListener):
        RecyclerView.Adapter<MembersListAdapter.ViewHolder>() {

    interface MembersListListener {
        fun onMemberSelected(member: Member)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_members, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(members[position])
    }

    override fun getItemCount(): Int = members.size
    fun updateMembers(members: ArrayList<Member>) {
        this.members = members
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view),
            View.OnClickListener {

        override fun onClick(view: View?) {
            membersListListener.onMemberSelected(members[adapterPosition])
        }
        fun bind(member: Member) {
            view.memberName.text = member.name
        }
    }
}
