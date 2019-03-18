package com.frobengineering.getposts.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frobengineering.getposts.R
import com.frobengineering.getposts.data.Post
import kotlinx.android.synthetic.main.fragment_post.view.*


class PostRecyclerViewAdapter(
    var mValues: List<Post> = listOf()
) : RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = mValues[position]
        holder.mUserIdView.text = holder.itemView.context.getString(R.string.id, post.userId.toString())
        holder.mTitle.text = holder.itemView.context.getString(R.string.title, post.title)
        holder.mBody.text = holder.itemView.context.getString(R.string.body, post.body)

        with(holder.mView) {
            tag = post
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mUserIdView: TextView = mView.tv_user_id
        val mTitle: TextView = mView.tv_title
        val mBody: TextView = mView.tv_body

        override fun toString(): String {
            return super.toString() + " '" + mTitle.text + "'"
        }
    }
}
