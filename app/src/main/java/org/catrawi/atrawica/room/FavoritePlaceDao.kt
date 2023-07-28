package org.catrawi.atrawica.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritePlaceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    //membuat suspend fun karena dieksekusi di background
    fun addToFavorite(favoritePlace: FavoritePlace)

    //query untuk menampilkan list dan mengembalikan livedata
    @Query("SELECT * FROM favorite_place")
    fun getFavoritePlace(): List<FavoritePlace>

    //mengecek apakah place sudah ditambahkan ke dalam favorite place
    @Query("SELECT count(*) FROM favorite_place WHERE id = :id")
    fun checkPlace(id: Int): Int

    //menghapus place dari daftar favorite
    @Query("DELETE FROM favorite_place WHERE id = :id")
    fun removeFromFavorite(id: Int): Int
}