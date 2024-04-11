package com.shivamjha.adminsnackhungrys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.shivamjha.adminsnackhungrys.databinding.ActivityAdminProfileBinding

class AdminProfileActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAdminProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_admin_profile)


        binding.back.setOnClickListener {
            finish()
        }
        binding.name.isEnabled = false
        binding.gmail.isEnabled = false
        binding.password.isEnabled = false
        binding.contactNo.isEnabled = false
        binding.address.isEnabled = false

        var isEnable = false
        binding.editProfile.setOnClickListener {
            isEnable = !isEnable
            binding.name.isEnabled = isEnable
            binding.gmail.isEnabled = isEnable
            binding.password.isEnabled = isEnable
            binding.contactNo.isEnabled = isEnable
            binding.address.isEnabled = isEnable

            if(isEnable){
                binding.name.requestFocus()
            }
        }
    }
}