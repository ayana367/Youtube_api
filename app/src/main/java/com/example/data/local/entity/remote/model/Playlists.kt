package com.example.data.local.entity.remote.model
import android.provider.MediaStore
import com.google.gson.annotations.SerializedName

data class Playlists(@SerializedName("kind")
                     val kind: String = "",
                     @SerializedName("nextPageToken")
                     val nextPageToken: String = "",
                     @SerializedName("pageInfo")
                     val pageInfo: PageInfo,
                     @SerializedName("etag")
                     val etag: String = "",
                     @SerializedName("items")
                     val items: List<ItemsItem>?)

data class Snippet(@SerializedName("publishedAt")
                    val publishedAt: String = "",
                   @SerializedName("localized")
                    val localized: Localized,
                   @SerializedName("description")
                    val description: String = "",
                   @SerializedName("title")
                    val title: String = "",
                   @SerializedName("thumbnails")
                    val thumbnails: Thumbnails,
                   @SerializedName("channelId")
                    val channelId: String = "",
                   @SerializedName("channelTitle")
                    val channelTitle: String = "")

data class Standard(@SerializedName("width")
    val width: Int = 0,
    @SerializedName("url")
    val url: String = "",
    @SerializedName("height")
    val height: Int = 0)

data class Thumbnails(@SerializedName("standard")
    val standard: Standard,
                      @SerializedName("default")
    val default: Default,
                      @SerializedName("high")
    val high: High,
                      @SerializedName("maxres")
    val maxres: Maxres,
                      @SerializedName("medium")
    val medium: Medium
)

data class PageInfo(@SerializedName("totalResults")
    val totalResults: Int = 0,
    @SerializedName("resultsPerPage")
    val resultsPerPage: Int = 0)

data class Medium(@SerializedName("width")
    val width: Int = 0,
    @SerializedName("url")
    val url: String = "",
    @SerializedName("height")
    val height: Int = 0)

data class Maxres(@SerializedName("width")
    val width: Int = 0,
    @SerializedName("url")
    val url: String = "",
    @SerializedName("height")
    val height: Int = 0)

data class Localized(@SerializedName("description")
    val description: String = "",
    @SerializedName("title")
    val title: String = "")

data class ItemsItem(@SerializedName("snippet")
    val snippet: Snippet,
                     @SerializedName("kind")
    val kind: String = "",
                     @SerializedName("etag")
    val etag: String = "",
                     @SerializedName("id")
    val id: String = "",
                     @SerializedName("contentDetails")
    val contentDetails: ContentDetails
) : java.io.Serializable

data class High(@SerializedName("width")
    val width: Int = 0,
    @SerializedName("url")
    val url: String = "",
    @SerializedName("height")
    val height: Int = 0)

data class Default(@SerializedName("width")
    val width: Int = 0,
    @SerializedName("url")
    val url: String = "",
    @SerializedName("height")
    val height: Int = 0)

data class ContentDetails(@SerializedName("itemCount")
    val itemCount: Int = 0)

