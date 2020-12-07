package ru.spider.imgurclient.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.spider.imgurclient.R
import ru.spider.imgurclient.databinding.ItemCommentBinding
import ru.spider.imgurclient.model.CommentItem

class CommentsAdapter(private val comments: List<CommentItem>): RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        val binding = ItemCommentBinding.bind(view)

        return CommentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class CommentViewHolder(val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = comments.getOrNull(position)

            binding.userName.text = item?.author ?: "USER NOT FOUND"
            binding.comment.text = item?.comment ?: ""
        }
    }
}