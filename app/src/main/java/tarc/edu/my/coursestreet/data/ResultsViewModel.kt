package tarc.edu.my.coursestreet.data

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ResultsViewModel: ViewModel() {
    private val col = Firebase.firestore.collection("users")
}