package com.one.vision

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
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
        pricingList.apply {
            add(Pricing("Free","0"))
            add(Pricing("Monthly","199"))
            add(Pricing("Yearly","599"))
        }
        setPricingList()
    }

    private fun init(){
        pricingAdapter = PricingAdapter()
        pricingItemDecoration = PricingItemDecoration(this.requireContext())
    }

    private fun setPricingList() {
        pricingAdapter.setPricingList(this.requireContext(),pricingList)
        binding.pricingRv.apply {
            addItemDecoration(pricingItemDecoration)
            adapter = pricingAdapter
            layoutManager = LinearLayoutManager(this@PricingBottomSheet.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

}