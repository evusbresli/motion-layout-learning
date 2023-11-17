package com.example.motionlayoutlearning

import android.os.*
import android.view.*
import androidx.fragment.app.*
import com.example.motionlayoutlearning.databinding.*

/**
 * A simple [Fragment] subclass
 */
class SecondItemFragment : Fragment() {

    companion object {
        internal const val TAG = "SecondItemFragment"
    }

    private var _binding: FragmentSecondItemBinding? = null

    private val binding get() = _binding!!

    private val position get() = arguments?.getInt("position")!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSecondItemBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.audioName.text = "Song #$position"
    }
}