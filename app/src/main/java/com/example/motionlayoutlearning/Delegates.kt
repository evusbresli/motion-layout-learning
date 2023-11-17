package com.example.motionlayoutlearning

import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.motionlayoutlearning.databinding.ItemPagerPersonBinding
import com.example.motionlayoutlearning.databinding.ItemPersonBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun pagerItemDelegate(
    addHook: (layout: MotionLayout) -> Unit,
    removeHook: (layout: MotionLayout) -> Unit
) = adapterDelegateViewBinding<String, String, ItemPagerPersonBinding>(
    { layoutInflater, parent -> ItemPagerPersonBinding.inflate(layoutInflater, parent, false) }
) {

    bind {
        binding.textNameLarge.text = item
        binding.textNameSmall.text = item
    }

    onViewAttachedToWindow { addHook(binding.root) }

    onViewDetachedFromWindow { removeHook(binding.root) }
}

fun testItemDelegate(
    onClick: (position: Int) -> Unit
) = adapterDelegateViewBinding<String, String, ItemPersonBinding>(
    { layoutInflater, parent -> ItemPersonBinding.inflate(layoutInflater, parent, false) }
) {
    bind {
        binding.labelInfo.text = item
        binding.root.setOnClickListener { onClick(bindingAdapterPosition) }
    }
}