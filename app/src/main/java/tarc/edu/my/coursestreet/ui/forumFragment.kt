package tarc.edu.my.coursestreet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.data.EventViewModel
import tarc.edu.my.coursestreet.data.QuestionViewModel
import tarc.edu.my.coursestreet.data.Questions
import tarc.edu.my.coursestreet.databinding.FragmentForumBinding
import tarc.edu.my.coursestreet.util.QuestionAdapter


class forumFragment : Fragment() {

    private lateinit var binding: FragmentForumBinding
    private val nav by lazy { findNavController() }
    private val vm: QuestionViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForumBinding.inflate(inflater,container,false)

        val adapter = QuestionAdapter() { holder, questions ->
            holder.root.setOnClickListener {
                nav.navigate(R.id.checkQuestionFragment, bundleOf("id" to questions.id))
            }

        }

        binding.forumRV.adapter = adapter

//        Firebase.firestore
//            .collection("questions")
//            .get()
//            .addOnSuccessListener { snap ->
//                val list = snap.toObjects<Questions>()
//                adapter.submitList(list)
//            }

        vm.getAll().observe(viewLifecycleOwner) { questions ->
            adapter.submitList(questions)

        }

        binding.postQuestionButton.setOnClickListener {
            nav.navigate(R.id.postQuestionFragment)
        }

        return binding.root
    }




}