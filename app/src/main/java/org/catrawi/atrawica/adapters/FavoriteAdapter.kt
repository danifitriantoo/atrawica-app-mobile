package org.catrawi.atrawica.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.catrawi.atrawica.adapters.interfaces.ItemListener
import org.catrawi.atrawica.databinding.ItemFavoriteBinding
import org.catrawi.atrawica.models.Place

class FavoriteAdapter : RecyclerView.Adapter<FavoriteViewHolder>() {

    var favorites = mutableListOf<Place>()
    var listener: ItemListener? = null

    fun setPlaceList(favorites: List<Place>) {
        this.favorites = favorites.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemFavoriteBinding.inflate(inflater, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = favorites[position]
        holder.binding.tvPlaceName.text = favorite.name
        holder.binding.tvPlacePrice.text = "IDR ${favorite.price}"

        holder.binding.cvFav.setOnClickListener {
            listener?.onItemClicked(it,favorites[position])
        }

    }

    override fun getItemCount(): Int {
        return favorites.size
    }
}

class FavoriteViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)