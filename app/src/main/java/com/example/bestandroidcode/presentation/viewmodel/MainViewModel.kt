package com.example.bestandroidcode.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.bestandroidcode.data.remote.model.Cat
import com.example.bestandroidcode.data.remote.repository.DataRepository
import com.example.bestandroidcode.presentation.viewmodel.MainViewModel.CurrentViewState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

//Another way of getting data from repository
//    private val catCompletableDeferred = CompletableDeferred<CatResponse>()
//
//    suspend fun getRandomCat(): CatResponse {
//        viewModelScope.launch {
//            val catResponse = repository.fetchRandomCat()
//            catCompletableDeferred.complete(catResponse)
//        }
//        return catCompletableDeferred.await()
//    }

    fun getRandomCat() = liveData {
        emit(ShowLoading)
        val catResponse = repository.fetchRandomCat()
        emit(HideLoading)

        if (catResponse.throwable == null)
            emit(ShowData(catResponse.cat))
        else
            emit(ShowError(catResponse.throwable.message ?: "Unknown Error"))
    }

    fun getCatByCategory(categoryId: String) = liveData {
        emit(ShowLoading)
        val catResponse = repository.getCatByCategory(categoryId)
        emit(HideLoading)

        if (catResponse.throwable == null) {
            emit(ShowData(catResponse.cat))
        } else {
            emit(ShowError(catResponse.throwable.message ?: "Unknown Error"))
        }
    }

    sealed class CurrentViewState {
        object ShowLoading : CurrentViewState()
        object HideLoading : CurrentViewState()
        data class ShowError(val message: String) : CurrentViewState()
        data class ShowData(val item: Cat?) : CurrentViewState()
    }
}