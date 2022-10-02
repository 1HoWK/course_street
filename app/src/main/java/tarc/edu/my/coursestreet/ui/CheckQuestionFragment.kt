package tarc.edu.my.coursestreet.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
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
    private val questionText by lazy { arguments?.getString("question","") }
    private val questionTitle by lazy { arguments?.getString("title","") }
    private val cvm : CommentViewModel by activityViewModels()
    private val avm: AuthViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckQuestionBinding.inflate(inflater,container,false)
        binding.replyBtn.isVisible = questionUserID != avm.getUserID()
        binding.questionCheckTitle.text = questionTitle
        binding.questionCheckText.text = questionText
        val currentUserID = avm.getUserID()
        val currentUser = avm.getUser()

        val adapter = CommentAdapter(){ holder, reply ->
            holder.helpfulBtn.setOnClickListener{
                USERS.document(reply.user).update("credit",FieldValue.increment(50))

                cvm.updateStatus(reply.id)
                QUESTIONS.document(reply.belongTo).update("answered", true)
            }
        }

        binding.replyBtn.setOnClickListener {
            val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(context)
            builder.setTitle("Reply")

            val input = EditText(context)
            input.hint = "Answer"
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->

                if (!TextUtils.isEmpty(input.text.toString())){
                    val replyText = input.text.toString()
                    val replySet = Reply(
                        id = "",
                        belongTo = "$questionId",
                        user = currentUserID,
                        userName = "${currentUser!!.name}",
                        status = false,
                        comment = replyText
                    )
                    cvm.setReply(replySet)
                    Toast.makeText(context, "Posted reply!", Toast.LENGTH_SHORT).show()
                }

            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

            builder.show()
        }


        binding.rvComment.adapter = adapter
        questionUserID?.let { adapter.setUserID(it) }
        adapter.setCurrentUserID(currentUserID)
        questionId?.let { cvm.sortComment(it) }
        cvm.getAllComments().observe(viewLifecycleOwner){ reply ->
            adapter.submitList(reply)
        }



        return binding.root
    }


}