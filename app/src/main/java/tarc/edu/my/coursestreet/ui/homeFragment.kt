package tarc.edu.my.coursestreet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.data.EventViewModel
import tarc.edu.my.coursestreet.data.MatchedUniViewModel
import tarc.edu.my.coursestreet.databinding.FragmentHomeBinding
import tarc.edu.my.coursestreet.util.EventAdapter
import tarc.edu.my.coursestreet.util.MatchUniAdapter

class homeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val nav by lazy { findNavController() }
    private val vm: EventViewModel by activityViewModels()
    private val univm: MatchedUniViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)



        val uniListAdapter = MatchUniAdapter() { holder, data ->
            holder.root.setOnClickListener{
            }
        }

        binding.matchedUniRV.adapter = uniListAdapter

        univm.getAllUni().observe(viewLifecycleOwner) { uni ->
            uniListAdapter.submitList(uni)
        }

        return binding.root
    }


}