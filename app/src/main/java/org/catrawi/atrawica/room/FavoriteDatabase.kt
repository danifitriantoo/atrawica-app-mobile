package org.catrawi.atrawica.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavoritePlace::class],
    version = 1
)
 abstract class FavoriteDatabase: RoomDatabase() {
    //untuk bisa akses INSTANCE secara langsung
    companion object{
        var INSTANCE : FavoriteDatabase? = null

        //function untuk mendapatkan INSTANCE nya
        fun getDatabase(context: Context): FavoriteDatabase?{
            if (INSTANCE==null){
                synchronized(FavoriteDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, FavoriteDatabase::class.java, "favorite_place").build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun favoritePlaceDao(): FavoritePlaceDao
}