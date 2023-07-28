package org.catrawi.atrawica.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_place")
data class FavoritePlace(
    @PrimaryKey
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("price")
    var price: Int,

    @SerializedName("image")
    var image: String
)
