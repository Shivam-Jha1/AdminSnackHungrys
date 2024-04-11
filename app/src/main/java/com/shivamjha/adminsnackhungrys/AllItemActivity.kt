package com.shivamjha.adminsnackhungrys

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.shivamjha.adminsnackhungrys.adapter.MenuItemAdapter
import com.shivamjha.adminsnackhungrys.databinding.ActivityAllItemBinding
import com.shivamjha.adminsnackhungrys.model.AllMenu

class AllItemActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAllItemBinding

    private lateinit var databaseRefernce:DatabaseReference
    private lateinit var database : FirebaseDatabase
    private var menuItems:ArrayList<AllMenu> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_all_item)

        databaseRefernce = FirebaseDatabase.getInstance().reference
        retriveMenuItem()

        binding.back.setOnClickListener {
            finish()
        }

    }

    private fun retriveMenuItem() {
        database = FirebaseDatabase.getInstance()
        val foodRef:DatabaseReference = database.reference.child("menu")

        foodRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                menuItems.clear()

                for(foodSnapshot in snapshot.children){
                    val menuItem:AllMenu? = foodSnapshot.getValue(AllMenu::class.java)
                    menuItem?.let {
                        menuItems.add(it)
                    }
                }
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Tag","Error: ${error.message}")
            }
        })
    }

    private fun setAdapter() {
        val adapter = MenuItemAdapter(this,menuItems,databaseRefernce)
        binding.rv.layoutManager = LinearLayoutManager(this)

        binding.rv.adapter = adapter
    }
}