package com.shivamjha.adminsnackhungrys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivamjha.adminsnackhungrys.adapter.DeliveryAdapter
import com.shivamjha.adminsnackhungrys.adapter.PendingOrderAdapter
import com.shivamjha.adminsnackhungrys.databinding.ActivityPendingUserBinding

class PendingUserActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPendingUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_pending_user)

        binding.back.setOnClickListener {
            finish()
        }

        val customerName = arrayListOf("John Doe"," Jans smith","Mike Janson")
        val quantity = arrayListOf("3","5","8")
        val foodImg = arrayListOf(R.drawable.banner1,R.drawable.banner1,R.drawable.banner1)

        val adapter = PendingOrderAdapter(customerName,quantity,foodImg)
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this)
    }
}