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
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.data.AuthViewModel
import tarc.edu.my.coursestreet.data.User
import tarc.edu.my.coursestreet.databinding.FragmentRegisterBinding
import tarc.edu.my.coursestreet.util.cropToBlob
import tarc.edu.my.coursestreet.util.errorDialog


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val nav by lazy { findNavController() }
    private val vm: AuthViewModel by activityViewModels()

    // TODO: Get content launcher
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            binding.imgPhoto.setImageURI(it.data?.data)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        reset()
        binding.imgPhoto.setOnClickListener  { select() }
        binding.btnReset.setOnClickListener  { reset()  }
        binding.btnRegister.setOnClickListener { register() }

        return binding.root
    }

    private fun select() {
        // TODO: Select file
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        launcher.launch(intent)

    }

    private fun reset() {
        binding.edtRegName.text.clear()
        binding.edtRegEmail.text.clear()
        binding.edtRegPassword.text.clear()
        binding.edtConfirmPass.text.clear()
        binding.imgPhoto.setImageDrawable(null)
        binding.edtRegName.requestFocus()
    }

    private fun register() {
        val user = User(
            name= binding.edtRegName.text.toString().trim(),
            email = binding.edtRegEmail.text.toString().trim(),
            password  = binding.edtRegPassword.text.toString().trim(),
            credit = 0,
            photo = binding.imgPhoto.cropToBlob(300,300),
            // TODO: Photo

        )

        val err = vm.validate(user,binding.edtConfirmPass.text.toString().trim())
        if (err != "") {
            errorDialog(err)
            return
        }

        vm.set(user)
        Toast.makeText(context, "Registered successfully", Toast.LENGTH_SHORT).show()
        nav.navigateUp()
    }


}