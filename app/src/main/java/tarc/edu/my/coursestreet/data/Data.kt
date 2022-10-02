package tarc.edu.my.coursestreet.data

import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude
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
    var results : Boolean = false,
    var photo   : Blob = Blob.fromBytes(ByteArray(0)),
)

data class Store(
    @DocumentId
    var id : String = "",
    var itemName : String = "",
    var itemPhoto : Blob = Blob.fromBytes(ByteArray(0)),
    var itemPrice : Int = 0,
)

data class Items(
    @DocumentId
    var id : String = "",
    var user : String = "",
    var itemName : String = "",
    var itemPhoto : Blob = Blob.fromBytes(ByteArray(0)),
    var itemQty : Int = 0
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

data class Reply(
    @DocumentId
    var id      : String = "",
    var belongTo: String = "",
    var user    : String = "",
    var userName: String = "",
    var status  : Boolean = false,
    var comment : String = "",
){
    @get: Exclude
    var question: Questions = Questions()
}

data class Academic(
    @DocumentId
    var id          : String = "",
    var user        : String = "",
    var fieldOfInterest : String = "",
    var qualification : String = "",
    var Subject1    : String = "",
    var Grade1      : String = "",
    var Subject2    : String = "",
    var Grade2      : String = "",
    var Subject3    : String = "",
    var Grade3      : String = "",
    var Subject4    : String = "",
    var Grade4      : String = "",
    var Subject5    : String = "",
    var Grade5      : String = "",
    var Subject6    : String = "",
    var Grade6      : String = "",
    var Subject7    : String = "",
    var Grade7      : String = "",
    var Subject8    : String = "",
    var Grade8      : String = "",
    var Subject9    : String = "",
    var Grade9      : String = "",
    var Subject10    : String = "",
    var Grade10      : String = "",
    var Subject11    : String = "",
    var Grade11      : String = "",
    var Subject12    : String = "",
    var Grade12      : String = "",

    )

val USERS = Firebase.firestore.collection("users")
val STORE = Firebase.firestore.collection("store")
val QUESTIONS = Firebase.firestore.collection("questions")
val REPLY = Firebase.firestore.collection("reply")
val ITEMS = Firebase.firestore.collection("items")
val ACADEMIC = Firebase.firestore.collection("academic_qualifications")