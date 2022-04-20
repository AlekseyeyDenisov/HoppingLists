package com.example.listporxhese.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.listporxhese.databinding.ActivityMainBinding
import com.example.listporxhese.domain.ShopItem
import com.example.listporxhese.presentation.AdapterShopItem.Companion.MAX_POOL_SIZE
import com.example.listporxhese.presentation.AdapterShopItem.Companion.VIEW_TYPE_DISABLED
import com.example.listporxhese.presentation.AdapterShopItem.Companion.VIEW_TYPE_ENABLED

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val shopListAdapter = AdapterShopItem()

    val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel.shopList.observe(this) {
            //shopListAdapter.shopList = it
            shopListAdapter.submitList(it)
        }

        initRecycler(binding.rvShopList)
        clickItem()
        swipedItem()
        setContentView(binding.root)
    }

    private fun swipedItem() {
        val callback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //val item = shopListAdapter.shopList[viewHolder.adapterPosition]
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvShopList)
    }

    private fun clickItem() {
        shopListAdapter.onShopItemClick = object : OnShopItemClick {
            override fun longClickItem(shopItem: ShopItem) {
                //viewModel.deleteShopItem(shopItem)
                viewModel.changeEnableState(shopItem)
            }

            override fun clickItem(shopItem: ShopItem) {
                //viewModel.changeEnableState(shopItem)
            }
        }
    }


    private fun initRecycler(rvShopList: RecyclerView) {
        with(rvShopList) {
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(VIEW_TYPE_ENABLED, MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(VIEW_TYPE_DISABLED, MAX_POOL_SIZE)

        }

    }
}