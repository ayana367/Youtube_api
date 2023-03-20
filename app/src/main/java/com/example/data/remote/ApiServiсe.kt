package com.example.data.remote

import com.example.data.entity.model.ItemsItem
import com.example.data.entity.model.Playlists
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("playlists")
  suspend  fun getPlaylists(
        @Query("part") apiKey:String,
        @Query("channelId") channelId:String,
        @Query("key") key:String,
        @Query("maxResults") maxResults : Int = 30
    ): Response<Playlists>

    @GET("playlistItems")
  suspend  fun getPlaylistItems(
        @Query("key") key : String,
        @Query("part") part : String,
        @Query("playlistId") channelId : String,
        @Query("maxResults") maxResults : Int = 20
    ) : Response<Playlists>

    @GET("videos")
    suspend fun getVideo(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("id") id: String,
    ): Response<ItemsItem>
}

