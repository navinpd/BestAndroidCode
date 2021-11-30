package com.example.bestandroidcode.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bestandroidcode.CatTestData
import com.example.bestandroidcode.data.remote.repository.DataRepository
import junit.framework.TestCase
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest : TestCase() {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    var repository : DataRepository? = null

    lateinit var mainViewModel : MainViewModel

    @Before
    public override fun setUp() {
        mainViewModel = MainViewModel(repository!!)
        runBlocking {
            `when`(repository!!.fetchRandomCat())
                .thenReturn(CatTestData.successCatResponse)
        }
    }

}

