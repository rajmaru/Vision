package com.one.vision.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.one.vision.R
import com.one.vision.databinding.PricingItemBinding
import com.one.vision.models.Pricing

class PricingAdapter : RecyclerView.Adapter<PricingAdapter.PricingViewHolder>(){
    private lateinit var context: Context
    private lateinit var pricingList: ArrayList<Pricing>

    fun setPricingList(context: Context, pricingList: ArrayList<Pricing>){
        this.context = context
        this.pricingList = pricingList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PricingViewHolder {
        return PricingViewHolder(PricingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PricingViewHolder, position: Int) {
        if(pricingList[position].name == "Free"){
            holder.binding.pricingPlanTv.text = pricingList[position].name
            holder.binding.pricingFreeMoviesTick.setColorFilter(context.resources.getColor(R.color.active_icon_tick))
            holder.binding.pricingRechargeBtn.text = "Select"
        }
        if(pricingList[position].name == "Monthly"){
            holder.binding.pricingPlanTv.text = pricingList[position].name
            holder.binding.pricingFreeMoviesTick.setColorFilter(context.resources.getColor(R.color.active_icon_tick))
            holder.binding.pricingLiveTvTick.setColorFilter(context.resources.getColor(R.color.active_icon_tick))
            holder.binding.pricingPrimeMoviesTick.setColorFilter(context.resources.getColor(R.color.active_icon_tick))
            holder.binding.pricingRechargeBtn.text = "Pay Rs.199"
        }
        if(pricingList[position].name == "Yearly"){
            holder.binding.pricingPlanTv.text = pricingList[position].name
            holder.binding.pricingFreeMoviesTick.setColorFilter(context.resources.getColor(R.color.active_icon_tick))
            holder.binding.pricingLiveTvTick.setColorFilter(context.resources.getColor(R.color.active_icon_tick))
            holder.binding.pricingPrimeMoviesTick.setColorFilter(context.resources.getColor(R.color.active_icon_tick))
            holder.binding.pricingRequestMoviesTick.setColorFilter(context.resources.getColor(R.color.active_icon_tick))
            holder.binding.pricingRechargeBtn.text = "Pay Rs.599"
        }
    }

    override fun getItemCount(): Int {
        return pricingList.size
    }

    inner class PricingViewHolder(val binding: PricingItemBinding) : RecyclerView.ViewHolder(binding.root)

}