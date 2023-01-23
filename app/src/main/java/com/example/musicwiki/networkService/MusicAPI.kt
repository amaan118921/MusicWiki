package com.example.musicwiki.networkService

import com.example.musicwiki.helper.Constants
import com.example.musicwiki.model.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(Constants.BASE_URL)
    .build()

interface MusicAPI {
    @GET(".")
    suspend fun getTopGenres(@Query("method") method: String, @Query("api_key") key: String, @Query("format") format: String): GenreModel

    @GET(".")
    suspend fun getTopAlbums(@Query("method") method: String,@Query("tag") tag: String, @Query("api_key") key: String, @Query("format") format: String): Albums

    @GET(".")
    suspend fun getTopTracks(@Query("method") method: String,@Query("tag") tag: String, @Query("api_key") key: String, @Query("format") format: String): Tracks

    @GET(".")
    suspend fun getTopArtists(@Query("method") method: String,@Query("tag") tag: String, @Query("api_key") key: String, @Query("format") format: String): Artists

    @GET(".")
    suspend fun getGenreInfo(@Query("method") method: String,@Query("tag") tag: String, @Query("api_key") key: String, @Query("format") format: String): GenreInfo

    @GET(".")
    suspend fun getArtistInfo(@Query("method") method: String,@Query("artist") artist: String, @Query("api_key") key: String, @Query("format") format: String): ArtistInfo

    @GET(".")
    suspend fun getArtistTopAlbums(@Query("method") method: String,@Query("artist") artist: String, @Query("api_key") key: String, @Query("format") format: String): TopAlbums

    @GET(".")
    suspend fun getArtistTopTracks(@Query("method") method: String,@Query("artist") artist: String, @Query("api_key") key: String, @Query("format") format: String): TopTracks

}

object API {
    val retrofitService: MusicAPI by lazy { retrofit.create(MusicAPI::class.java) }
}
