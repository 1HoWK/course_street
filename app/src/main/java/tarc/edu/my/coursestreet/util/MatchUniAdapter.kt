package tarc.edu.my.coursestreet.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.data.Universities

class MatchUniAdapter (
    val fn: (ViewHolder, Universities) -> Unit = { _, _ ->}
): ListAdapter<Universities, MatchUniAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<Universities>(){

        override fun areItemsTheSame(a: Universities, b: Universities) = a.id == b.id
        override fun areContentsTheSame(a: Universities, b: Universities) = a == b
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view
        val programmeTitle1: TextView = view.findViewById(R.id.textView_programme1)
        val uniName: TextView = view.findViewById(R.id.textView_uni)
        val programmeTitle2: TextView = view.findViewById(R.id.textView_programme2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.matcheduni_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uniList = getItem(position)

        holder.programmeTitle1.text = uniList.Programme1
        holder.programmeTitle2.text = uniList.Programme2
        holder.uniName.text = uniList.id
//        if (question.answered){
//            holder.questionStatus.text = "Answered"
//            holder.questionStatus.setBackgroundColor(Color.parseColor("#1DE24B"))
//        }else{
//            holder.questionStatus.text = "Unanswered"
//            holder.questionStatus.setBackgroundColor(Color.parseColor("#EC0303"))
//        }

        fn(holder, uniList)
    }

}