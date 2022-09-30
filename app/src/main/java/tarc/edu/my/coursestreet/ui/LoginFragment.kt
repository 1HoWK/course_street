package tarc.edu.my.coursestreet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import tarc.edu.my.coursestreet.R
import tarc.edu.my.coursestreet.data.AuthViewModel
import tarc.edu.my.coursestreet.databinding.FragmentLoginBinding
import tarc.edu.my.coursestreet.util.errorDialog
import tarc.edu.my.coursestreet.util.hideKeyboard


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val nav by lazy { findNavController() }
    private val auth: AuthViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.btnLogin.setOnClickListener { login() }
        return binding.root
    }

    private fun login() {
        hideKeyboard()

        val ctx      = requireContext()
        val email    = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()
        val remember = binding.chkRemember.isChecked

        // TODO(3): Login -> auth.login(...)
        //          Clear navigation backstack
        lifecycleScope.launch{
            val success = auth.login(ctx, email,password,remember)
            if (success){
                nav.popBackStack(R.id.homeFragment, false)
                nav.navigateUp()
            }else{
                errorDialog("Invalid login credentials.")
            }
        }

    }
}