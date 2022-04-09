package com.example.listporxhese.domain

class GetShopListUseCase (private val repositoryShopList: RepositoryShopList) {
    fun getShopList():List<ShopItem>{
       return repositoryShopList.getShopList()
    }
}