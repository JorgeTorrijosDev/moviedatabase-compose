package com.example.moviedatabase.data.sources.remote

import com.example.moviedatabase.BuildConfig
import com.example.moviedatabase.utils.ConstantsUtils
import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .addQueryParameter(ConstantsUtils.API_KEY, BuildConfig.API_KEY_MOVIEDATABASE)
            .addQueryParameter(ConstantsUtils.API_ADULT,ConstantsUtils.API_ADULT_VALUE)
            .build()
        val request = chain.request().newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}