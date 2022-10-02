package tarc.edu.my.coursestreet.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CommentViewModel: ViewModel() {
    private val col = Firebase.firestore.collection("reply")
    private var replies = listOf<Reply>()
    private var result = MutableLiveData<List<Reply>>()
    private var currentQuestionID = ""

    init {
        viewModelScope.launch {
            val questions = QUESTIONS.get().await().toObjects<Questions>()

            col.addSnapshotListener { snap, _ ->
                if (snap == null) return@addSnapshotListener

                replies = snap.toObjects<Reply>()
                for (r in replies){
                    val question = questions.find {q -> q.id == r.belongTo}!!
                    r.question = question
                }

                updateResult()
            }
        }
    }

    private fun updateResult(){
        var list = replies

        list = list.filter { r ->
            r.question.id == currentQuestionID
        }

        result.value = list
    }

    fun getAllComments() = result

    fun sortComment(questionID: String){
        this.currentQuestionID = questionID
        updateResult()

    }

    fun setReply(r: Reply){
        col.document().set(r)
    }

    fun updateStatus(r: String){
        col.document(r).update("status",true)
    }

}