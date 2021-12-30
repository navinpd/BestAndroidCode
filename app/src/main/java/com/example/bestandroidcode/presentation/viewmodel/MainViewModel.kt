package com.example.bestandroidcode.presentation.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.bestandroidcode.data.remote.model.Cat
import com.example.bestandroidcode.data.remote.model.CatResponse
import com.example.bestandroidcode.data.remote.repository.DataRepository
import com.example.bestandroidcode.presentation.viewmodel.MainViewModel.CurrentViewState.*
import com.example.bestandroidcode.util.favList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DataRepository,
    private val sharedPref: SharedPreferences
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
    private var catImageUrl = ""

    private var unselectMutableLiveData = MutableLiveData<Unit>()
    val liveData : LiveData<Unit> = unselectMutableLiveData

    fun updateFavCatList(): Boolean {
        val currentFavoriteList =
            HashSet<String>(sharedPref.getStringSet(favList, HashSet<String>()))

        var isAdded = false
        if (catImageUrl.isNotEmpty() && catImageUrl.isNotBlank()) {
            if (currentFavoriteList.contains(catImageUrl)) {
                currentFavoriteList.remove(catImageUrl)
            } else {
                isAdded = true
                currentFavoriteList.add(catImageUrl)
            }

            val e: SharedPreferences.Editor = sharedPref.edit()
            e.remove(favList).apply()

            e.putStringSet(favList, currentFavoriteList).apply()
        }
        return isAdded
    }

    fun unselectLikeButton() {
        unselectMutableLiveData.postValue(Unit)
    }

    fun getRandomCat() = liveData {
        emit(ShowLoading)
        val catResponse = try {
            repository.fetchRandomCat()
        } catch (exception: Throwable) {
            CatResponse(null, exception)
        }
        emit(HideLoading)

        if (catResponse.throwable == null) {
            emit(ShowData(catResponse.cat))
            catImageUrl = catResponse.cat!!.url
        } else
            emit(ShowError(catResponse.throwable.message ?: "Unknown Error"))
    }

    fun getCatByCategory(categoryId: String) = liveData {
        emit(ShowLoading)
        val catResponse = try {
            repository.getCatByCategory(categoryId)
        } catch (exception: Throwable) {
            CatResponse(null, exception)
        }
        emit(HideLoading)

        if (catResponse.throwable == null) {
            emit(ShowData(catResponse.cat))
            catImageUrl = catResponse.cat!!.url
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