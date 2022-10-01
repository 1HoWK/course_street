package tarc.edu.my.coursestreet.util

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.data.Questions


class QuestionAdapter (
    val fn: (ViewHolder, Questions) -> Unit = { _, _ ->}
        ): ListAdapter<Questions, QuestionAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<Questions>(){

        override fun areItemsTheSame(a: Questions, b: Questions) = a.id == b.id
        override fun areContentsTheSame(a: Questions, b: Questions) = a == b
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view
        val questionTitle: TextView = view.findViewById(R.id.questionTitle)
        val questionTxt: TextView = view.findViewById(R.id.questionTxt)
        val questionStatus: TextView = view.findViewById(R.id.questionStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forum_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = getItem(position)

        holder.questionTitle.text = question.title
        holder.questionTxt.text = question.question
        if (question.answered){
            holder.questionStatus.text = "Answered"
            holder.questionStatus.setBackgroundColor(Color.parseColor("#1DE24B"))
        }else{
            holder.questionStatus.text = "Unanswered"
            holder.questionStatus.setBackgroundColor(Color.parseColor("#EC0303"))
        }

        fn(holder, question)
    }

}