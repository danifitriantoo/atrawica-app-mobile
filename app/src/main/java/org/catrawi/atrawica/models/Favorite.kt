package org.catrawi.atrawica.models

import com.google.gson.annotations.SerializedName

/* buat class data untuk metaFavorite */
data class MetaFavorite(
    @SerializedName("status")
    var status: String,

    @SerializedName("code")
    var code: Int,

    @SerializedName("data")
    var data: ArrayList<Favorite>
)

/* lengkapi class data untuk favorite */
data class Favorite (
    @SerializedName("id")
    var id: Int,

    /* lanjutkan untuk object yang lain */
    @SerializedName("userId")
    var userId: Int,

    @SerializedName("placeId")
    var placeId: Int,
    )
