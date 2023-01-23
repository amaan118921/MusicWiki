package com.example.musicwiki.fragments

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import com.example.musicwiki.R
import com.example.musicwiki.adapters.ViewPagerAdapter
import com.example.musicwiki.helper.Constants
import com.example.musicwiki.helper.makeGone
import com.example.musicwiki.helper.makeVisible
import com.example.musicwiki.model.GenreDesc
import com.example.musicwiki.viewModel.MusicViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_genre_detail.*
import java.util.*


class GenreDetailFragment: BaseFragment() {
    private val viewModel: MusicViewModel by activityViewModels()
    private var viewPagerAdapter: ViewPagerAdapter? = null
    private var genre: String? = null
    private var genreDesc: GenreDesc? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        genre = arguments?.getString(Constants.GENRE)
    }
    override fun getLayoutRes(): Int {
        return R.layout.fragment_genre_detail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            reset()
            popBackStack()
        }
        viewModel._genreInfo.value=null
        showProgressFrame()
        initTabLayout()
        setObservers()
        genre?.let { viewModel.getGenreInfo(it) }
    }

    private fun setObservers() {
        viewModel._genreInfo.observe(viewLifecycleOwner) {
            genreDesc = it
            initViews()
        }
    }

    private fun initViews() {
        if(genreDesc!=null){
            tvGenreTitle.text = genreDesc?.name?.capitalize(Locale.ROOT)?:""
            tvGenreDesc.text = genreDesc?.wiki?.summary?:""
            hideProgressFrame()
        }
    }

    private fun showProgressFrame() {
        clDetail.makeGone()
        pfDetail.makeVisible()
    }

    private fun hideProgressFrame() {
        clDetail.makeVisible()
        pfDetail.makeGone()
    }

    private fun initTabLayout() {
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = "Albums"
                1 -> tab.text = "Artists"
                2 -> tab.text = "Tracks"
            }
        }.attach()
    }

    private fun reset() {
        viewModel.setGenre(null)
    }

    override fun onClick(v: View?) {

    }

}