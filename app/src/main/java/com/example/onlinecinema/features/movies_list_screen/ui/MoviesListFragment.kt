package com.example.onlinecinema.features.movies_list_screen.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.onlinecinema.R
import com.example.onlinecinema.features.movies_card_screen.ui.MoviesCardFragment
import com.example.onlinecinema.features.movies_list_screen.ui.adapter.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private val moviesListViewModel by viewModel<MoviesListViewModel>()
    private val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(movies = emptyList()) {
            moviesListViewModel.processUiEvent(UiEvent.OnCardClick(it))
        }
    }

    private lateinit var pbLoadingView: ProgressBar
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.vpMovies)
        viewPager.adapter = moviesAdapter

        moviesListViewModel.viewState.observe(viewLifecycleOwner, ::render)
        moviesListViewModel.singleLiveEvent.observe(viewLifecycleOwner, ::onSingleEvent)


        pbLoadingView = view.findViewById(R.id.pbLoading)

    }

    private fun render(viewState: ViewState) {
        moviesAdapter.updateMovies(viewState.movies)
        pbLoadingView.isVisible = viewState.isLoading
    }

    private fun onSingleEvent(event: SingleEvent) {
        when (event) {
            is SingleEvent.OpenMovieCard -> {
                Log.d("", "CLICKED")
                parentFragmentManager.beginTransaction()
                    .replace(android.R.id.content, MoviesCardFragment.newInstance(event.movie))
                    .addToBackStack("movies")
                    .commit()
            }
        }
    }

}