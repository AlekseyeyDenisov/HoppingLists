package com.example.listporxhese.domain

class AddShopItemUseCase(private val repositoryShopList: RepositoryShopList) {
    fun addShopItem(shopItem: ShopItem){
        repositoryShopList.addShopItem(shopItem)

    }
}