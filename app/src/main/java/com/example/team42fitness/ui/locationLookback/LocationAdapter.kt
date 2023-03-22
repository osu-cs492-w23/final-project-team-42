package com.example.team42fitness.ui.locationLookback

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.team42fitness.R
import com.example.team42fitness.data.activity.FitnessActivity
import com.example.team42fitness.data.locationLookback.LocationDate
import com.google.android.material.snackbar.Snackbar

/**
 * For use with LocationViewModel, which is used in main screen for location entry feature
 */
class LocationAdapter(private val onClick: (LocationDate) -> Unit) : RecyclerView.Adapter<LocationAdapter.ViewHolder>()
{
    private var fitnessActivities: List<FitnessActivity> = mutableListOf()


    override fun getItemCount() = this.fitnessActivities.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_list_item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(this.fitnessActivities[position])
    }

    fun addFitnessActivities(fitnessActivities: List<FitnessActivity>) {
        this.fitnessActivities = fitnessActivities
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View, val onClick: (LocationDate) -> Unit) : RecyclerView.ViewHolder(itemView)
    {
        private var fitnessActivity: FitnessActivity? = null
        private val dateTV = itemView.findViewById<TextView>(R.id.tv_location_date_text)
        private val stepsTV = itemView.findViewById<TextView>(R.id.tv_steps)

        init
        {
            itemView.setOnClickListener {
                if (fitnessActivity != null) {
                    val uriBase = "geo:0,0?q="
                    val uri: String = uriBase + fitnessActivity!!.location

                    val geoUri = Uri.parse(uri)

                    val intent = Intent(Intent.ACTION_VIEW, geoUri)

                    try {

                        itemView.context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        Snackbar.make(
                            itemView,
                            "An app wasn't found to be able to open and display the location for the entry you entered... :(",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        fun bind(fitnessActivity: FitnessActivity)
        {
            this.fitnessActivity = fitnessActivity
            dateTV.text = fitnessActivity.date
            stepsTV.text = "Steps: " + fitnessActivity.steps.toString()
        }
    }
}