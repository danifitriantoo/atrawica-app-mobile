package org.catrawi.atrawica.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.catrawi.atrawica.adapters.interfaces.TicketListener
import org.catrawi.atrawica.databinding.ItemTicketBinding
import org.catrawi.atrawica.models.BookingResponse

class HistoryAdapter : RecyclerView.Adapter<HistoryViewHolder>() {

    var bookings = mutableListOf<BookingResponse>()
    var listener: TicketListener? = null

    fun setHistoryList(bookings: List<BookingResponse>) {
        this.bookings = bookings.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemTicketBinding.inflate(inflater, parent, false)
        return HistoryViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val booking = bookings[position]
        holder.binding.tvPlaceName.text = booking.place.name
        holder.binding.tvDate.text = booking.date
        holder.binding.tvQty.text = "${booking.qty} Person"

        holder.binding.cvTicket.setOnClickListener {
            listener?.onTicketClicked(it,bookings[position])
        }

    }

    override fun getItemCount(): Int {
        return bookings.size
    }
}

class HistoryViewHolder(val binding: ItemTicketBinding) : RecyclerView.ViewHolder(binding.root)