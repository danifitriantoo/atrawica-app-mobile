package org.catrawi.atrawica.adapters.interfaces

import android.view.View
import org.catrawi.atrawica.models.BookingResponse
import org.catrawi.atrawica.models.Place

interface ItemListener {
    fun onItemClicked(view: View, data: Place)
}

interface TicketListener {
    fun onTicketClicked(view: View, data: BookingResponse)
}