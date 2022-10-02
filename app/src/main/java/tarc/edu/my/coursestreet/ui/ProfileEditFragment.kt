package tarc.edu.my.coursestreet.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.Blob
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.data.AuthViewModel
import tarc.edu.my.coursestreet.data.ProfileViewModel
import tarc.edu.my.coursestreet.data.User
import tarc.edu.my.coursestreet.databinding.FragmentProfileBinding
import tarc.edu.my.coursestreet.databinding.FragmentProfileEditBinding
import tarc.edu.my.coursestreet.util.cropToBlob
import tarc.edu.my.coursestreet.util.errorDialog
import tarc.edu.my.coursestreet.util.toBitmap


class ProfileEditFragment : Fragment() {

    private lateinit var binding: FragmentProfileEditBinding
    private val nav by lazy { findNavController() }
    private val auth: AuthViewModel by activityViewModels()
    private val profile : ProfileViewModel by activityViewModels()

    // TODO: Get content launcher
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            binding.editImgPhoto.setImageURI(it.data?.data)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileEditBinding.inflate(inflater, container, false)

        reset()
        binding.editImgPhoto.setOnClickListener  { select() }
        binding.btnProfileReset.setOnClickListener { reset() }
        binding.btnProfileSave.setOnClickListener { save() }

        return binding.root
    }

    private fun select() {
        // TODO: Select file
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        launcher.launch(intent)

    }

    private fun reset(){
        binding.edtProfileName.setText(auth.getUser()?.name.toString())
        binding.txtProfileEmail.text = auth.getUser()?.email.toString()
        if (auth.getUser()?.photo!= Blob.fromBytes(ByteArray(0))){
            binding.editImgPhoto.setImageBitmap(auth.getUser()?.photo?.toBitmap())
        }
    }

    private fun save(){
        val user = User(
            id = auth.getUser()?.id.toString(),
            email = auth.getUser()?.email.toString(),
            name = binding.edtProfileName.text.toString().trim(),
            photo = binding.editImgPhoto.cropToBlob(300,300)
        )

        val err = profile.validateProfile(user)
        if(err != ""){
            errorDialog(err)
            return
        }
        if(profile.update(user)){
            Toast.makeText(context, "Edited successfully", Toast.LENGTH_SHORT).show()
        }
        nav.navigateUp()
    }


}