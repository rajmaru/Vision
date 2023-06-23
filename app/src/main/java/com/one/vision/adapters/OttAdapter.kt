package com.one.vision.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.OttActivity
import com.one.vision.R
import com.one.vision.databinding.OttRvItemBinding
import com.one.vision.models.Ott

class OttAdapter : RecyclerView.Adapter<OttAdapter.OttViewHolder>(){
    private lateinit var context: Context
    private lateinit var ottList: ArrayList<Ott>
    var onItemClick: ((Ott) -> Unit)? = null

    fun setOttList(context: Context, ottList: ArrayList<Ott>){
        this.context = context
        this.ottList = ottList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OttViewHolder {
        return OttViewHolder(OttRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: OttViewHolder, position: Int) {
        Glide.with(context.applicationContext)
            .load(context.getDrawable(ottList[position].image!!))
            .into(holder.binding.ottImage)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(ottList[position])
        }
    }

    override fun getItemCount(): Int {
        return ottList.size
    }

    inner class OttViewHolder(val binding: OttRvItemBinding) : RecyclerView.ViewHolder(binding.root)
}