package com.lukitor.projectandroidjetpackpro1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lukitor.projectandroidjetpackpro1.data.source.local.entity.MovieEntitiy
import java.util.ArrayList

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>() {
    private var data = ArrayList<MovieEntitiy>()
    fun setFavorite(movie: List<MovieEntitiy>?){
        if (movie == null) return
        this.data.clear()
        this.data.addAll(movie)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_cardview,
            parent,
            false
        )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(data[position])
    }
    override fun getItemCount(): Int {return data.size}

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var gambar: ImageView = itemView.findViewById(R.id.gambarcard)
        private var judul: TextView = itemView.findViewById(R.id.txtJudul)
        private var tipe: TextView = itemView.findViewById(R.id.txtTipe)
        fun bind(movie: MovieEntitiy){
            judul.text= movie.judul
            tipe.text= movie.type
            Glide.with(itemView.context).load(movie.image).into(gambar)
            itemView.setOnClickListener{
                val intent= Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("data",movie.judul)
                itemView.context.startActivity(intent)
            }
        }
    }
}