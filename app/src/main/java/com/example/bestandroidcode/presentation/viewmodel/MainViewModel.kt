package com.example.bestandroidcode.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.bestandroidcode.data.remote.model.Cat
import com.example.bestandroidcode.data.remote.model.CatResponse
import com.example.bestandroidcode.data.remote.repository.DataRepository
import com.example.bestandroidcode.presentation.viewmodel.MainViewModel.CurrentViewState.HideLoading
import com.example.bestandroidcode.presentation.viewmodel.MainViewModel.CurrentViewState.ShowLoading
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {

    private val randomCatData = MutableLiveData<CurrentViewState>()
    val randomCatLiveData: LiveData<CurrentViewState>
        get() = randomCatData
    private lateinit var randCatObserver: Observer<CatResponse>

    private val categoryCatData = MutableLiveData<CurrentViewState>()
    val categoryCatLiveData: LiveData<CurrentViewState>
        get() = categoryCatData
    private lateinit var categoryCatObserver: Observer<CatResponse>

    fun getRandomCat() {
        passRandomCatState(ShowLoading, true)

        randCatObserver = Observer {
            passRandomCatState(HideLoading, true)

            if (it.throwable == null) {
                passCategoryViewState(
                    CurrentViewState.ShowData(it.cat), false
                )
            } else {
                passRandomCatState(
                    CurrentViewState.ShowError(it.throwable.message ?: "Unknown Error"), false
                )
            }
        }

        repository.randomCatLiveData.observeForever(randCatObserver)
        repository.getRandomCatApi()
    }

    fun getCatByCategory(categoryId: String) {
        passCategoryViewState(ShowLoading, true)

        categoryCatObserver = Observer {
            passCategoryViewState(HideLoading, true)

            if (it.throwable == null) {
                passCategoryViewState(
                    CurrentViewState.ShowData(it.cat), false
                )
            } else {
                passCategoryViewState(
                    CurrentViewState.ShowError(it.throwable.message ?: "Unknown Error"), false
                )
            }
        }

        repository.categoryCatLiveData.observeForever(categoryCatObserver)
        repository.getCategoryBasedCat(categoryId)
    }

    private fun passRandomCatState(currentViewState: CurrentViewState, isMain: Boolean) {
        if (isMain)
            randomCatData.value = currentViewState
        else
            randomCatData.postValue(currentViewState)

    }

    private fun passCategoryViewState(currentViewState: CurrentViewState, isMain: Boolean) {
        if (isMain)
            categoryCatData.value = currentViewState
        else
            categoryCatData.postValue(currentViewState)

    }

    override fun onCleared() {
        repository.randomCatLiveData.removeObserver(categoryCatObserver)
        repository.randomCatLiveData.removeObserver(randCatObserver)
        super.onCleared()
    }

    sealed class CurrentViewState {
        object ShowLoading : CurrentViewState()
        object HideLoading : CurrentViewState()
        data class ShowError(val message: String) : CurrentViewState()
        data class ShowData(val show: Cat?) : CurrentViewState()
    }
}