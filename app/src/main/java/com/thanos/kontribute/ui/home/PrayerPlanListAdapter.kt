package com.thanos.kontribute.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Group


class PrayerPlanListAdapter(private var prayerPlanList: ArrayList<Group>,
                            private var prayerPlanListListener: PrayerPlanListListener):
        RecyclerView.Adapter<PrayerPlanListAdapter.PrayerPlanListViewHolder>() {

    interface PrayerPlanListListener {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrayerPlanListViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.progress_bar, parent, false)
        return PrayerPlanListViewHolder(view)
    }

    override fun getItemCount(): Int = prayerPlanList.size

    override fun onBindViewHolder(holder: PrayerPlanListViewHolder, position: Int) {
        val prayerPlan = prayerPlanList[position]
        holder.bind(prayerPlan)
    }

    inner class PrayerPlanListViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
        }

        override fun onClick(view: View?) {
            val prayerPlan = prayerPlanList[adapterPosition]
        }

        fun bind(prayerPlan: Group) {

        }

    }
}