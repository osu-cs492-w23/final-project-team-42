package com.example.team42fitness.ui.locationLookback

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.data.locationLookback.LocationData

/**
 * RecyclerView adapter for user location entries
 */
class ClickedDayAdapter(private val onClick: (LocationData) -> Unit) : RecyclerView.Adapter<ClickedDayAdapter.ViewHolder>()
{
    private val TAG = "ClickedDayAdapter"

    private val locationEntries: MutableList<LocationData> = mutableListOf()


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


    fun addLocationEntry(locationEntry: LocationData)
    {
        locationEntries.add(locationEntry)
        notifyDataSetChanged()
        //notifyItemInserted(0)
    }

    fun deleteLocationEntry(entryPosition: Int)
    {
        locationEntries.removeAt(entryPosition)
        notifyItemRemoved(entryPosition)
    }



    class ViewHolder(itemView: View, private val onClick: (LocationData) -> Unit) : RecyclerView.ViewHolder(itemView)
    {
        private val locationTV = itemView.findViewById<TextView>(R.id.tv_clicked_day_text)

        private lateinit var currLocationEntry: LocationData
        init
        {
            // Log.d(TAG, "ViewHolder() within ClickedDayAdapter called for $currLocationEntry")
            itemView.setOnClickListener { currLocationEntry.let(onClick) }
        }

        fun bind(locationEntry: LocationData)
        {
            currLocationEntry = locationEntry
            locationTV.text = locationEntry.locationName
        }
    }
}