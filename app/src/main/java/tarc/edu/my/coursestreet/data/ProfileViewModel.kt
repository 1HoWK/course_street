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

class ProfileViewModel : ViewModel() {
    private val users = MutableLiveData<List<User>>()
    private val userLiveData = MutableLiveData<User?>()
    private val col = Firebase.firestore.collection("users")

    init{
        col.addSnapshotListener { snap, _ -> users.value = snap?.toObjects() }
    } // TODO

    fun set(user:User){
        col.document(user.id).set(user)
    }

    fun update(user:User): Boolean{
        col.document(user.id).update("name",user.name,"photo",user.photo)
        return true
    }

    fun updatePassword(user:User, newPass: String): Boolean{
        if (newPass==""){
            return false
        }else{
            col.document(user.id).update("password",newPass)
            return true
        }
    }


    fun validateProfile(user: User):String{
        var e = ""

        e += if (user.name == "") "- Name is required.\n"
        else if (user.name.length < 3) "- Name is too short (at least 3 letters).\n"
        else ""


        return e
    }

    fun validatePassword(user: User,oldPass:String, pass: String, confirmPass: String):String{
        var e = ""

        e += if (oldPass !="" && oldPass!=user.password) "- Old Password is invalid.\n"
        else ""

        e += if (oldPass=="" && pass!="" ) "- Old Password is required.\n"
        else ""

        e += if (oldPass != "" && oldPass==user.password && pass=="") "- New Password is required.\n"
        else if (oldPass != "" && oldPass==user.password && pass.length < 8 ) "- Password must have at least 8 characters, 1 digit, 1 lowercase and 1 uppercase letter.\n"
        else if (oldPass != "" && oldPass==user.password && pass.firstOrNull { it.isDigit() } == null) "- Password must have at least 1 digit, 1 lowercase and 1 uppercase letter.\n"
        else if (oldPass != "" && oldPass==user.password && pass.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) "- Password must have at least 1 digit, 1 lowercase and 1 uppercase letter.\n"
        else if (oldPass!= "" && oldPass==user.password && pass.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) "- Password must have at least 1 digit, 1 lowercase and 1 uppercase letter.\n"
        else if (oldPass != "" && oldPass==user.password && pass==user.password) "- New Password cannot same with Old Password.\n"
        else ""

        e += if (pass!="" && confirmPass != pass) "- New Passwords did not match.\n"
        else ""

        return e
    }
}