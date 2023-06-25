package com.one.vision.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.one.vision.databinding.LanguagesItemBinding
import com.one.vision.models.Tag

class LanguagesAdapter : RecyclerView.Adapter<LanguagesAdapter.LanguagesViewHolder>(){
    private lateinit var context: Context
    private lateinit var languagesList: ArrayList<Tag>
    var onItemClick: ((Tag) -> Unit)? = null

    fun setLanguagesList(context: Context, languagesList: ArrayList<Tag>){
        this.context = context
        this.languagesList = languagesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguagesViewHolder {
        return LanguagesViewHolder(LanguagesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LanguagesViewHolder, position: Int) {
        Glide.with(context.applicationContext)
            .load(languagesList[position].image)
            .into(holder.binding.languagesImg)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(languagesList[position])
        }
    }

    override fun getItemCount(): Int {
        return languagesList.size
    }

    inner class LanguagesViewHolder(val binding: LanguagesItemBinding) : RecyclerView.ViewHolder(binding.root)

}