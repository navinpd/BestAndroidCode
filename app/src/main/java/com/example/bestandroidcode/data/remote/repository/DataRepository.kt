package com.example.bestandroidcode.data.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bestandroidcode.data.remote.api.CatAPI
import com.example.bestandroidcode.data.remote.model.Cat
import com.example.bestandroidcode.data.remote.model.CatResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val catApi: CatAPI
) {

    private val randomCatData = MutableLiveData<CatResponse>()
    val randomCatLiveData: LiveData<CatResponse>
        get() = randomCatData

    private val categoryCatData = MutableLiveData<CatResponse>()
    val categoryCatLiveData: LiveData<CatResponse>
        get() = categoryCatData

    fun getRandomCatApi() {
        catApi.getCatRandom().enqueue(object : Callback<List<Cat>> {

            override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                if (response.isSuccessful) {
                    randomCatData.postValue(
                        CatResponse(
                            cat = response.body()?.first(),
                            throwable = null
                        )
                    )
                } else {
                    randomCatData.postValue(
                        CatResponse(
                            cat = null,
                            throwable = Throwable(response.message())
                        )
                    )
                }
            }

            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                randomCatData.postValue(CatResponse(cat = null, throwable = t))
            }

        })
    }

    fun getCategoryBasedCat(categoryId: String) {
        catApi.getCatBasedOnCategory(categoryId).enqueue(object : Callback<List<Cat>> {
            override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                if (response.isSuccessful) {
                    categoryCatData.postValue(
                        CatResponse(
                            cat = response.body()?.first(),
                            throwable = null
                        )
                    )
                } else {
                    categoryCatData.postValue(
                        CatResponse(
                            cat = null,
                            throwable = Throwable(response.message())
                        )
                    )
                }
            }

            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                categoryCatData.postValue(CatResponse(cat = null, throwable = t))
            }

        })
    }

}