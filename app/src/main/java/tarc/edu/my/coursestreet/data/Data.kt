package tarc.edu.my.coursestreet.data

import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

data class User(
    @DocumentId
    var id      : String = "",
    var email   : String = "",
    var password: String = "",
    var name    : String = "",
    var credit  : Int = 0,
    var photo   : Blob = Blob.fromBytes(ByteArray(0)),
)

data class Questions(
    @DocumentId
    var id      : String = "",
    var user    : String = "",
    var title   : String = "",
    var question: String = "",
    var date    : Date = Date(),
    var answered: Boolean = false
)

val USERS = Firebase.firestore.collection("users")