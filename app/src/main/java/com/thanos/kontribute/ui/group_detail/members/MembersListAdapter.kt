package com.thanos.kontribute.ui.group_detail.members

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Member

class MembersListAdapter(private var members: ArrayList<Member>):
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
            val member = members[adapterPosition]
            val popUp = PopupMenu(view?.context, view)
            if (member.isAdmin) {
                popUp.menuInflater.inflate(R.menu.member_menu_is_admin, popUp.menu)
            } else {
                popUp.menuInflater.inflate(R.menu.member_menu_not_admin, popUp.menu)
            }
            popUp.show()

            popUp.setOnMenuItemClickListener {item ->
                member.isAdmin = item.itemId != R.id.navigation_is_admin
                return@setOnMenuItemClickListener false
            }
        }
        fun bind(member: Member) {
            view.memberName.text = member.name
        }
    }
}
