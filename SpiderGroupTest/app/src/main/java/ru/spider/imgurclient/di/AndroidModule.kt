package ru.spider.imgurclient.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.spider.imgurclient.network.Api
import ru.spider.imgurclient.network.UnsafeOkHttpClient
import javax.inject.Singleton


@Module
class AndroidModule(private val application: App) {

    @Provides
    @Singleton
    @Application
    fun provideApplicationContext() = application

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return UnsafeOkHttpClient.unsafeOkHttpClient
    }

    @Provides
    @Singleton
    fun provideApi(gson: Gson, httpClient: OkHttpClient): Api {
        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))

        return retrofit.build().create(Api::class.java)
    }
}