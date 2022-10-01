package tarc.edu.my.coursestreet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.Blob
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.data.AuthViewModel
import tarc.edu.my.coursestreet.databinding.FragmentLoginBinding
import tarc.edu.my.coursestreet.databinding.FragmentProfileBinding
import tarc.edu.my.coursestreet.databinding.FragmentProfileEditBinding
import tarc.edu.my.coursestreet.util.toBitmap


class profileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val nav by lazy { findNavController() }
    private val auth: AuthViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        auth.getUserLiveData().observe(viewLifecycleOwner) { user ->
            binding.profileTextName.text = user?.name.toString()
            binding.textEmailProfile1.text = user?.email.toString()
            if (user?.photo!= Blob.fromBytes(ByteArray(0))){
                binding.profilePhoto.setImageBitmap(user?.photo?.toBitmap())
            }
        }
        binding.btnEditPassword.setOnClickListener { nav.navigate(R.id.profileEditPasswordFragment) }
        binding.btnEdit.setOnClickListener { nav.navigate(R.id.profileEditFragment) }

        return binding.root
    }


}