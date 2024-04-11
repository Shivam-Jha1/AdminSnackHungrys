package com.shivamjha.adminsnackhungrys

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.shivamjha.adminsnackhungrys.databinding.ActivityAddItemBinding
import com.shivamjha.adminsnackhungrys.model.AllMenu

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemBinding
    private lateinit var foodName: String
    private lateinit var foodPrice: String
    private lateinit var foodDescription: String
    private lateinit var foodIngredients: String
    private var foodImageUri: Uri? = null

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_item)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.addItem.setOnClickListener {
            foodName = binding.foodName.text.toString().trim()
            foodPrice = binding.foodPrice.text.toString().trim()
            foodDescription = binding.longDesc.text.toString().trim()
            foodIngredients = binding.ingredients.text.toString().trim()

            if (foodName.isBlank() || foodPrice.isBlank() || foodDescription.isBlank() || foodIngredients.isBlank() || foodImageUri == null) {
                Toast.makeText(this, "Please fill all fields and select an image", Toast.LENGTH_LONG).show()
            } else {
                uploadData()
            }
        }

        binding.selectImage.setOnClickListener {
            pickImage.launch("image/*")
        }

        binding.back.setOnClickListener {
            finish()
        }
    }

    private fun uploadData() {
        val menuRef = database.getReference("menu")
        val newItemKey = menuRef.push().key

        val storageRef = FirebaseStorage.getInstance().reference
        val imageRef = storageRef.child("menu_images${newItemKey}.jpg")
        val uploadTask = imageRef.putFile(foodImageUri!!)

        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener { downloadurl ->
                val newItem = AllMenu(
                    foodName = foodName,
                    foodPrice = foodPrice,
                    foodDescription = foodDescription,
                    foodIngredient = foodIngredients,
                    foodImage = downloadurl.toString()
                )
                newItemKey?.let { key ->
                    menuRef.child(key).setValue(newItem).addOnSuccessListener {
                        Toast.makeText(
                            this,
                            "Data Uploaded Successfully",
                            Toast.LENGTH_LONG
                        ).show()
                        finish()
                    }
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Image upload failed", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Upload failed", Toast.LENGTH_LONG).show()
        }
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            binding.foodImage.setImageURI(uri)
            foodImageUri = uri
        }
    }
}