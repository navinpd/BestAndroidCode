package com.example.bestandroidcode.di

import android.content.Context
import com.bumptech.glide.Glide
import com.example.bestandroidcode.data.remote.ServiceBuilder
import com.example.bestandroidcode.data.remote.api.CatAPI
import com.example.bestandroidcode.data.remote.repository.DataRepository
import com.example.bestandroidcode.presentation.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DiModule {

    @Provides
    fun provideNetworking(): CatAPI = ServiceBuilder.buildService(CatAPI::class.java)

    @Provides
    fun provideRepository(catAPI: CatAPI) =
        DataRepository(catApi = catAPI)

    @Provides
    fun provideMainViewModel(dataRepository: DataRepository) =
        MainViewModel(repository = dataRepository)

    @Provides
    fun provideSharedPreference(@ApplicationContext appContext: Context) =
        appContext.getSharedPreferences("Local-Shared-Pref", 0)

    @Provides
    fun provideGlide(@ApplicationContext appContext: Context) = Glide.with(appContext)

}