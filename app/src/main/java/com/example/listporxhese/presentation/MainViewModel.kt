package com.example.listporxhese.presentation

import androidx.lifecycle.ViewModel
import com.example.listporxhese.data.ShopListRepositoryImpl
import com.example.listporxhese.domain.DeleteShopItemUseCase
import com.example.listporxhese.domain.EditShopItemUseCase
import com.example.listporxhese.domain.GetShopListUseCase
import com.example.listporxhese.domain.ShopItem


class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(isEnabled = !shopItem.isEnabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}
