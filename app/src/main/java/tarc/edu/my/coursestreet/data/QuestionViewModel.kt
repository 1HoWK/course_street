package tarc.edu.my.coursestreet.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class QuestionViewModel: ViewModel(){
    private val col = Firebase.firestore.collection("questions")
    private val questions = MutableLiveData<List<Questions>>()

    init {
        col.addSnapshotListener { snap, _ -> questions.value = snap?.toObjects()  }
    }

    fun getAll() = questions

    fun setQuestions(q: Questions){
        col.document().set(q)
    }
}