package tarc.edu.my.coursestreet.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.data.AuthViewModel
import tarc.edu.my.coursestreet.data.QuestionViewModel
import tarc.edu.my.coursestreet.data.Questions
import tarc.edu.my.coursestreet.data.User
import tarc.edu.my.coursestreet.databinding.FragmentPostQuestionBinding


class PostQuestionFragment : Fragment() {

    private lateinit var binding: FragmentPostQuestionBinding
    private val nav by lazy { findNavController() }
    private val user: AuthViewModel by activityViewModels()
    private val qvm: QuestionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPostQuestionBinding.inflate(inflater,container,false)

        binding.postBtn.setOnClickListener {
            val title: String = binding.editTitle.text.toString()
            val question: String = binding.editQuestion.text.toString()
            val userID = user.getUserID()

            val questionSet = Questions(
                id = "",
                user = "$userID",
                title = "$title",
                question = "$question",
                answered = false
            )
            qvm.setQuestions(questionSet)
            Toast.makeText(context, "Posted Question!", Toast.LENGTH_SHORT).show()
            nav.navigateUp()

        }

        return binding.root
    }


}