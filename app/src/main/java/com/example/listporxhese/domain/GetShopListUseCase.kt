package com.example.listporxhese.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase (private val repositoryShopList: RepositoryShopList) {
    fun getShopList(): LiveData<List<ShopItem>> {
       return repositoryShopList.getShopList()
    }
}