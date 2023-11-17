package com.example.motionlayoutlearning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
import androidx.constraintlayout.widget.ConstraintSet.Motion
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.motionlayoutlearning.databinding.FragmentSecondBinding
import com.example.motionlayoutlearning.databinding.ItemPagerPersonBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import java.lang.ref.WeakReference

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), TransitionListener {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val testItems = List(10) { "Test info $it" }
    private val pagerItems = listOf("John Doe", "Ivan Ivanov", "John McClain", "Andrew Ryan")
    private val testAdapter = ListDelegationAdapter(testItemDelegate(::navigateToItem))
        .apply { items = testItems }
    private val pagerAdapter =
        ListDelegationAdapter(pagerItemDelegate(::addMotionLayout, ::removeMotionLayout))
            .apply { items = pagerItems }

    private val motionLayouts: MutableList<MotionLayout> = mutableListOf()

    private val motionLayoutReference: WeakReference<MotionLayout>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMotionLayoutListener()

        binding.recyclerItems.adapter = testAdapter
        binding.pagerPersons.adapter = pagerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onTransitionStarted(motionLayout: MotionLayout, startId: Int, endId: Int) =
        updateNestedMotionLayout(motionLayout)

    override fun onTransitionChange(
        motionLayout: MotionLayout,
        startId: Int,
        endId: Int,
        progress: Float
    ) =
        updateNestedMotionLayout(motionLayout)

    override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) =
        updateNestedMotionLayout(motionLayout)

    override fun onTransitionTrigger(
        motionLayout: MotionLayout,
        triggerId: Int,
        positive: Boolean,
        progress: Float
    ) =
        updateNestedMotionLayout(motionLayout)

    private fun setupMotionLayoutListener() {
        binding.root.setTransitionListener(this@SecondFragment)
//        pagerPersons.root.setTransitionListener(this@SecondFragment)
    }

    private fun updateNestedMotionLayout(motionLayout: MotionLayout) = with(binding) {
        if (motionLayout.id == root.id) {
//            pagerPersons.root.progress = motionLayout.progress
            motionLayouts.forEach { it.progress = motionLayout.progress }
        }
    }

    private fun addMotionLayout(motionLayout: MotionLayout) {
        motionLayout.progress = binding.root.progress
        motionLayouts += motionLayout
    }

    private fun removeMotionLayout(motionLayout: MotionLayout) {
        motionLayouts -= motionLayout
    }

    private fun navigateToItem(position: Int) {
//        childFragmentManager.beginTransaction()
//            .replace(
//                binding.frameAudioPlayer.id,
//                SecondItemFragment::class.java,
//                bundleOf("position" to position),
//                SecondItemFragment.TAG
//            )
//            .commit()
        println(motionLayouts)
    }
}

private class PagerItemsAdapter(
    private val onBindMotionLayout: (layout: MotionLayout) -> Unit,
    private val onAttachMotionLayoutToWindow: (layout: MotionLayout) -> Unit
) : RecyclerView.Adapter<PagerItemViewHolder>() {

    private val items = listOf("John Doe", "Ivan Ivanov", "John McClain")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerItemViewHolder =
        PagerItemViewHolder(
            ItemPagerPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PagerItemViewHolder, position: Int) {
        holder.bind(items[position])
        println("bind")
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        println("attached to recycler")
    }

    override fun onViewRecycled(holder: PagerItemViewHolder) {
        super.onViewRecycled(holder)
        println("recycle")
    }

    override fun onViewAttachedToWindow(holder: PagerItemViewHolder) {
        super.onViewAttachedToWindow(holder)
        println("attached to window")
    }

    override fun getItemCount(): Int = items.count()
}

private class PagerItemViewHolder(private val binding: ItemPagerPersonBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val motionLayout get() = binding.root

    fun bind(name: String) = with(binding) {
        textNameLarge.text = name
        textNameSmall.text = name
    }
}