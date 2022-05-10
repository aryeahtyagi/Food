package com.atria.software.fooddelivery

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.atria.software.fooddelivery.databinding.FragmentLoginBinding
import com.atria.software.fooddelivery.viewmodel.LoginFactoryViewModel
import com.atria.software.fooddelivery.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber


class LoginFragment : Fragment() {

    private val auth = FirebaseAuth.getInstance()
    private lateinit var loginViewModel : LoginViewModel
    private lateinit var loginBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        loginBinding = FragmentLoginBinding.inflate(inflater,container,false)
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(auth.currentUser!=null){
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        loginViewModel = ViewModelProvider(this,LoginFactoryViewModel()).get(LoginViewModel::class.java)
        loginBinding.button.setOnClickListener{
            val phoneNumber = loginBinding.phoneNumberEditText.text.toString()
            if(activity!=null){
                loginViewModel.sendOtpToNumber(requireActivity(),phoneNumber)
            }
        }
        loginViewModel.onVerificationComplete.observe(viewLifecycleOwner){
            Timber.d(it.toString())
            if(it == 1){
                // verification is completed
                Toast.makeText(activity, "done", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_oneTimePasswordFragment)
            }
        }

    }

}