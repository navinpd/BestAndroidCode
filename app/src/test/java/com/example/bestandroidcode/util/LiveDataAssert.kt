package com.example.bestandroidcode.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.bestandroidcode.data.remote.model.CatResponse
import org.junit.Test

class LiveDataAssert<S> {

    var liveData: LiveData<S>? = null
    private val capturedState = mutableListOf<S>()

    private val liveDataObserver: Observer<S> = Observer { state ->
        capturedState.add(state)
    }

    fun start(liveDataStates: LiveData<S>) {
        liveData = liveDataStates
        liveData?.observeForever(liveDataObserver)
    }

    fun stop() {
        liveData?.removeObserver(liveDataObserver)
        liveData = null
        capturedState.clear()
    }

    fun assertStates(assertion: (states: List<S>) -> Unit) {
        assertion(capturedState)
    }

    fun assertLastState(name: (lastState: S) -> Unit) {
        name(capturedState.last())
    }

}

class MyTestClass {

    private val liveDataAssert = LiveDataAssert<CatResponse>()
    private val liveData = MutableLiveData<CatResponse>() //Get this LData from VModel

    fun setUp() {
        liveDataAssert.start(liveData)
    }

    fun tearDown() {
        liveDataAssert.stop()
    }

    @Test
    fun testCases() {
        liveDataAssert.assertLastState { actual ->
            assert(actual.cat?.id == CatTestData.cat.id)
        }

        liveDataAssert.assertStates { states ->
            val initialMessage = states[0]
            val errorMessage = states[1]
            val successMessage = states[2]

            //Compare states here
            assert(states[0].cat?.id == CatTestData.cat.id)
        }

    }

}