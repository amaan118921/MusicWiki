package com.example.musicwiki.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import com.example.musicwiki.R
import com.example.musicwiki.adapters.GenreAdapter
import com.example.musicwiki.eventBus.GenreEvent
import com.example.musicwiki.eventBus.MessageEvent
import com.example.musicwiki.helper.Constants
import com.example.musicwiki.helper.makeGone
import com.example.musicwiki.helper.makeVisible
import com.example.musicwiki.model.Genres
import com.example.musicwiki.viewModel.MusicViewModel
import kotlinx.android.synthetic.main.fragment_select_genre.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class SelectGenreFragment: BaseFragment(), GenreAdapter.IAdapter {
    private val viewModel: MusicViewModel by activityViewModels()
    private var adapter: GenreAdapter?  = null
    private var genreList: List<Genres>? = null
    private var expanded = false
    override fun getLayoutRes(): Int {
       return R.layout.fragment_select_genre
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            finish()
        }
        showProgressFrame()
        initRecyclerview()
        setObservers()
        viewModel.getGenres()
        ivExpand.setOnClickListener(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent?) {
        when(event?.status) {
            Constants.ARTIST_DETAIL -> {
                val ev = event as GenreEvent
                goToDetailGenre(ev.genre)
            }
        }
    }
    private fun showProgressFrame() {
        cl.makeVisible()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerview() {
        adapter = GenreAdapter(this, 0)
        rvStarting.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    private fun setObservers() {
        viewModel.genres.observe(viewLifecycleOwner) {
            genreList = it
            collapseList()
            hideProgressFrame()
        }
    }

    private fun hideProgressFrame() {
        cl.makeGone()
    }


    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.ivExpand -> {
                if(expanded) {
                    collapseList()
                }
                else expandList()
            }
        }
    }

    private fun collapseList() {
        ivExpand.setImageDrawable(resources.getDrawable(R.drawable.down))
        adapter?.bindList(genreList?.take(10))
        expanded = false
    }

    private fun expandList() {
        ivExpand.setImageDrawable(resources.getDrawable(R.drawable.up))
        adapter?.bindList(genreList)
        expanded = true
    }

    override fun onItemClick(genre: String) {
        goToDetailGenre(genre)
    }

    private fun goToDetailGenre(genre: String) {
        Bundle().apply {
            putString(Constants.GENRE, genre)
            addFragment(Constants.DETAIL_GENRE, this)
            viewModel.setGenre(genre)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}

