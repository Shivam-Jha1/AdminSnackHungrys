package com.shivamjha.adminsnackhungrys.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.shivamjha.adminsnackhungrys.databinding.AllItemBinding
import com.shivamjha.adminsnackhungrys.model.AllMenu

class MenuItemAdapter(
    private val context: Context,
    private val menuList: ArrayList<AllMenu>,
    databaseRefernce: DatabaseReference
)
    :RecyclerView.Adapter<MenuItemAdapter.AllItemViewHolder>(){

        private val itemQuantity = IntArray(menuList.size){1}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllItemViewHolder {
            return  AllItemViewHolder(AllItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }

        override fun onBindViewHolder(holder: AllItemViewHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount(): Int {
            return menuList.size
        }


        inner class AllItemViewHolder(private var binding: AllItemBinding): RecyclerView.ViewHolder(binding.root) {
            fun bind(position: Int) {

                binding.apply {
                    val quantity = itemQuantity[position]
                    val menuItem = menuList[position]
                    val uriString:String? = menuItem.foodImage
                    val uri:Uri? = Uri.parse(uriString)
                    foodName.text = menuItem.foodName
                    foodPrice.text = menuItem.foodPrice
                    Glide.with(context).load(uri).into(cartImg)
                    qntBtn.text = quantity.toString()

                    minusBtn.setOnClickListener {
                        decreaseQnt(position)
                    }
                    plusBtn.setOnClickListener {
                        increaseQnt(position)
                    }
                    delBtn.setOnClickListener {
                        val itemPosition = adapterPosition
                        if(itemPosition!= RecyclerView.NO_POSITION) {
                            deleteQnt(itemPosition)
                        }
                    }
                }
            }
            private fun decreaseQnt(position: Int){
                if(itemQuantity[position]>1){
                    itemQuantity[position]--
                    binding.qntBtn.text = itemQuantity[position].toString()
                }
            }
            private fun increaseQnt(position: Int){
                if(itemQuantity[position]<10){
                    itemQuantity[position]++
                    binding.qntBtn.text = itemQuantity[position].toString()
                }
            }
            private fun deleteQnt(position: Int){
                menuList.removeAt(position)
                menuList.removeAt(position)
                menuList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position,menuList.size)
            }
        }
    }