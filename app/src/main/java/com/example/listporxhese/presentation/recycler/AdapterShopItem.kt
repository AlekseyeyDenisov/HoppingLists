package com.example.listporxhese.presentation.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.listporxhese.databinding.ItemShopDisabledBinding
import com.example.listporxhese.databinding.ItemShopEnabledBinding
import com.example.listporxhese.domain.ShopItem

interface OnShopItemClick{
    fun longClickItem(shopItem: ShopItem)
    fun clickItem(shopItem: ShopItem)
}
//class AdapterShopItem() : RecyclerView.Adapter<AdapterShopItem.ViewHolderShopItem>() {
class AdapterShopItem : ListAdapter<ShopItem, AdapterShopItem.ViewHolderShopItem>(ShopItemDiffUtilCallBack()) {

    var onShopItemClick: OnShopItemClick? = null
//    var shopList = listOf<ShopItem>()
//        set(value) {
//            val callback = ShopListDiffUtil(shopList,value)
//            val diffUtil = DiffUtil.calculateDiff(callback)
//            diffUtil.dispatchUpdatesTo(this)
//            field = value
//        }

    var i = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderShopItem {
        Log.d("@@@", "onCreateViewHolder: count ${++i}")
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> {
                ItemShopEnabledBinding.inflate(LayoutInflater.from(parent.context),parent,false)

            }
            VIEW_TYPE_DISABLED -> {
                ItemShopDisabledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
            else -> throw RuntimeException("Unknown view type: $viewType")
        } 

        return ViewHolderShopItem(layout)
    }

    override fun onBindViewHolder(holder: ViewHolderShopItem, position: Int) {
        //val shopItem = shopList[position]
        val shopItem = getItem(position)
        if (shopItem.isEnabled) holder.bindEnabled(shopItem)
        else holder.bindDisabled(shopItem)


        holder.itemView.setOnLongClickListener {
            onShopItemClick?.longClickItem(shopItem)
            true
        }
        holder.itemView.setOnClickListener {
            onShopItemClick?.clickItem(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isEnabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }

    }

    //override fun getItemCount(): Int = shopList.size



    inner class ViewHolderShopItem(viewBinding: ViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {


        fun bindEnabled(shopItem: ShopItem) {
            ItemShopEnabledBinding.bind(itemView).apply {
                tvName.text = shopItem.name
                tvCount.text = shopItem.count.toString()
            }
        }

        fun bindDisabled(shopItem: ShopItem) {
            ItemShopDisabledBinding.bind(itemView).apply {
                tvName.text = shopItem.name
                tvCount.text = shopItem.count.toString()
            }
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = -1
        const val MAX_POOL_SIZE = 30
    }
}


