package org.catrawi.atrawica.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.catrawi.atrawica.adapters.interfaces.FavListener
import org.catrawi.atrawica.adapters.interfaces.FavoriteListener
import org.catrawi.atrawica.adapters.interfaces.ItemListener
import org.catrawi.atrawica.databinding.ItemFavoriteBinding
import org.catrawi.atrawica.models.Place
import org.catrawi.atrawica.room.FavoritePlace

class FavoriteAdapter : RecyclerView.Adapter<FavoriteViewHolder>() {

    var favorites = mutableListOf<FavoritePlace>()
    var listener: FavListener? = null

    fun setPlaceList(favorites: List<FavoritePlace>) {
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

        Picasso.get().load(favorite.image).into(holder.binding.placeImageView)

        holder.binding.cvFav.setOnClickListener {
            listener?.onFavoriteClicked(it,favorites[position])
        }

    }

    override fun getItemCount(): Int {
        return favorites.size
    }
}

class FavoriteViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)