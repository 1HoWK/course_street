package tarc.edu.my.coursestreet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.databinding.FragmentCheckQuestionBinding


class CheckQuestionFragment : Fragment() {

    private lateinit var binding: FragmentCheckQuestionBinding
    private val nav by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckQuestionBinding.inflate(inflater,container,false)


        return binding.root
    }


}