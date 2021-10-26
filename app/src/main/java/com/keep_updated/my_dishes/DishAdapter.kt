package com.keep_updated.my_dishes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keep_updated.my_dishes.databinding.ListItemBinding

class DishAdapter(private val onDeleteListener: (dish: Dish) -> Unit) :
    RecyclerView.Adapter<DishAdapter.ViewHolder>() {
    private val dishList = mutableListOf<Dish>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dishList[position])
    }

    override fun getItemCount(): Int = dishList.size


    inner class ViewHolder(private val listItemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root) {

        init {
            listItemBinding.description.isSelected = true
        }

        fun bind(dish: Dish) {
            listItemBinding.title.text = dish.name
            listItemBinding.description.text = dish.description
            listItemBinding.deleteItem.setOnClickListener {
                onDeleteListener(dish)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDishList(newList: MutableList<Dish>) {
        dishList.clear()
        dishList.addAll(newList)
        notifyDataSetChanged()
    }
}