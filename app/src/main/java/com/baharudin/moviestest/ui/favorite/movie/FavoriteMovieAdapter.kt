package com.baharudin.moviestest.ui.favorite.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.moviestest.BuildConfig
import com.baharudin.moviestest.R
import com.baharudin.moviestest.data.database.model.MovieEntity
import com.baharudin.moviestest.databinding.AdapterMovieBinding
import com.baharudin.moviestest.utils.Constant.Genres
import com.baharudin.moviestest.utils.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class FavoriteMovieAdapter (
    private val showDetail: (MovieEntity) -> Unit
) : PagedListAdapter<MovieEntity, FavoriteMovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            tvTitle.text = getItem(position)?.title

            tvRating.text = getItem(position)?.vote_average.toString()

            tvRelease.text = getItem(position)?.release_date?.let {
                Utils.dateFormat(
                    it,
                    "yyyy-mm-dd",
                    "yyyy"
                )
            }
            if(!getItem(position)?.genre.isNullOrEmpty()) {
                tvGenres.text = getItem(position)?.genre?.let { Genres.getValue(it.toInt()) }
            } else {
                tvGenres.visibility = View.GONE
            }

            holder.itemView.also {
                Glide.with(it.context)
                    .load( BuildConfig.imageUrl + getItem(position)?.poster_path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgPoster)

                tvPopularity.text = getItem(position)?.popularity.toString() +
                        it.context.getString(R.string.title_viewers)
            }

            root.setOnClickListener {
                getItem(position)?.let { it1 -> showDetail(it1) }
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<MovieEntity>(){
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
    class ViewHolder(val view: AdapterMovieBinding) : RecyclerView.ViewHolder(view.root)
}