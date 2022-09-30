package tarc.edu.my.coursestreet.data

import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class User(
    @DocumentId
    var id      : String = "",
    var email   : String = "",
    var password: String = "",
    var name    : String = "",
    var photo   : Blob = Blob.fromBytes(ByteArray(0)),
)

val USERS = Firebase.firestore.collection("users")