package com.example.tweetscategorycompose.api

import com.example.tweetscategorycompose.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsApi {
    @GET("/v3/b/65562efa0574da7622c7820d?meta=false")
    suspend fun getTweets( @Header("X-JSON-Path") category:String):Response<List<TweetListItem>>


    @GET("/v3/b/65562efa0574da7622c7820d?meta=false")
    @Headers("X-JSON-Path:tweets..category")
    suspend fun getCategories():Response<List<String>>
}