package com.example.team42fitness.ui.fitness

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.data.fitnessData.LocationData

class ClickedDayAdapter(private val onClick: (LocationData) -> Unit) : RecyclerView.Adapter<ClickedDayAdapter.ViewHolder>()
{
    private val locationEntries: MutableList<LocationData> = mutableListOf()



    fun addLocationEntry(locationEntry: LocationData)
    {
        locationEntries.add(locationEntry)
        // notifyItemInserted(0)
    }

    override fun getItemCount() = this.locationEntries.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.clickedday_list_item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(this.locationEntries[position])
    }



    class ViewHolder(itemView: View, val onClick: (LocationData) -> Unit) : RecyclerView.ViewHolder(itemView)
    {
        private val locationTV = itemView.findViewById<TextView>(R.id.tv_clicked_day_text)

        init
        {
            itemView.setOnClickListener {  }
        }

        fun bind(locationEntry: LocationData)
        {
            locationTV.text = locationEntry.locationName
        }
    }
}