package com.example.bestandroidcode.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bestandroidcode.CatTestData
import com.example.bestandroidcode.CatTestData.errorMessage
import com.example.bestandroidcode.CoroutineTestRule
import com.example.bestandroidcode.data.remote.repository.DataRepository
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest : TestCase() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = CoroutineTestRule()

    @Mock
    var repository: DataRepository? = null

    private lateinit var mainViewModel: MainViewModel
    private var stateInvocationCount = 0;
    private val category = "CATEGORY"

    @Before
    public override fun setUp() {
        mainViewModel = MainViewModel(repository!!)
    }

    @Test
    fun `test Successful Random Cat response`() = runBlocking {
        runBlocking {
            `when`(repository!!.fetchRandomCat())
                .thenReturn(CatTestData.successCatResponse)
        }
        mainViewModel.getRandomCat().observeForever {
            when (it) {
                is MainViewModel.CurrentViewState.ShowLoading -> {
                    stateInvocationCount++
                }
                is MainViewModel.CurrentViewState.HideLoading -> {
                    stateInvocationCount++
                    assertEquals(stateInvocationCount, 2)
                }
                is MainViewModel.CurrentViewState.ShowError -> {
                    assertTrue(false)
                }
                is MainViewModel.CurrentViewState.ShowData -> {
                    stateInvocationCount++
                    assertEquals(it.item?.id, CatTestData.cat.id)
                    assertEquals(stateInvocationCount, 3)
                }
            }
        }
    }

    @Test
    fun `test Failed Random Cat response`() = runBlocking {
        runBlocking {
            `when`(repository!!.fetchRandomCat())
                .thenReturn(CatTestData.failedCatResponse)
        }
        mainViewModel.getRandomCat().observeForever {
            when (it) {
                is MainViewModel.CurrentViewState.ShowLoading -> {
                    stateInvocationCount++
                }
                is MainViewModel.CurrentViewState.HideLoading -> {
                    stateInvocationCount++
                    assertEquals(stateInvocationCount, 2)
                }
                is MainViewModel.CurrentViewState.ShowError -> {
                    stateInvocationCount++
                    assertEquals(it.message, errorMessage)
                    assertEquals(stateInvocationCount, 3)
                }
                is MainViewModel.CurrentViewState.ShowData -> {
                    assertTrue(false)
                }
            }
        }
    }

    @Test
    fun `test Successful Cat ByCategory`() = runBlocking {
        runBlocking {
            `when`(repository!!.getCatByCategory(category))
                .thenReturn(CatTestData.successCatResponse)
        }
        mainViewModel.getCatByCategory(category).observeForever {
            when (it) {
                is MainViewModel.CurrentViewState.ShowLoading -> {
                    stateInvocationCount++
                }
                is MainViewModel.CurrentViewState.HideLoading -> {
                    stateInvocationCount++
                    assertEquals(stateInvocationCount, 2)
                }
                is MainViewModel.CurrentViewState.ShowError -> {
                    assertTrue(false)
                }
                is MainViewModel.CurrentViewState.ShowData -> {
                    stateInvocationCount++
                    assertEquals(it.item?.id, CatTestData.cat.id)
                    assertEquals(stateInvocationCount, 3)
                }
            }
        }
    }

    @Test
    fun `test Failed Cat ByCategory`() = runBlocking {
        runBlocking {
            `when`(repository!!.getCatByCategory(category))
                .thenReturn(CatTestData.failedCatResponse)
        }
        mainViewModel.getCatByCategory(category).observeForever {
            when (it) {
                is MainViewModel.CurrentViewState.ShowLoading -> {
                    stateInvocationCount++
                }
                is MainViewModel.CurrentViewState.HideLoading -> {
                    stateInvocationCount++
                    assertEquals(stateInvocationCount, 2)
                }
                is MainViewModel.CurrentViewState.ShowError -> {
                    stateInvocationCount++
                    assertEquals(it.message, errorMessage)
                    assertEquals(stateInvocationCount, 3)
                }
                is MainViewModel.CurrentViewState.ShowData -> {
                    assertTrue(false)
                }
            }
        }
    }

}

