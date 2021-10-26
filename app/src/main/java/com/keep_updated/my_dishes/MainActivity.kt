package com.keep_updated.my_dishes

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.keep_updated.my_dishes.databinding.ActivityMainBinding
import com.keep_updated.my_dishes.databinding.AlertDialogViewBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: DishAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        adapter = DishAdapter {
            mainViewModel.deleteDish(it)
        }

        initRecycleView()

        mainViewModel.dishList.observe(this) {
            adapter.setDishList(it)
        }

        binding.addDish.setOnClickListener {
            showDialogBox()
        }

    }

    private fun showDialogBox() {
        val alertDialogLayout = AlertDialogViewBinding.inflate(layoutInflater)

        AlertDialog.Builder(this)
            .setTitle("Add New Dish")
            .setView(alertDialogLayout.root)
            .setPositiveButton("Add") { _, _ ->
                val dish = Dish(
                    alertDialogLayout.addTitle.text.toString(),
                    alertDialogLayout.addDescription.text.toString()
                )
                mainViewModel.addDish(dish)
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .show()
        alertDialogLayout.addTitle.setText("")
        alertDialogLayout.addDescription.setText("")

    }

    private fun initRecycleView() {
        with(binding.rvDishes)
        {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = this@MainActivity.adapter
        }
    }


}