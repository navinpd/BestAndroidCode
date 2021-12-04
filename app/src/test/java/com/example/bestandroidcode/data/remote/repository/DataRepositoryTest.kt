package com.example.bestandroidcode.data.remote.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bestandroidcode.util.CatTestData
import com.example.bestandroidcode.util.CatTestData.category
import com.example.bestandroidcode.data.remote.api.CatAPI
import com.example.bestandroidcode.data.remote.model.Cat
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class DataRepositoryTest : TestCase() {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var catApi: CatAPI

    @Mock
    private val apiCall: Call<List<Cat>>? = null

    @Mock
    private val result: Response<List<Cat>>? = null

    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO

    private lateinit var subject: DataRepository


    @Before
    public override fun setUp() {
        subject = DataRepository(catApi = catApi, io = coroutineDispatcher!!)
        `when`(catApi.getCatRandom()).thenReturn(apiCall)
        `when`(catApi.getCatBasedOnCategory(category)).thenReturn(apiCall)

        `when`(apiCall!!.execute()).thenReturn(result)

        `when`(result?.body()).thenReturn(CatTestData.listofCat)
        `when`(result?.message()).thenReturn(CatTestData.errorMessage)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `test success fetchRandomCat`() = runTest {
        //Mock
        `when`(result?.isSuccessful).thenReturn(true)
        //Execute
        val catResponse = subject.fetchRandomCat()
        //Validate
        assertNull("Exception found", catResponse.throwable)
        assertEquals("Cat id mismatched", catResponse.cat?.id, CatTestData.cat.id)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `test failed fetchRandomCat`() = runTest {
        //Mock
        `when`(result?.isSuccessful).thenReturn(false)
        //Execute
        val catResponse = subject.fetchRandomCat()
        //Validate
        assertNotNull("Exception not found", catResponse.throwable)
        assertEquals("Cat id mismatched", catResponse.throwable?.message, CatTestData.errorMessage)
    }


    @Test
    @ExperimentalCoroutinesApi
    fun `test success getCatByCategory`() = runTest {
        //Mock
        `when`(result?.isSuccessful).thenReturn(true)
        //Execute
        val catResponse = subject.getCatByCategory(category)
        //Validate
        assertNull("Exception found", catResponse.throwable)
        assertEquals("Cat id mismatched", catResponse.cat?.id, CatTestData.cat.id)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `test failed getCatByCategory`() = runTest {
        //Mock
        `when`(result?.isSuccessful).thenReturn(false)
        //Execute
        val catResponse = subject.getCatByCategory(category)
        //Validate
        assertNotNull("Exception not found", catResponse.throwable)
        assertEquals("Cat id mismatched", catResponse.throwable?.message, CatTestData.errorMessage)
    }


    @After
    public override fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
    }
}