package com.example.musicwiki.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.helper.Constants
import com.example.musicwiki.model.*
import com.example.musicwiki.networkService.API
import kotlinx.coroutines.launch

class MusicViewModel: ViewModel() {

    private val _genres: MutableLiveData<List<Genres>?> = MutableLiveData()
    val genres: LiveData<List<Genres>?> = _genres

    var _genreInfo: MutableLiveData<GenreDesc?> = MutableLiveData()
    var _top : MutableLiveData<AlbumModel?> = MutableLiveData()

    var _topArtists: MutableLiveData<ArtistModel?> = MutableLiveData()
    var _topTracks: MutableLiveData<TrackModel?> = MutableLiveData()

    var _artistInfo : MutableLiveData<Stats?> = MutableLiveData()
    var _artistTopAlbums: MutableLiveData<List<Album>?> = MutableLiveData()
    var _artistTopTracks: MutableLiveData<List<Track>?> = MutableLiveData()

    private var genre: String? = null

    fun setGenre(genre: String?){
        this.genre =genre
    }

    fun getGenre(): String? {
        return this.genre
    }

    fun getGenres() {
        viewModelScope.launch {
            _genres.value = API.retrofitService.getTopGenres(Constants.getTop, Constants.API_KEY, Constants.JSON).toptags?.tag
        }
    }

    fun getGenreInfo(genre: String) {
        viewModelScope.launch {
            _genreInfo.value = API.retrofitService.getGenreInfo(Constants.getInfo, genre, Constants.API_KEY, Constants.JSON).tag
        }
    }

    fun getTop(method: String, genre: String) {
        Constants.apply {
            when(method) {
                getTopAlbums ->  viewModelScope.launch {
                    _top.value = API.retrofitService.getTopAlbums(method, genre, API_KEY, JSON).albums
                }
                getTopArtists -> viewModelScope.launch {
                    _topArtists.value = API.retrofitService.getTopArtists(method, genre, API_KEY, JSON).topartists
                }
                getTopTracks -> viewModelScope.launch {
                    _topTracks.value = API.retrofitService.getTopTracks(method, genre, API_KEY, JSON).tracks
                }
            }
        }
    }

    fun getArtistInfo(method: String, artist: String) {
        viewModelScope.launch {
            _artistInfo.value = API.retrofitService.getArtistInfo(method, artist, Constants.API_KEY, Constants.JSON).artist?.stats
        }
    }

    fun getTopAlbums(method: String, artist: String) {
        viewModelScope.launch {
            _artistTopAlbums.value = API.retrofitService.getArtistTopAlbums(method, artist, Constants.API_KEY, Constants.JSON).topalbums?.album
        }
    }

    fun getTopTracks(method: String, artist: String) {
        viewModelScope.launch {
            _artistTopTracks.value = API.retrofitService.getArtistTopTracks(method, artist, Constants.API_KEY, Constants.JSON).toptracks?.track
        }
    }
}