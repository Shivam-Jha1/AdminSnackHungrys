package com.shivamjha.adminsnackhungrys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.shivamjha.adminsnackhungrys.databinding.ActivitySignUpBinding
import com.shivamjha.adminsnackhungrys.model.UserModel

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignUpBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var userName:String
    private lateinit var nameOfResturant:String
    private lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)

        auth = Firebase.auth
        database = Firebase.database.reference

        val locationList = arrayOf("Muzaffarpur","Patna","Samastipur","Darbhanga","Gaya","Motihari","Baruni","Hajipur")
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,locationList)
        val autoCompleteTextView = binding.locationList
        autoCompleteTextView.setAdapter(adapter)

        binding.signBtn.setOnClickListener {

            email = binding.userEmail.text.toString().trim()
            password = binding.userPassword.text.toString().trim()
            userName = binding.ownerName.text.toString()
            nameOfResturant = binding.resturantName.text.toString()

            if(email.isBlank() || nameOfResturant.isBlank() || userName.isBlank() || password.isBlank()){
                Toast.makeText(this,"Fill All details",Toast.LENGTH_LONG).show()
            }

            else {
                createAccount(email,password)
            }
        }

    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
            if(task.isSuccessful){
                Toast.makeText(this,"Account Created Successfully",Toast.LENGTH_LONG).show()
                saveUserData()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this,"Account Creation Failed",Toast.LENGTH_LONG).show()
                Log.d("Account","createAccount: Failure",task.exception)
            }
        }
    }

    private fun saveUserData() {
        email = binding.userEmail.text.toString().trim()
        password = binding.userPassword.text.toString().trim()
        userName = binding.ownerName.text.toString()
        nameOfResturant = binding.resturantName.text.toString()
        val user = UserModel(userName,nameOfResturant,email,password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        database.child("user").child(userId).setValue(user)
    }
}