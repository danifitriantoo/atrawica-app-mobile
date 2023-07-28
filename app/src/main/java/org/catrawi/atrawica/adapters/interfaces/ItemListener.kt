package org.catrawi.atrawica.adapters.interfaces

import android.view.View
import android.widget.CompoundButton
import org.catrawi.atrawica.models.BookingResponse
import org.catrawi.atrawica.models.Place
import org.catrawi.atrawica.room.FavoritePlace

interface ItemListener {
    fun onItemClicked(view: View, data: Place)
}

interface TicketListener {
    fun onTicketClicked(view: View, data: BookingResponse)
}

interface FavoriteListener {
    fun onToggleChanged(view : CompoundButton, data: Place)

}

interface FavListener {
    fun onFavoriteClicked(view: View, data: FavoritePlace)
}