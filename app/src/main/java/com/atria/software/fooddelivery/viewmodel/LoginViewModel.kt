package com.atria.software.fooddelivery.viewmodel

import android.app.Activity
import android.app.Person
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.atria.software.Instances
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber
import java.util.concurrent.TimeUnit

class LoginViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    val onVerificationComplete : MutableLiveData<Int> = MutableLiveData(-1)
    val onAutoVerify : MutableLiveData<Int> = MutableLiveData(-1)

    fun sendOtpToNumber(activity: Activity, phoneNumber:String){
        
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Timber.d("verification complete")
                signInWithPhoneAuthCredential(activity,p0)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Timber.d(p0.localizedMessage)
                Toast.makeText(activity, "${p0.localizedMessage}", Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                Instances.verification_id = p0
                onVerificationComplete.postValue(1);
            }

        }
        
        
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun checkIfNumberExists(){

    }


    private fun signInWithPhoneAuthCredential(activity: Activity,credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = task.result?.user
                    onAutoVerify.postValue(1)

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

class LoginFactoryViewModel:ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel() as T
    }

}