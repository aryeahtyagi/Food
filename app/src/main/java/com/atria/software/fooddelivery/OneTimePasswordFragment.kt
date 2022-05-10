package com.atria.software.fooddelivery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.atria.software.fooddelivery.databinding.FragmentOneTimePasswordBinding
import com.atria.software.fooddelivery.viewmodel.OneTimeViewModel
import com.atria.software.fooddelivery.viewmodel.OneTimeViewModelFactory


class OneTimePasswordFragment : Fragment() {

    private lateinit var binding: FragmentOneTimePasswordBinding
    private lateinit var model: OneTimeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOneTimePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(this,OneTimeViewModelFactory()).get(OneTimeViewModel::class.java)
        binding.button.setOnClickListener{
            model.verifyOtp(requireActivity(),binding.otpview.text.toString())
        }
        model.onVerify.observe(viewLifecycleOwner){
            if(it ==1 ){
                findNavController().navigate(R.id.action_oneTimePasswordFragment_to_homeFragment)
            }
        }
    }


}