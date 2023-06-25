package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.databinding.OttRvItemBinding
import com.one.vision.models.Tag

class OttAdapter : RecyclerView.Adapter<OttAdapter.OttViewHolder>(){
    private lateinit var context: Context
    private lateinit var tagList: ArrayList<Tag>
    var onItemClick: ((Tag) -> Unit)? = null

    fun setOttList(context: Context, tagList: ArrayList<Tag>){
        this.context = context
        this.tagList = tagList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OttViewHolder {
        return OttViewHolder(OttRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: OttViewHolder, position: Int) {
        Glide.with(context.applicationContext)
            .load(context.getDrawable(tagList[position].image!!))
            .into(holder.binding.ottImage)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(tagList[position])
        }
    }

    override fun getItemCount(): Int {
        return tagList.size
    }

    inner class OttViewHolder(val binding: OttRvItemBinding) : RecyclerView.ViewHolder(binding.root)
}