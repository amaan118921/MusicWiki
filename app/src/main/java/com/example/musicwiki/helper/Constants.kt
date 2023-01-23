package com.example.musicwiki.helper

import androidx.fragment.app.Fragment
import com.example.musicwiki.fragments.AlbumDetailFragment
import com.example.musicwiki.fragments.ArtistsDetailFragment
import com.example.musicwiki.fragments.GenreDetailFragment
import com.example.musicwiki.fragments.SelectGenreFragment

class Constants {
    companion object {
        const val ALBUM = "ALBUM"
        const val ALBUM_DETAIL = "ALBUM_DETAIL"
        const val SELECT_GENRE = "SELECT_GENRE"
        const val DETAIL_GENRE = "DETAIL_GENRE"
        const val GENRE = "GENRE"
        const val API_KEY = "2f13c92fa4112b3fe0c385f2d16574fc"
        const val SHARED_SECRET = "4b905f90b87ffb437e5eba9a0fdc3aaa"
        const val BASE_URL = "https://ws.audioscrobbler.com/2.0/"
        const val getTop = "tag.getTopTags"
        const val getTopAlbums = "tag.gettopalbums"
        const val getTopArtists = "tag.gettopartists"
        const val getTopTracks = "tag.gettoptracks"
        const val getInfo = "tag.getinfo"
        const val JSON = "json"
        const val ARTIST = "ARTIST"
        const val ARTIST_DETAIL = "ARTIST_DETAIL"
        const val getArtistInfo = "artist.getinfo"
        const val getArtistTopAlbums = "artist.gettopalbums"
        const val getArtistTopTracks = "artist.gettoptracks"
        fun getFragmentClass(fragmentID: String): Class<Fragment> {
            return when(fragmentID) {
                SELECT_GENRE -> SelectGenreFragment::class.java as Class<Fragment>
                DETAIL_GENRE -> GenreDetailFragment:: class.java as Class<Fragment>
                ALBUM_DETAIL -> AlbumDetailFragment::class.java as Class<Fragment>
                ARTIST_DETAIL -> ArtistsDetailFragment::class.java as Class<Fragment>
                else -> SelectGenreFragment::class.java as Class<Fragment>
            }
        }
    }
}