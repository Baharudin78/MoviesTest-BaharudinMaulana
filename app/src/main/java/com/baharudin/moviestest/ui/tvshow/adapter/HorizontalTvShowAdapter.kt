package com.baharudin.moviestest.ui.tvshow.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.moviestest.BuildConfig.imageUrl
import com.baharudin.moviestest.data.model.tvshow.DataTvShow
import com.baharudin.moviestest.databinding.AdapterHorizontalTvBinding
import com.baharudin.moviestest.utils.Utils
import com.bumptech.glide.Glide

class HorizontalTvShowAdapter (
    private val showDetail: (DataTvShow) -> Unit
) : RecyclerView.Adapter<HorizontalTvShowAdapter.ViewHolder>() {

    private var data = ArrayList<DataTvShow>()

    fun setData(movieList: List<DataTvShow>?) {
        if (movieList == null) return
        data.clear()
        data.addAll(movieList)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            val newRating = Utils.nomalizeRating(data[position].vote_average!!.toFloat())
            tvTitle.text = data[position].name
            rating.rating = newRating

            holder.itemView.also {
                Glide.with(it.context)
                    .load(imageUrl + data[position].poster_path)
                    .into(imgPoster)
            }

            root.setOnClickListener {
                showDetail(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterHorizontalTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterHorizontalTvBinding) : RecyclerView.ViewHolder(view.root)

}