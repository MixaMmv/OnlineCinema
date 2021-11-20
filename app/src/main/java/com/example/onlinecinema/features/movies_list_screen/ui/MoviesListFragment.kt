package com.example.onlinecinema.features.movies_list_screen.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinecinema.R
import com.example.onlinecinema.databinding.FragmentMoviesCardBinding
import com.example.onlinecinema.databinding.FragmentMoviesListBinding
import com.example.onlinecinema.features.movies_card_screen.ui.MoviesCardFragment
import com.example.onlinecinema.features.movies_list_screen.ui.adapter.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    companion object {
        fun newInstance() = MoviesListFragment()
    }

    private val viewBinding: FragmentMoviesListBinding by viewBinding(FragmentMoviesListBinding::bind)

    private val moviesListViewModel by viewModel<MoviesListViewModel>()
    private val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(movies = emptyList()) {
            moviesListViewModel.processUiEvent(UiEvent.OnCardClick(it))
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.vpMovies.adapter = moviesAdapter

        moviesListViewModel.viewState.observe(viewLifecycleOwner, ::render)
        moviesListViewModel.singleLiveEvent.observe(viewLifecycleOwner, ::onSingleEvent)
    }

    private fun render(viewState: ViewState) {
        moviesAdapter.updateMovies(viewState.movies)
        viewBinding.pbLoading.isVisible = viewState.isLoading

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