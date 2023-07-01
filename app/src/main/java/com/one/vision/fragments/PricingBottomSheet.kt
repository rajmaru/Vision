package com.one.vision.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.one.vision.adapters.PricingAdapter
import com.one.vision.databinding.PricingBottomSheetBinding
import com.one.vision.itemdecoration.PricingItemDecoration
import com.one.vision.models.Pricing

class PricingBottomSheet: BottomSheetDialogFragment() {

    private lateinit var binding: PricingBottomSheetBinding
    private lateinit var pricingAdapter: PricingAdapter
    private var pricingList= ArrayList<Pricing>()
    private lateinit var pricingItemDecoration: PricingItemDecoration

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = PricingBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getPricingData()
        onClick()
    }

    private fun onClick() {

    }

    private fun getPricingData() {
        var activityName = activity?.localClassName
        activityName = activityName?.replace("activities.","")
        if(activityName == "AboutActivity"){
            binding.pricingErrorTitle.visibility = View.GONE
            pricingList.apply {
                add(Pricing("Free","0"))
                add(Pricing("Monthly","199"))
                add(Pricing("Yearly","599"))
            }
            setPricingList()
        }
        if(activityName == "MovieActivity"){
            binding.pricingErrorTitle.visibility = View.VISIBLE
            pricingList.apply {
                add(Pricing("Monthly","199"))
                add(Pricing("Yearly","599"))
            }
            setPricingList()
        }
    }

    private fun init(){
        pricingAdapter = PricingAdapter()
        pricingItemDecoration = PricingItemDecoration(this.requireContext())
    }

    private fun setPricingList() {
        pricingAdapter.setPricingList(this.requireContext(),pricingList)
        binding.pricingViewpager.apply {
            addItemDecoration(pricingItemDecoration)
            adapter = pricingAdapter
            offscreenPageLimit = 1
        }
    }

}