package com.shivamjha.adminsnackhungrys.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivamjha.adminsnackhungrys.databinding.PendingOrderDetailsBinding

class PendingOrderAdapter(private val customerNames:ArrayList<String>,
                          private val qnt:ArrayList<String>,
                          private val foodImage:ArrayList<Int>
    ): RecyclerView.Adapter<PendingOrderAdapter.PendingOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingOrderViewHolder {
        return PendingOrderViewHolder(PendingOrderDetailsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return customerNames.size
    }

    override fun onBindViewHolder(holder: PendingOrderViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class PendingOrderViewHolder(private var binding:PendingOrderDetailsBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            binding.apply {
                customerName.text = customerNames[position]
                itemQunatity.text = qnt[position]
                cartImg.setImageResource(foodImage[position])
            }

        }

    }
}

