package org.catrawi.atrawica.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.catrawi.atrawica.adapters.interfaces.ItemListener
import org.catrawi.atrawica.databinding.ItemPlaceBinding
import org.catrawi.atrawica.models.Place

class PlaceAdapter : RecyclerView.Adapter<PlaceViewHolder>() {

    var places = mutableListOf<Place>()
    var listener: ItemListener? = null

    fun setPlaceList(places: List<Place>) {
        this.places = places.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemPlaceBinding.inflate(inflater, parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = places[position]
        holder.binding.placeNameTextView.text = place.name
        holder.binding.descriptionTextView.text = place.description
        holder.binding.priceTextView.text = "IDR ${place.price}"

        holder.binding.card.setOnClickListener {
            listener?.onItemClicked(it,places[position])
        }

    }

    override fun getItemCount(): Int {
        return places.size
    }
}

class PlaceViewHolder(val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root)