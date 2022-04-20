package com.example.listporxhese.domain



class EditShopItemUseCase(private val shopListRepository: RepositoryShopList) {
    fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}