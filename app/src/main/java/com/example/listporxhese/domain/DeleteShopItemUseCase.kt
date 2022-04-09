package com.example.listporxhese.domain

class DeleteShopItemUseCase(private val repositoryShopList: RepositoryShopList) {
    fun deleteShopItem(shopItem: ShopItem){
        repositoryShopList.deleteShopItem(shopItem)

    }
}