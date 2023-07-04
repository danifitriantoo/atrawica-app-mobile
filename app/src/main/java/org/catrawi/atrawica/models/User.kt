package org.catrawi.atrawica.models

import com.google.gson.annotations.SerializedName

data class Meta (
    @SerializedName("meta")
    var meta: AccessCredetial)

data class AccessCredetial (
    @SerializedName("status")
    var status: String,

    @SerializedName("code")
    var code: Int,

    @SerializedName("token")
    var authToken: String,

    @SerializedName("message")
    var message: String)

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

    @SerializedName("keypass")
    var keypass: String
)