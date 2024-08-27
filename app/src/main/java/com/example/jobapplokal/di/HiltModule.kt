package com.example.jobapplokal.di

import com.example.jobapplokal.data_layer.network.ApiService
import com.example.jobapplokal.data_layer.repoImpl.RepoImpl
import com.example.jobapplokal.domain_layer.repo.Repo
import com.example.jobapplokal.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideJobs(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideRepo(api: ApiService): Repo {
        return RepoImpl(apiRef = api)
    }





}