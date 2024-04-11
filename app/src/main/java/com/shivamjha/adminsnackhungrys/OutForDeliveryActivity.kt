package com.shivamjha.adminsnackhungrys

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivamjha.adminsnackhungrys.adapter.DeliveryAdapter
import com.shivamjha.adminsnackhungrys.databinding.ActivityOutForDeliveryBinding

class OutForDeliveryActivity : AppCompatActivity() {
    private lateinit var binding:ActivityOutForDeliveryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_out_for_delivery)

        binding.back.setOnClickListener {
            finish()
        }

        val customerName = arrayListOf("John Doe"," Jans smith","Mike Janson")
        val moneyStatus = arrayListOf("received","notReceived","pending")

        val adapter = DeliveryAdapter(customerName,moneyStatus)
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this)
    }
}