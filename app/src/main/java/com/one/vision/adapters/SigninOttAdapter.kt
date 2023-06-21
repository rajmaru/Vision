package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.databinding.SigninOttRvItemBinding

class SigninOttAdapter : RecyclerView.Adapter<SigninOttAdapter.OttViewHolder>(){
    private lateinit var context: Context
    private lateinit var ottList: ArrayList<Int>

    fun setOttList(context: Context, ottList: ArrayList<Int>){
        this.context = context
        this.ottList = ottList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OttViewHolder {
        return OttViewHolder(SigninOttRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: OttViewHolder, position: Int) {
        Glide.with(context.applicationContext)
            .load(context.getDrawable(ottList[position]))
            .into(holder.binding.ottImage)
    }

    override fun getItemCount(): Int {
        return ottList.size
    }

    inner class OttViewHolder(val binding: SigninOttRvItemBinding) : RecyclerView.ViewHolder(binding.root)
}