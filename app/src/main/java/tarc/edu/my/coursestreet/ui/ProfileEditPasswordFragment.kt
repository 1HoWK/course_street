package tarc.edu.my.coursestreet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.Blob
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.data.AuthViewModel
import tarc.edu.my.coursestreet.data.ProfileViewModel
import tarc.edu.my.coursestreet.data.User
import tarc.edu.my.coursestreet.databinding.FragmentProfileEditBinding
import tarc.edu.my.coursestreet.databinding.FragmentProfileEditPasswordBinding
import tarc.edu.my.coursestreet.util.cropToBlob
import tarc.edu.my.coursestreet.util.errorDialog
import tarc.edu.my.coursestreet.util.toBitmap


class ProfileEditPasswordFragment : Fragment() {

    private lateinit var binding: FragmentProfileEditPasswordBinding
    private val nav by lazy { findNavController() }
    private val auth: AuthViewModel by activityViewModels()
    private val profile : ProfileViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileEditPasswordBinding.inflate(inflater, container, false)

        reset()
        binding.btnPasswordReset.setOnClickListener { reset() }
        binding.btnPasswordSave.setOnClickListener { save() }

        return binding.root
    }

    private fun reset(){
        binding.edtProfileNewPassword.text.clear()
        binding.edtProfileOldPassword.text.clear()
        binding.edtProfileConfirmNewPassword.text.clear()

    }

    private fun save(){
        val user = User(
            id = auth.getUser()?.id.toString(),
            email = auth.getUser()?.email.toString(),
            password = auth.getUser()?.password.toString()
        )

        val err = profile.validatePassword(user, binding.edtProfileOldPassword.text.toString().trim(), binding.edtProfileNewPassword.text.toString().trim(),binding.edtProfileConfirmNewPassword.text.toString().trim())
        if(err != ""){
            errorDialog(err)
            return
        }
        if(profile.updatePassword(user,binding.edtProfileNewPassword.text.toString().trim())){
            Toast.makeText(context, "Changed successfully", Toast.LENGTH_SHORT).show()
        }

        nav.navigateUp()
    }


}