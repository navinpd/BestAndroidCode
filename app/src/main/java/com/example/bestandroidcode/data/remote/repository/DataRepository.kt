package com.example.bestandroidcode.data.remote.repository

import com.example.bestandroidcode.data.remote.api.CatAPI
import com.example.bestandroidcode.data.remote.model.CatResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val catApi: CatAPI
) {

    suspend fun fetchRandomCat(): CatResponse = withContext(IO) {
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

}