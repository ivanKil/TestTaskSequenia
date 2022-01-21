package com.kusch.filmsmvp.data.di.modules


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kusch.filmsmvp.data.api.FilmsApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BSE_URL = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/"

@Module
class FilmsApiModule {

    @Reusable
    @Provides
    fun provideGitHubApi(): FilmsApi =
        Retrofit.Builder()
            .baseUrl(BSE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(createOkHttpClient())
            .build()
            .create(FilmsApi::class.java)

    private val gson: Gson = GsonBuilder().create()

    private fun createOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

}