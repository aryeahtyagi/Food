package com.atria.software.fooddelivery.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.atria.software.Instances
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OneTimeViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    val onVerify : MutableLiveData<Int> = MutableLiveData(0)

    fun verifyOtp(activity:Activity,otp:String){
        val credentials = PhoneAuthProvider.getCredential(Instances.verification_id,otp)
        signInWithPhoneAuthCredential(activity,credentials)
    }

    private fun signInWithPhoneAuthCredential(activity: Activity, credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = task.result?.user
                    onVerify.postValue(1)
                } else {
                    // Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }


}

class OneTimeViewModelFactory:ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OneTimeViewModel() as T
    }

}