package com.coding.baxta.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object ConnectionUtil {
    fun Context.isConnected(): Boolean {
        var result = false
        val connectivityManager: ConnectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val hasNetworkCapabilities = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(hasNetworkCapabilities) ?: return false
            result = when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    true
                }
                else -> {
                    false
                }
            }
        } else {
            connectivityManager.run {
                this.activeNetworkInfo?.run {
                    result = when (this.type) {
                        ConnectivityManager.TYPE_MOBILE,
                        ConnectivityManager.TYPE_WIFI,
                        ConnectivityManager.TYPE_ETHERNET -> {
                            true
                        }
                        else -> {
                            false
                        }
                    }
                }
            }
        }
        return result
    }
}