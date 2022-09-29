package tarc.edu.my.coursestreet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import tarc.edu.my.coursestreet.databinding.FragmentHomeBinding

class homeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val nav by lazy { findNavController() }
    private val vm: EventViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.nextPicEvent.setOnClickListener{ next() }
        binding.prevPicEvent.setOnClickListener { prev() }

        // Recycler view and Observe live data
        val adapter = EventAdapter()
        binding.eventRv.adapter = adapter
        vm.eventPhoto.observe(viewLifecycleOwner){
            eventPhoto -> adapter.submitList(eventPhoto)
        }

        //snapHelper
        val snap = LinearSnapHelper()
        snap.attachToRecyclerView(binding.eventRv)


        return binding.root
    }

    private fun getCount(): Int {
        return binding.eventRv.adapter?.itemCount ?: 0
    }

    private fun getPosition(): Int {
        val mgr = binding.eventRv.layoutManager as LinearLayoutManager
        return mgr.findFirstVisibleItemPosition()
    }

    private fun prev() {
        val pos = getPosition() - 1
        if (pos >= 0)
            binding.eventRv.smoothScrollToPosition(pos)
    }

    private fun next() {
        val pos = getPosition() + 1
        if (pos < getCount())
            binding.eventRv.smoothScrollToPosition(pos)
    }

}