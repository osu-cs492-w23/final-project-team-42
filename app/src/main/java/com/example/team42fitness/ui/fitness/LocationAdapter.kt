package com.example.team42fitness.ui.fitness

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.data.fitnessData.LocationDate

/**
 * For use with LocationViewModel
 */
class LocationAdapter(private val onClick: (LocationDate) -> Unit) : RecyclerView.Adapter<LocationAdapter.ViewHolder>()
{
    private val locationDates: MutableList<LocationDate> = mutableListOf()

    /**
     * For functionality about adding a day to the list (in the works / not sure if will add that)
     */
    fun addDate(locationDate: LocationDate)
    {
        locationDates.add(locationDate)
        // notifyItemChanged(0)
    }

    override fun getItemCount() = this.locationDates.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_list_item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(this.locationDates[position])
    }


    class ViewHolder(itemView: View, val onClick: (LocationDate) -> Unit) : RecyclerView.ViewHolder(itemView)
    {
        private var specifiedDate: LocationDate? = null
        private val dateTV = itemView.findViewById<TextView>(R.id.tv_location_date_text)


        init
        {
            itemView.setOnClickListener { specifiedDate?.let(onClick) }
        }

        fun bind(locationDate: LocationDate)
        {
            specifiedDate = locationDate
            dateTV.text = locationDate.locationDateText

        }
    }
}