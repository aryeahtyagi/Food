package com.atria.software.fooddelivery

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atria.software.fooddelivery.adapter.Myadapter
import com.atria.software.fooddelivery.data.User
import com.atria.software.fooddelivery.databinding.FragmentMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var myadapter: Myadapter
    private lateinit var db: FirebaseFirestore
    private lateinit var mainfragmentbinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainfragmentbinding = FragmentMainBinding.inflate(inflater, container, false)
        return mainfragmentbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = mainfragmentbinding.recycler1
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()

        EventChangeListener{
            Collections.shuffle(it as ArrayList<User>)
            myadapter = Myadapter(this,it as ArrayList<User>)
//            myadapter = Myadapter(this,userArrayList)
            recyclerView.adapter = myadapter
            myadapter.setOnItemClickListener(object :Myadapter.onItemClickListener{
                override fun onItemClick(position: Int) {

                    var intentt: Intent


                }

            })
        }

    }


    private fun EventChangeListener(ondone:(List<User>)->Unit) {
        db=FirebaseFirestore.getInstance()
        db.collection("hotels")
            .get()
            .addOnSuccessListener {
                val hotels = it.toObjects(User::class.java)
                val raavsab = hotels.first()
                Timber.i(raavsab.category + ": ")
                ondone(hotels)
            }
    }
}
