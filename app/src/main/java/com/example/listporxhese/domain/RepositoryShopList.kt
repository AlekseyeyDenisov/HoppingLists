package com.example.listporxhese.domain

interface RepositoryShopList {
    fun getShopList():List<ShopItem>
    fun addShopItem(shopItem: ShopItem)
    fun deleteShopItem(shopItem: ShopItem)
    fun editShopItem(id:Int)
    fun getShopItem(id:Int):ShopItem

}