package com.example.musicwiki.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.musicwiki.fragments.TopArtistsFragment
import com.example.musicwiki.fragments.TopTracksFragment
import com.example.musicwiki.fragments.TopAlbumsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> TopAlbumsFragment()
            1 -> TopArtistsFragment()
            else -> TopTracksFragment()
        }
    }
}