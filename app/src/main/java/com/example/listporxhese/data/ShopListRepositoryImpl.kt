package com.example.listporxhese.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.listporxhese.domain.RepositoryShopList
import com.example.listporxhese.domain.ShopItem
import kotlin.random.Random


object ShopListRepositoryImpl : RepositoryShopList {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
     //private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })
     private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {
        for (i in 0 until 3000) {
            val item = ShopItem("Name ${i+1}", i+1, Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = ++autoIncrementId
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList() {
        shopListLD.value = shopList.sortedBy { o1-> o1.id }
    }
}
