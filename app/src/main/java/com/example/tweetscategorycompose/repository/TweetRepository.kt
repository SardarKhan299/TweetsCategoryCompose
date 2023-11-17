package com.example.tweetscategorycompose.repository

import android.util.Log
import com.example.tweetscategorycompose.api.TweetsApi
import com.example.tweetscategorycompose.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository@Inject constructor(private val tweetsApi: TweetsApi) {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets
    suspend fun getCategories(){
        val response = tweetsApi.getCategories()
        if(response.isSuccessful && response.body()!=null){
           _categories.emit(response.body()!!)
        }else{
            Log.d(TweetRepository::class.simpleName, "getCatrgories: Error.")
        }
    }

    suspend fun getTweets(category: String){
        val response = tweetsApi.getTweets(category)
        if(response.isSuccessful && response.body()!=null){
            _tweets.emit(response.body()!!)
        }else{
            Log.d(TweetRepository::class.simpleName, "getCatrgories: Error.")
        }
    }
}