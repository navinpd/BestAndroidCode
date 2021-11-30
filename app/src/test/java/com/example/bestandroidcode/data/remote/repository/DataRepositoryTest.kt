package com.example.bestandroidcode.data.remote.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bestandroidcode.CatTestData
import com.example.bestandroidcode.data.remote.api.CatAPI
import com.example.bestandroidcode.data.remote.model.Cat
import junit.framework.TestCase
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class DataRepositoryTest : TestCase() {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var catApi: CatAPI

    @Mock
    private var apiCall: Call<List<Cat>>? = null

    @Mock
    private var result: Response<List<Cat>>? = null

    @Mock
    private var coroutineDispatcher: CoroutineDispatcher? = null

    private lateinit var dataRepository: DataRepository


    @Before
    public override fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        dataRepository = DataRepository(catApi = catApi, io = coroutineDispatcher!!)
        `when`(catApi.getCatRandom()).thenReturn(apiCall)

        `when`(apiCall!!.execute()).then {
            (it.arguments[0] as Callback<List<Cat>>)
                .onResponse(apiCall, result)
        }

        `when`(result?.isSuccessful).thenReturn(true)
        `when`(result?.body()).thenReturn(CatTestData.listofCat)

        catApi.getCatRandom()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test success fetchRandomCat`() = runBlocking {
        val catResponse = dataRepository.fetchRandomCat()
        assertEquals("Exception found", catResponse.throwable, null)
        assertEquals("Cat id mismatched", catResponse.cat?.id, CatTestData.cat.id)
    }

    @Test
    fun `test failed fetchRandomCat`() {

    }


    @Test
    fun `test success getCatByCategory`() {

    }

    @Test
    fun `test failed getCatByCategory`() {

    }


    @After
    public override fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}