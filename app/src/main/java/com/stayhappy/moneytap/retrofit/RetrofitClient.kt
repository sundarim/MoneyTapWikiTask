package com.stayhappy.moneytap.retrofit

import android.content.Context
import okhttp3.Cache
import okhttp3.Interceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import java.io.File





class RetrofitClient{
   companion object {
        fun getClient(context:Context): Retrofit? {
            if (Companion.retrofit == null) {
                val cacheSize = 10 * 1024 * 1024
                val cache = Cache(context.cacheDir ,cacheSize.toLong())
                val httpClient = OkHttpClient.Builder().cache(cache).addInterceptor { chain ->
                    val original = chain.request()

                    val request = original.newBuilder()
                            .method(original.method(), original.body())
                            .build()
                    chain.proceed(request)
                }
                val client = httpClient.build()
                Companion.retrofit = Retrofit.Builder()
                        .baseUrl(Companion.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build()
            }
            return Companion.retrofit
        }
        val BASE_URL = "https://en.wikipedia.org/w/"
        private var retrofit: Retrofit? = null

    }


}