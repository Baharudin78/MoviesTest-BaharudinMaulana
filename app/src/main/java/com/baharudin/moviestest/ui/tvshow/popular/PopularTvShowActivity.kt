package com.baharudin.moviestest.ui.tvshow.popular

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.baharudin.moviestest.R
import com.baharudin.moviestest.data.model.tvshow.DataTvShow
import com.baharudin.moviestest.databinding.ActivityPopularTvShowBinding
import com.baharudin.moviestest.databinding.BottomSheetBinding
import com.baharudin.moviestest.state.TvShowState
import com.baharudin.moviestest.ui.tvshow.adapter.AllTvShowAdapter
import com.baharudin.moviestest.ui.tvshow.detail.DetailTvShowActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularTvShowActivity : AppCompatActivity() {

    private val binding : ActivityPopularTvShowBinding by lazy {
        ActivityPopularTvShowBinding.inflate(layoutInflater)
    }

    private val viewModel : PopularTvShowViewModel by viewModels()

    private val adapter : AllTvShowAdapter by lazy {
        AllTvShowAdapter{ item -> detailTvShow(item)}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        setupData()
        setupViewModel()
        setupStatusBar()
    }

    private fun setupView() {
        with(binding) {
            rvTvShow.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(this@PopularTvShowActivity, 3)
                it.setHasFixedSize(true)
            }

            imgBack.setOnClickListener { finish() }
        }
    }

    private fun setupData() {
        viewModel.getPopularTvShow()
    }

    private fun setupViewModel() {
        viewModel.state.observe(this) {
            when (it) {
                is TvShowState.Loading -> getLoadingPopular(true)
                is TvShowState.Result -> getLoadingPopular(false)
                is TvShowState.Error -> showError()
            }
        }
        viewModel.data.observe(this, Observer(adapter::submitList))
    }

    private fun getLoadingPopular(loading: Boolean) {
        with(binding) {
            if (loading) {
                rvTvShow.visibility = View.INVISIBLE
                shTvShow.visibility = View.VISIBLE
            }else {
                rvTvShow.visibility = View.VISIBLE
                shTvShow.visibility = View.INVISIBLE
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

    private fun detailTvShow(item: DataTvShow) {
        startActivity(Intent(this, DetailTvShowActivity::class.java).also {
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