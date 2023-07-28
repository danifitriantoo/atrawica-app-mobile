package org.catrawi.atrawica.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.catrawi.atrawica.models.Place
import org.catrawi.atrawica.room.FavoriteDatabase
import org.catrawi.atrawica.room.FavoritePlace
import org.catrawi.atrawica.room.FavoritePlaceDao
import org.catrawi.atrawica.viewmodels.repository.DetailRepository
import org.catrawi.atrawica.viewmodels.repository.HomeRepository

class DetailViewModel (
    application: Application,
    private val  repository: DetailRepository
) : AndroidViewModel(application) {

    private var favoriteDao: FavoritePlaceDao?
    private var favoriteDb : FavoriteDatabase?
    init {
        favoriteDb = FavoriteDatabase.getDatabase(application)
        favoriteDao = favoriteDb?.favoritePlaceDao()
    }

    fun addToFavorite(data: Place) {
        CoroutineScope(Dispatchers.IO).launch {
            var favorite = FavoritePlace(data.id,data.name,data.description,data.price,data.image)
            favoriteDao?.addToFavorite(favorite)
        }
    }
}