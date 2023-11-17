package com.example.motionlayoutlearning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.motionlayoutlearning.databinding.FragmentFirstBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ListDelegationAdapter(testItemDelegate { findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment) })
            .apply { items = List(15) { "Test info $it" } }
            .let { adapter -> binding.recyclerItems.adapter = adapter }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}