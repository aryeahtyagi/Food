package com.atria.software.fooddelivery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.atria.software.fooddelivery.databinding.FragmentMainBinding
import com.google.firebase.firestore.auth.User


class MenuFragment : Fragment()  {

    lateinit var  im : ImageView
    lateinit var texthotelsname: TextView
    lateinit var rc: RecyclerView
    lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        im= view.findViewById<ImageView>(R.id.imageViewmenu)
        rc= view.findViewById<RecyclerView>(R.id.rcmenu)
        texthotelsname= view.findViewById<TextView>(R.id.texthotelsname)

        im.setImageURI()
    }


}