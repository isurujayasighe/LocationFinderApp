package com.covalent.locationfinderapp.data.repository

import android.content.Context
import android.os.NetworkOnMainThreadException
import android.util.Log
import com.covalent.locationfinderapp.domain.repository.What3WordClient
import com.what3words.androidwrapper.What3WordsAndroidWrapper
import com.what3words.androidwrapper.What3WordsV3
import com.what3words.javawrapper.request.Coordinates
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class What3WordClientImpl @Inject constructor(
    @ApplicationContext context: Context,
    private val what3WordsV3: What3WordsV3
) : What3WordClient {
    override suspend fun getWhat3WordString(lat: Double, lng: Double): Flow<String> {
        return flow {

            try {
                val result =  what3WordsV3.convertTo3wa(Coordinates(6.8998884,79.8948804)).execute()
                if (result.isSuccessful){
                    Log.d("CO_RESULT",result.words)
                    emit(result.words)
                }

            }catch (e:NetworkOnMainThreadException){
                Log.d("CO_","${e.printStackTrace()}")
            }
        }
    }

}