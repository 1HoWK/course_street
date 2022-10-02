package tarc.edu.my.coursestreet.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tarc.edu.my.coursestreet.data.EventPhoto
import tarc.edu.my.coursestreet.R

class EventAdapter (
    val fn: ( ViewHolder, EventPhoto) -> Unit = {_, _ ->}
): ListAdapter<EventPhoto, EventAdapter.ViewHolder>(DiffCallback){

    //static member
    companion object DiffCallback: DiffUtil.ItemCallback<EventPhoto>(){
        override fun areItemsTheSame(a: EventPhoto, b: EventPhoto) = a.id == b.id
        override fun areContentsTheSame(a: EventPhoto, b: EventPhoto) = a == b
    }

    //Inner class
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val root = view
        val eventPhoto: ImageView = view.findViewById(R.id.eventPhoto)
        val eventTitle: TextView = view.findViewById(R.id.eventTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.eventPhoto.load(item.eventPhoto) { placeholder(R.drawable.loading_ani) }
        holder.eventTitle.text = item.author

        fn(holder, item)
    }
}