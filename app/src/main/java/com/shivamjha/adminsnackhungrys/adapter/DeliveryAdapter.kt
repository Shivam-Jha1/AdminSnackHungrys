package com.shivamjha.adminsnackhungrys.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivamjha.adminsnackhungrys.databinding.DeliveryItemBinding

class DeliveryAdapter(private val customerNames:ArrayList<String>,
                      private val moneyStatus:ArrayList<String>):
    RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        return DeliveryViewHolder(
            DeliveryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return customerNames.size
    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class DeliveryViewHolder(private val binding: DeliveryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                name.text = customerNames[position]
                paymentStatus.text = moneyStatus[position]

                val colourMap = mapOf(
                    "received" to Color.GREEN, "notReceived" to Color.RED, "pending" to Color.GRAY
                )
                paymentStatus.setTextColor(colourMap[moneyStatus[position]]?:Color.BLACK)
                orderIndicator.backgroundTintList = ColorStateList.valueOf(colourMap[moneyStatus[position]]?:Color.BLACK)
            }
        }
    }
}