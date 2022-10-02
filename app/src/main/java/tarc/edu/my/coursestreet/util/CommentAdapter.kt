package tarc.edu.my.coursestreet.util

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tarc.edu.my.coursestreet.R

import tarc.edu.my.coursestreet.data.Reply
import tarc.edu.my.coursestreet.data.USERS

class CommentAdapter (
    val fn: (ViewHolder, Reply) -> Unit = { _, _ ->}
        ) : ListAdapter<Reply, CommentAdapter.ViewHolder>(DiffCallback) {

    private var userID = ""
    private var currentUserID = ""

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val root = view
        val userTxt: TextView = view.findViewById(R.id.userCommentTxt)
        val userNameTxt: TextView = view.findViewById(R.id.userNameTxt)
        val helpfulTxt: TextView = view.findViewById(R.id.helpfulTxt)
        val helpfulBtn: Button = view.findViewById(R.id.helpfulBtn)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Reply>(){
        override fun areItemsTheSame(a: Reply, b: Reply) = a.id == b.id
        override fun areContentsTheSame(a: Reply, b: Reply) = a == b

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = getItem(position)


        holder.userNameTxt.text = comment.userName
        holder.userTxt.text = comment.comment
        var bool = currentUserID == userID


        holder.helpfulBtn.isVisible = bool

        if (comment.status){
            holder.helpfulBtn.isVisible = false
            holder.helpfulTxt.isVisible = true

        }



        fn(holder, comment)
    }

    fun setUserID(id: String){
        this.userID = id

    }

    fun setCurrentUserID(id: String){
        this.currentUserID = id
    }



}