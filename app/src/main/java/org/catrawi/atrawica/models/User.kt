package org.catrawi.atrawica.models

import com.google.gson.annotations.SerializedName

data class Meta (
    @SerializedName("status")
    var status: String,

    @SerializedName("code")
    var code: Int,

    @SerializedName("data")
    var data: AccessCredetial,
)

data class MetaRegister (
    @SerializedName("status")
    var status: String,

    @SerializedName("code")
    var code: Int,

    @SerializedName("data")
    var data: User,
)

data class AccessCredetial (

    @SerializedName("token")
    var authToken: String
)

data class Credential (
    @SerializedName("email")
    var email: String,

    @SerializedName("keypass")
    var keypass: String
)

data class User (
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("noHp")
    var noHp: String,

    @SerializedName("keypass")
    var keypass: String,

    @SerializedName("budget")
    var budget: Int
)