package tarc.edu.my.coursestreet.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {
    private val userLiveData = MutableLiveData<User?>()
    private val users = MutableLiveData<List<User>>()
    private var listener: ListenerRegistration? = null
    private val col = Firebase.firestore.collection("users")

    init{
        col.addSnapshotListener { snap, _ -> users.value = snap?.toObjects() }
    } // TODO

    // Remove snapshot listener when view model is destroyed
    override fun onCleared() {
        super.onCleared()
        listener?.remove()
    }

    // Return observable live data
    fun getUserLiveData(): LiveData<User?> {
        return userLiveData
    }

    // Return user from live data
    fun getUser(): User? {
        return userLiveData.value
    }

    fun getUserID(): String {
        return userLiveData.value?.id ?: ""
    }

    fun set(user: User) {
        // TODO
        col.document().set(user)
    }


    // TODO(1): Login
    suspend fun login(ctx: Context, email: String, password: String, remember: Boolean = true): Boolean {
        // TODO(1A): Get the user record with matching email + password
        //           Return false is no matching found
        var user = USERS
            //.whereEqualTo(FieldPath.documentId(),"xxx")
            .whereEqualTo("email",email)
            .whereEqualTo("password", password)
            .get()
            .await()
            .toObjects<User>()
            .firstOrNull() ?: return false


        // TODO(1B): Setup snapshot listener
        //           Update live data -> user
        listener?.remove()
        listener = USERS.document(user.id).addSnapshotListener { doc, _ ->
            userLiveData.value = doc?.toObject()

        }



        // TODO(6A): Handle remember-me -> add shared preferences
        if (remember){
            getPreferences(ctx)
                .edit()
                .putString("email",email)
                .putString("password", password)
                .apply()

        }

        return true
    }

    // TODO(2): Logout
    fun logout(ctx: Context) {
        // TODO(2A): Remove snapshot listener
        //           Update live data -> null
        listener?.remove()
        userLiveData.value = null


        // TODO(6B): Handle remember-me -> clear shared preferences
        getPreferences(ctx).edit().clear().apply()
        //getPreferences(ctx).edit().remove("password").apply()

    }

    // TODO(6): Get shared preferences
    private fun getPreferences(ctx: Context): SharedPreferences {
        //return ctx.getSharedPreferences("AUTH", Context.MODE_PRIVATE)

        return EncryptedSharedPreferences.create(
            "AUTH",
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            ctx,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    // TODO(7): Auto login from shared preferences
    suspend fun loginFromPreferences(ctx: Context) {
        val pref = getPreferences(ctx)
        val email = pref.getString("email",null)
        val password = pref.getString("password", null)
        if (email!=null&&password!=null){
            login(ctx,email,password)
        }

    }

    fun noEmail(ctx:Context): Boolean{
        val pref = getPreferences(ctx)
        val email = pref.getString("email",null)
        return email==null
    }

    private fun emailExists(email: String): Boolean{
        return users.value?.any { user -> user.email == email } ?: false
    }

    fun validate(user: User, confirmPass: String): String {
        var e = ""

        e += if (user.name == "") "- Name is required.\n"
        else if (user.name.length < 3) "- Name is too short (at least 3 letters).\n"
        else ""

        e += if (user.email == "") "- Email is required.\n"
        else if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) "- Email is invalid.\n"
        else if (emailExists(user.email)) "- Email is duplicated.\n"
        else ""

        e += if (user.password == "") "- Password is required.\n"
        else if (user.password.length < 8 ) "- Password must have at least 8 characters, 1 digit, 1 lowercase and 1 uppercase letter.\n"
        else if (user.password.firstOrNull { it.isDigit() } == null) "- Password must have at least 1 digit, 1 lowercase and 1 uppercase letter.\n"
        else if (user.password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) "- Password must have at least 1 digit, 1 lowercase and 1 uppercase letter.\n"
        else if (user.password.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) "- Password must have at least 1 digit, 1 lowercase and 1 uppercase letter.\n"
        else ""

        e += if (user.password != "" && confirmPass != user.password) "- Passwords did not match.\n"
        else ""


        return e
    }

}