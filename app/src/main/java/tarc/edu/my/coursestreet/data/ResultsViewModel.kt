package tarc.edu.my.coursestreet.data

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class ResultsViewModel: ViewModel() {
    private val col_academic = Firebase.firestore.collection("academic_qualifications")

    private val users = MutableLiveData<List<User>>()
    private val col = Firebase.firestore.collection("users")

    init{
        col.addSnapshotListener { snap, _ -> users.value = snap?.toObjects() }
    } // TODO


    fun update(user:User){
        col.document(user.id).update("results",true)
    }

    fun upload(academic: Academic){
        col_academic.document().set(academic)
    }

    fun validate(academic: Academic): String {
        var e = ""

        e += if (academic.fieldOfInterest == "-") "- Field Of Interest is required.\n"
        else ""

        e += if (academic.qualification == "-") "- Qualification of Study is required.\n"
        else ""



        return e
    }

}