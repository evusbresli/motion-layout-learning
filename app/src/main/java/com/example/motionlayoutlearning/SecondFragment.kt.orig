package com.example.motionlayoutlearning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.motionlayoutlearning.databinding.FragmentSecondBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

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
        ListDelegationAdapter(pagerItemDelegate(::addMotionLayout) { removeMotionLayout() })
            .apply { items = pagerItems }

    private var motionLayoutHook: MotionLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.setTransitionListener(this)
        binding.recyclerItems.adapter = testAdapter
        binding.pagerPersons.adapter = pagerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onTransitionStarted(motionLayout: MotionLayout, startId: Int, endId: Int) =
        updateMotionLayoutHook(motionLayout)

    override fun onTransitionChange(
        motionLayout: MotionLayout,
        startId: Int,
        endId: Int,
        progress: Float
    ) = updateMotionLayoutHook(motionLayout)

    override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) =
        updateMotionLayoutHook(motionLayout)

    override fun onTransitionTrigger(
        motionLayout: MotionLayout,
        triggerId: Int,
        positive: Boolean,
        progress: Float
    ) = updateMotionLayoutHook(motionLayout)

    private fun updateMotionLayoutHook(motionLayout: MotionLayout) {
        motionLayoutHook?.progress = motionLayout.progress
    }

    private fun addMotionLayout(motionLayout: MotionLayout) {
        motionLayout.progress = binding.root.progress
        motionLayoutHook = motionLayout
    }

    private fun removeMotionLayout() {
        motionLayoutHook = null
    }

    private fun navigateToItem(position: Int) {
        childFragmentManager.beginTransaction()
            .replace(
                binding.frameAudioPlayer.id,
                SecondItemFragment::class.java,
                bundleOf("position" to position),
                SecondItemFragment.TAG
            )
            .commit()
    }
}