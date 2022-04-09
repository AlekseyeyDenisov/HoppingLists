package com.example.listporxhese.domain

class EditShopItemUseCase(private val repositoryShopList: RepositoryShopList) {
    fun editShopItem(id: Int) {
        repositoryShopList.editShopItem(id)
    }
}