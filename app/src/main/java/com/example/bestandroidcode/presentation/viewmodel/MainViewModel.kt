package com.example.bestandroidcode.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.bestandroidcode.data.remote.model.Cat
import com.example.bestandroidcode.data.remote.model.CatResponse
import com.example.bestandroidcode.data.remote.repository.DataRepository
import com.example.bestandroidcode.presentation.viewmodel.MainViewModel.CurrentViewState.HideLoading
import com.example.bestandroidcode.presentation.viewmodel.MainViewModel.CurrentViewState.ShowLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
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
                passRandomCatState(
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
        Log.d(javaClass.simpleName, "VM state passRandomCatState $currentViewState")
        if (isMain)
            randomCatData.value = currentViewState
        else
            randomCatData.postValue(currentViewState)

    }

    private fun passCategoryViewState(currentViewState: CurrentViewState, isMain: Boolean) {
        Log.d(javaClass.simpleName, "VM state passCategoryViewState $currentViewState")
        if (isMain)
            categoryCatData.value = currentViewState
        else
            categoryCatData.postValue(currentViewState)

    }

    override fun onCleared() {
        if (this::categoryCatObserver.isInitialized) {
            repository.randomCatLiveData.removeObserver(categoryCatObserver)
        }
        if (this::randCatObserver.isInitialized) {
            repository.randomCatLiveData.removeObserver(randCatObserver)
        }
        super.onCleared()
    }

    sealed class CurrentViewState {
        object ShowLoading : CurrentViewState()
        object HideLoading : CurrentViewState()
        data class ShowError(val message: String) : CurrentViewState()
        data class ShowData(val item: Cat?) : CurrentViewState()
    }
}