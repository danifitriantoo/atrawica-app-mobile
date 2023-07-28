package org.catrawi.atrawica.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.catrawi.atrawica.models.Favorite
import org.catrawi.atrawica.models.Place
import org.catrawi.atrawica.models.PlaceMeta
import org.catrawi.atrawica.room.FavoriteDatabase
import org.catrawi.atrawica.room.FavoritePlace
import org.catrawi.atrawica.room.FavoritePlaceDao
import org.catrawi.atrawica.viewmodels.repository.AuthRepository
import org.catrawi.atrawica.viewmodels.repository.HomeRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    application: Application,
    private val  repository: HomeRepository
    ) : AndroidViewModel(application) {


    val responseData = MutableLiveData<ArrayList<Place>>()
    val getData = MutableLiveData<List<FavoritePlace>>()
    val errorLog = MutableLiveData<String>()

    private var favoriteDao: FavoritePlaceDao?
    private var favoriteDb : FavoriteDatabase?
    init {
        favoriteDb = FavoriteDatabase.getDatabase(application)
        favoriteDao = favoriteDb?.favoritePlaceDao()
    }

    fun getAllPlace(token:String) {

        val response = repository.getAllPlace(token)

        response.enqueue(object: Callback<PlaceMeta> {
            override fun onResponse(call: Call<PlaceMeta>, response: Response<PlaceMeta>) {

                when (response.code()){
                    200 -> responseData.postValue(response.body()?.data)
                    401 -> errorLog.postValue("Unauthorized")
                    404 -> errorLog.postValue("Server Error")
                }

            }
            override fun onFailure(call: Call<PlaceMeta>, t: Throwable) {
                errorLog.postValue(t.message)
                TODO("Not yet implemented")
            }

        })
    }

    fun getPlaces(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            getData.postValue(favoriteDao?.getFavoritePlace())
        }

    }

    fun addToFavorite(data: Place) {
        CoroutineScope(Dispatchers.IO).launch {
            val favorite = FavoritePlace(data.id,data.name,data.description,data.price,data.image)
            favoriteDao?.addToFavorite(favorite)
        }
    }
    fun checkFavorite(id: Int) {
        Log.e("check data", favoriteDao?.checkPlace(id).toString())
        Log.e("check data", favoriteDao?.getFavoritePlace().toString())
        Log.e("check data", " is Clicked!")


    }

    fun removeFromFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            favoriteDao?.removeFromFavorite(id)
        }
    }


}