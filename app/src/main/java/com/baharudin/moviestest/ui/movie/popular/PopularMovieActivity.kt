package com.baharudin.moviestest.ui.movie.popular

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.moviestest.R
import com.baharudin.moviestest.data.model.movie.DataMovie
import com.baharudin.moviestest.databinding.ActivityPopularMovieBinding
import com.baharudin.moviestest.databinding.BottomSheetBinding
import com.baharudin.moviestest.state.MovieState
import com.baharudin.moviestest.ui.movie.adapter.AllMovieAdapter
import com.baharudin.moviestest.ui.movie.detail.DetailMovieActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMovieActivity : AppCompatActivity() {

    private val binding : ActivityPopularMovieBinding by lazy {
        ActivityPopularMovieBinding.inflate(layoutInflater)
    }

    private val viewModel : PopularMovieViewModel by viewModels()

    private val adapter : AllMovieAdapter by lazy {
        AllMovieAdapter { item -> detailMovie(item)}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupStatusBar()
        setupView()
        getData()
        setupViewModel()
    }

    private fun setupView() {
        with(binding) {
            rvMovie.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(
                    this@PopularMovieActivity, LinearLayoutManager.VERTICAL, false
                )
                it.setHasFixedSize(true)
            }

            imgBack.setOnClickListener { finish() }
        }
    }

    private fun getData() {
        viewModel.getMovie()
    }

    private fun setupViewModel() {
        viewModel.state.observe(this) {
            when (it) {
                is MovieState.Loading -> getLoadingUpcoming(true)
                is MovieState.Result -> getLoadingUpcoming(false)
                is MovieState.Error -> showError()
            }
        }
        viewModel.data.observe(this, Observer(adapter::submitList))
    }

    private fun getLoadingUpcoming(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvMovie.visibility = View.INVISIBLE
                shMovie.visibility = View.VISIBLE
            }else {
                rvMovie.visibility = View.VISIBLE
                shMovie.visibility = View.INVISIBLE
            }
        }
    }

    private fun showError() {
        val binding = BottomSheetBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(binding.root)
        with(binding) {
            btnOk.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun detailMovie(item: DataMovie) {
        startActivity(Intent(this, DetailMovieActivity::class.java).also {
            it.putExtra("data", item)
        })
    }

    private fun setupStatusBar() {
        with(window) {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }
}