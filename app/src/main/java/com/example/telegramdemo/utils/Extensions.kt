package com.example.telegramdemo.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

fun isOnline(context: Context): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
    return true
}

fun ConstraintLayout.show() {
    this.visibility = View.VISIBLE
}

fun ConstraintLayout.hide() {
    this.visibility = View.INVISIBLE
}

fun ConstraintLayout.gone() {
    this.visibility = View.GONE
}

/**
 * Time like HH:mm
 */
@SuppressLint("SimpleDateFormat")
fun toTimeFormat(currentTimeMillis: Long): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDateTime.now(ZoneId.ofOffset("GMT", ZoneOffset.ofHours(5/*gmtOffset*/)))
            .format(DateTimeFormatter.ofPattern("HH:mm"))
    } else {
        SimpleDateFormat("HH:mm").format(Date(currentTimeMillis))
    }
}

fun getDeviceName(): String {
    val manufacturer = Build.MANUFACTURER
    val model = Build.MODEL

    return if (model.startsWith(manufacturer)) {
        model
    } else {
        "$manufacturer $model"
    }
}
