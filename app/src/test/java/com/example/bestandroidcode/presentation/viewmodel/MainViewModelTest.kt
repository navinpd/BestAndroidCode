package com.example.bestandroidcode.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bestandroidcode.CatTestData
import com.example.bestandroidcode.CatTestData.category
import com.example.bestandroidcode.CatTestData.errorMessage
import com.example.bestandroidcode.CoroutineTestRule
import com.example.bestandroidcode.data.remote.repository.DataRepository
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import androidx.lifecycle.Observer as Observer1

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest : TestCase() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutinesRule = CoroutineTestRule()

    @Mock
    var repository: DataRepository? = null

    private lateinit var subject: MainViewModel
    private var stateInvocationCount = 0;
    private lateinit var randomCatObserver: Observer1<in MainViewModel.CurrentViewState>
    private lateinit var specialCatObserver: Observer1<in MainViewModel.CurrentViewState>

    @Before
    public override fun setUp() {
        subject = MainViewModel(repository!!)
    }

    @After
    public override fun tearDown() {
        super.tearDown()
        if (subject.getRandomCat().hasObservers()) subject.getRandomCat()
            .removeObserver(randomCatObserver)

        if (subject.getCatByCategory(category).hasObservers()) subject.getCatByCategory(category)
            .removeObserver(specialCatObserver)
    }

    @Test
    fun `test Successful Random Cat response`() = runBlocking {
        runBlocking {
            `when`(repository!!.fetchRandomCat())
                .thenReturn(CatTestData.successCatResponse)
        }

        randomCatObserver = object : Observer, Observer1<MainViewModel.CurrentViewState> {
            override fun onChanged(it: MainViewModel.CurrentViewState?) {
                verifyResponse(it, true)
            }

            override fun update(p0: Observable?, p1: Any?) {
            }

        }
        subject.getRandomCat().observeForever(randomCatObserver)
    }

    @Test
    fun `test Failed Random Cat response`() = runBlocking {
        runBlocking {
            `when`(repository!!.fetchRandomCat())
                .thenReturn(CatTestData.failedCatResponse)
        }

        randomCatObserver = object : Observer, Observer1<MainViewModel.CurrentViewState> {
            override fun onChanged(it: MainViewModel.CurrentViewState?) {
                verifyResponse(it, false)
            }

            override fun update(p0: Observable?, p1: Any?) {
            }

        }
        subject.getRandomCat().observeForever(randomCatObserver)
    }

    @Test
    fun `test Successful Cat ByCategory`() = runBlocking {
        runBlocking {
            `when`(repository!!.getCatByCategory(category))
                .thenReturn(CatTestData.successCatResponse)
        }
        specialCatObserver = object : Observer, Observer1<MainViewModel.CurrentViewState> {
            override fun onChanged(it: MainViewModel.CurrentViewState?) {
                verifyResponse(it, true)
            }

            override fun update(p0: Observable?, p1: Any?) {
            }

        }

        subject.getCatByCategory(category).observeForever(specialCatObserver)
    }

    @Test
    fun `test Failed Cat ByCategory`() = runBlocking {
        runBlocking {
            `when`(repository!!.getCatByCategory(category))
                .thenReturn(CatTestData.failedCatResponse)
        }
        specialCatObserver = object : Observer, Observer1<MainViewModel.CurrentViewState> {
            override fun onChanged(it: MainViewModel.CurrentViewState?) {
                verifyResponse(it, false)
            }

            override fun update(p0: Observable?, p1: Any?) {
            }

        }

        subject.getCatByCategory(category).observeForever(specialCatObserver)
    }

    fun verifyResponse(it : MainViewModel.CurrentViewState?, isSuccess: Boolean) {
        when (it) {
            is MainViewModel.CurrentViewState.ShowLoading -> {
                stateInvocationCount++
            }
            is MainViewModel.CurrentViewState.HideLoading -> {
                stateInvocationCount++
                assertEquals(stateInvocationCount, 2)
            }
            is MainViewModel.CurrentViewState.ShowError -> {
                if(isSuccess) {
                    assertTrue(false)
                } else {
                    stateInvocationCount++
                    assertEquals(it.message, errorMessage)
                    assertEquals(stateInvocationCount, 3)
                }
            }
            is MainViewModel.CurrentViewState.ShowData -> {
                if(isSuccess) {
                    stateInvocationCount++
                    assertEquals(it.item?.id, CatTestData.cat.id)
                    assertEquals(stateInvocationCount, 3)
                } else {
                    assertTrue(false)
                }
            }
            null -> assertTrue(false)
        }
    }

}

