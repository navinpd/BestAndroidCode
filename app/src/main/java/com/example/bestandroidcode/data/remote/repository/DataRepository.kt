package com.example.bestandroidcode.data.remote.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bestandroidcode.data.remote.api.CatAPI
import com.example.bestandroidcode.data.remote.model.Cat
import com.example.bestandroidcode.data.remote.model.CatResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
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

    @WorkerThread
    suspend fun fetchRandomCat(): CatResponse = withContext(Dispatchers.IO) {
        val result = try {
            catApi.getCatRandom().execute()
        } catch (cause: Throwable) {
            throw cause
        }
        if (result.isSuccessful) {
            return@withContext CatResponse(result.body()?.get(0), null)
        } else {
            return@withContext CatResponse(
                cat = null,
                throwable = Throwable(result.message())
            )
        }
    }

    suspend fun getCatByCategory(categoryId : String) : CatResponse = withContext(IO){
        val result = try {
            catApi.getCatBasedOnCategory(categoryId).execute()
        } catch (ex: Throwable) {
            throw ex
        }
        if(result.isSuccessful) {
            return@withContext CatResponse(result.body()?.get(0) , null)
        } else {
            return@withContext CatResponse(
                cat = null,
                throwable = Throwable(result.message())
            )
        }
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