package org.catrawi.atrawica.services.api

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.auth0.android.jwt.JWT
import org.catrawi.atrawica.R
import org.catrawi.atrawica.models.PayloadDataResponse

object SessionManager {

    const val USER_TOKEN = "user_token"
    const val USER_ID = "user_id"
    const val USER_NAME = "user_name"
    const val USER_PHONE = "user_phone"
    const val USER_EMAIL = "user_email"
    const val USER_BUDGET = "user_budget"

    fun saveAuthToken(context: Context, token: String) {
        decodeToken(context,token)
        saveString(context, USER_TOKEN, token)
    }

    fun getToken(context: Context): String? {
        return getString(context, USER_TOKEN)
    }

    fun getPayloadData(context: Context): PayloadDataResponse {
        return getPayload(context)
    }

    fun saveString(context: Context, key: String, value: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, "Bearer $value")
        editor.apply()

    }

    fun saveBookingId(context: Context, key: String, value: Int) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()

    }

    fun saveTicketId(context: Context, key: String, value: Int) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()

    }

    fun saveTicketPrice(context: Context, key: String, value: Int) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()

    }

    fun getBookingId(context: Context,key: String) :Int {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getInt(key, 0)
    }

    fun getTicketId(context: Context,key: String) :Int {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getInt(key, 0)
    }

    fun getTicketPrice(context: Context,key: String) :Int {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getInt(key, 0)
    }

    fun getString(context: Context, key: String): String? {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getString(key, null)
    }

    fun getPayload(context: Context): PayloadDataResponse {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

        val payload = PayloadDataResponse(
            prefs.getInt(this.USER_ID, 0),
            prefs.getString(this.USER_NAME, null),
            prefs.getString(this.USER_PHONE, null),
            prefs.getInt(this.USER_BUDGET, 0),
            prefs.getString(this.USER_EMAIL, null),
        )

        Log.d("Click Load Data","Clicked!")
        Log.d("Click Load Data",payload.toString())

        return payload
    }

    fun updateBudget(context: Context,budget: Int) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt(this.USER_BUDGET, budget)
        editor.apply()
    }

    fun clearData(context: Context){
        val editor = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }

    private fun decodeToken(context: Context, jwt: String) {
        val jwt = JWT(jwt)

        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()

        editor.putInt(USER_ID, jwt.getClaim("id").asInt()!!)
        editor.putString(USER_NAME,jwt.getClaim("unique_name").asString())
        editor.putString(USER_EMAIL,jwt.getClaim("email").asString())
        editor.putString(USER_PHONE,jwt.getClaim("no_hp").asString())
        editor.putInt(USER_BUDGET, jwt.getClaim("budget").asInt()!!)

        editor.apply()

    }

}