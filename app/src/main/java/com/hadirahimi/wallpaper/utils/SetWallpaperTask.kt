package com.hadirahimi.wallpaper.utils

import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class SetWallpaperTask(private val url: String, private val wallpaperManager: WallpaperManager) {

    suspend fun execute() :Boolean{
        var changed = false
        withContext(Dispatchers.IO) {
            try {
                val inputStream = URL(url).openStream()
                BitmapFactory.decodeStream(inputStream)?.let { bitmap ->
                    wallpaperManager.setBitmap(bitmap)
                    Log.e("HECTOR" , "S U C C E S S")
                    changed = true
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("HECTOR" , "E R R O R ")
                changed = false
            }
        }
        return changed
    }
}