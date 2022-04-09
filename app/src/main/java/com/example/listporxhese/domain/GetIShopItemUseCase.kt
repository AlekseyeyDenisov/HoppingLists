package com.example.listporxhese.domain

class GetIShopItemUseCase(private val repositoryShopList: RepositoryShopList) {
    fun getShopItem(id: Int): ShopItem {
        return repositoryShopList.getShopItem(id)
    }
}