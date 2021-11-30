package com.example.bestandroidcode

import com.example.bestandroidcode.data.remote.model.Cat
import com.example.bestandroidcode.data.remote.model.CatResponse

internal object CatTestData {

    val errorMessage = "Network Error"

    val cat = Cat(
        id = "z3-yEohk9",
        url = "https://cdn2.thecatapi.com/images/z3-yEohk9.jpg",
        width = 425,
        height = 283,
    )

    val listofCat = mutableListOf(cat)

    val throwable = Throwable(errorMessage)

    val successCatResponse = CatResponse(cat, null)

    val failedCatResponse = CatResponse(null, throwable)

}