package com.atria.software.fooddelivery

import android.media.CamcorderProfile
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.atria.software.fooddelivery.databinding.FragmentCategoryBinding
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment() {

    private lateinit var categoryBinding : FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        categoryBinding = FragmentCategoryBinding.inflate(inflater,container,false)
        return categoryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryBinding.maskOnBtn.setOnClickListener {
            postValue("mask on")
        }

        categoryBinding.maskOffBtn.setOnClickListener{
            postValue("mask off")
        }


    }

    data class CameraValue(
        val id : String = ""
    )

    fun postValue(value:String){
        val cameraValue = CameraValue(value)
        val firebase = FirebaseFirestore.getInstance()
        firebase.collection("python__cv_report_202390402493")
            .document("Xyz Place")
            .set(cameraValue)
            .addOnSuccessListener {
                Toast.makeText(context, "DONE", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "FAILED", Toast.LENGTH_SHORT).show()
            }
    }

}