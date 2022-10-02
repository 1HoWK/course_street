package tarc.edu.my.coursestreet.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.QuerySnapshot
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.data.*
import tarc.edu.my.coursestreet.databinding.FragmentCheckQuestionBinding
import tarc.edu.my.coursestreet.util.CommentAdapter


class CheckQuestionFragment : Fragment() {

    private lateinit var binding: FragmentCheckQuestionBinding
    private val nav by lazy { findNavController() }
    private val questionId by lazy { arguments?.getString("id", "") }
    private val questionUserID by lazy { arguments?.getString("userId", "") }
    private val cvm : CommentViewModel by activityViewModels()
    private val avm: AuthViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckQuestionBinding.inflate(inflater,container,false)
        binding.replyBtn.isVisible = questionUserID != avm.getUserID()



        val adapter = CommentAdapter(){ holder, reply ->
            holder.helpfulBtn.setOnClickListener{
                USERS.document(reply.user).update("credit",FieldValue.increment(50))
                Log.d("State",reply.belongTo)
                cvm.updateStatus(reply.id)
                QUESTIONS.document(reply.belongTo).update("answered", true)
            }
        }


        binding.rvComment.adapter = adapter
        questionUserID?.let { adapter.setUserID(it) }
        adapter.setCurrentUserID(avm.getUserID())
        questionId?.let { cvm.sortComment(it) }
        cvm.getAllComments().observe(viewLifecycleOwner){ reply ->
            adapter.submitList(reply)
        }

        binding.replyBtn.setOnClickListener {

        }

        return binding.root
    }

}