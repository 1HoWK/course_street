package tarc.edu.my.coursestreet.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class MatchedUniViewModel: ViewModel() {
    private val col_uni = Firebase.firestore.collection("Universities")
    private val col_subjects = Firebase.firestore.collection("Subjects")
    private val uni  = MutableLiveData<List<Universities>>()

    init {
        col_uni.addSnapshotListener { snap, _ -> uni.value = snap?.toObjects() }
    }

    fun getAllUni() = uni

}