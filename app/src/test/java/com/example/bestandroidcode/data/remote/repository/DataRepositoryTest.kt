package com.example.bestandroidcode.data.remote.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bestandroidcode.data.remote.api.CatAPI
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DataRepositoryTest : TestCase() {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var catApi : CatAPI

    lateinit var dataRepository : DataRepository


    @Before
    public override fun setUp() {
        dataRepository = DataRepository(catApi = catApi)
        Dispatchers.setMain(mainThreadSurrogate)

        if(catApi.getCatRandom())
    }

    @Test
    public fun `test success fetchRandomCat`() {

    }

    @Test
    public fun `test failed fetchRandomCat`() {

    }


    @Test
    public fun `test success getCatByCategory`() {

    }

    @Test
    public fun `test failed getCatByCategory`() {

    }



    @After
    public override fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}