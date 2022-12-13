package com.baharudin.moviestest.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.moviestest.data.model.movie.DataMovie
import com.baharudin.moviestest.databinding.BottomSheetBinding
import com.baharudin.moviestest.databinding.FragmentMovieBinding
import com.baharudin.moviestest.state.MovieState
import com.baharudin.moviestest.ui.favorite.FavoriteActivity
import com.baharudin.moviestest.ui.movie.adapter.HorizontalMovieAdapter
import com.baharudin.moviestest.ui.movie.adapter.VerticalMovieAdapter
import com.baharudin.moviestest.ui.movie.detail.DetailMovieActivity
import com.baharudin.moviestest.ui.movie.popular.PopularMovieActivity
import com.baharudin.moviestest.ui.movie.toprated.TopRatedMovieActivity
import com.baharudin.moviestest.ui.movie.upcoming.UpcomingMovieActivity
import com.baharudin.moviestest.ui.search.movie.SearchMovieActivity
import com.baharudin.moviestest.utils.Utils.delay
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels()

    private val binding : FragmentMovieBinding by lazy {
        FragmentMovieBinding.inflate(layoutInflater)
    }

    private val upcomingAdapter: HorizontalMovieAdapter by lazy {
        HorizontalMovieAdapter{ item -> detailMovie(item)}
    }

    private val topRatedAdapter: HorizontalMovieAdapter by lazy {
        HorizontalMovieAdapter{ item -> detailMovie(item)}
    }

    private val popularAdapter: VerticalMovieAdapter by lazy {
        VerticalMovieAdapter{ item -> detailMovie(item)}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        delay()
        setupView()
        setupViewModel()
        setupData()
        setupListener()
    }

    private fun setupData() {
        viewModel.getUpcomingMovie()
        viewModel.getPopularMovie()
        viewModel.getTopRatedMovie()
    }

    private fun setupViewModel() {
        viewModel.stateUpcoming.observe(viewLifecycleOwner) {
            when (it) {
                is MovieState.Loading -> getLoadingUpcoming(true)
                is MovieState.Result -> successGetDataUpComing(it.data.data)
                is MovieState.Error -> showError()
            }
        }

        viewModel.stateTopRated.observe(viewLifecycleOwner) {
            when (it) {
                is MovieState.Loading -> getLoadingPopular(true)
                is MovieState.Result -> successGetDataTopRated(it.data.data)
                is MovieState.Error -> showError()
            }
        }

        viewModel.statePopular.observe(viewLifecycleOwner) {
            when (it) {
                is MovieState.Loading -> getLoadingPopular(true)
                is MovieState.Result -> successGetDataPopular(it.data.data)
                is MovieState.Error -> showError()
            }
        }
    }

    private fun setupView() {
        with(binding) {
            rvUpcoming.also {
                it.adapter = upcomingAdapter
                it.layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.HORIZONTAL ,false)
                it.setHasFixedSize(true)
            }

            rvTopRated.also {
                it.adapter = topRatedAdapter
                it.layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.HORIZONTAL ,false)
                it.setHasFixedSize(true)
            }

            rvPopular.also {
                it.adapter = popularAdapter
                it.layoutManager = GridLayoutManager(requireContext(), 1)
                it.setHasFixedSize(true)
            }
        }
    }

    private fun successGetDataUpComing(data : List<DataMovie>) {
        getLoadingUpcoming(false)
        upcomingAdapter.setData(data)
    }

    private fun successGetDataTopRated(data : List<DataMovie>) {
        getLoadingTopRated(false)
        topRatedAdapter.setData(data)
    }

    private fun successGetDataPopular(data : List<DataMovie>) {
        getLoadingPopular(false)
        popularAdapter.setData(data)
    }

    private fun getLoadingUpcoming(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvUpcoming.visibility = View.INVISIBLE
                shUpcoming.visibility = View.VISIBLE
            }else {
                rvUpcoming.visibility = View.VISIBLE
                shUpcoming.visibility = View.INVISIBLE
            }
        }
    }

    private fun getLoadingTopRated(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvTopRated.visibility = View.INVISIBLE
                shTopRated.visibility = View.VISIBLE
            }else {
                rvTopRated.visibility = View.VISIBLE
                shTopRated.visibility = View.INVISIBLE
            }
        }
    }

    private fun getLoadingPopular(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvPopular.visibility = View.INVISIBLE
                shPopular.visibility = View.VISIBLE
            }else {
                rvPopular.visibility = View.VISIBLE
                shPopular.visibility = View.INVISIBLE
            }
        }
    }

    private fun showError() {
        val binding = BottomSheetBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(binding.root)
        with(binding) {
            btnOk.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun detailMovie(item: DataMovie) {
        startActivity(Intent(requireContext(), DetailMovieActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

    private fun setupListener() {
        with(binding) {
            tvSeeUpcoming.setOnClickListener {
                startActivity(Intent(requireContext(), UpcomingMovieActivity::class.java))
            }

            tvSeeTopRated.setOnClickListener {
                startActivity(Intent(requireContext(), TopRatedMovieActivity::class.java))
            }

            tvSeePopular.setOnClickListener {
                startActivity(Intent(requireContext(), PopularMovieActivity::class.java))
            }

            search.setOnClickListener {
                startActivity(Intent(requireContext(), SearchMovieActivity::class.java))
            }

            imgFavorite.setOnClickListener {
                startActivity(Intent(requireContext(), FavoriteActivity::class.java))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}