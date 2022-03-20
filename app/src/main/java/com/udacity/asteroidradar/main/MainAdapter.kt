package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.ItemAsteroidBinding
import com.udacity.asteroidradar.domain.Asteroid

class MainAdapter(val callback: AsteroidClick) : RecyclerView.Adapter<AsteroidViewHolder>() {

    var asteroids: List<Asteroid> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val binding: ItemAsteroidBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AsteroidViewHolder.LAYOUT,
            parent,
            false
        )
        return AsteroidViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        holder.binding.also {
            it.asteroid = asteroids[position]
            it.asteroidCallback = callback
        }
    }

    override fun getItemCount() = asteroids.size
}

class AsteroidViewHolder(val binding: ItemAsteroidBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.item_asteroid
    }
}

class AsteroidClick(val block: (Asteroid) -> Unit) {
    fun onClick(asteroid: Asteroid) = block(asteroid)
}