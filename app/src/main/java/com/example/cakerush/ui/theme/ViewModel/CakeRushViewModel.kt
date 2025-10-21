package com.example.cakerush.ui.theme.ViewModel

import androidx.lifecycle.LiveData
import com.example.cakerush.Model.CategoryModel
import com.example.cakerush.Model.ItemsModel
import com.example.cakerush.Model.Repository.CakeRushRepository
import com.example.cakerush.Model.SliderModel


class CakeRushViewModel {
    private val repository = CakeRushRepository()

    fun loadBanner(): LiveData<MutableList<SliderModel>> {
        return repository.loadBanner()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }

    fun loadBestSeller(): LiveData<MutableList<ItemsModel>> {
        return repository.loadBestSeller()
    }

    fun loadFiltered(id: String): LiveData<MutableList<ItemsModel>> {
        return repository.loadFiltered(id)
    }


}