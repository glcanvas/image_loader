package com.image_loader.nikita.image_loader.services

import android.app.IntentService
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.image_loader.nikita.image_loader.DetailFragment
import java.io.IOException
import java.net.URL

class BackgroundImageLoad : IntentService("LoadImageService") {
    override fun onHandleIntent(intent: Intent?) {
        val url = intent?.getStringExtra("full_back_url")
        val imageUrl = URL(url)
        val broadcastIntent = Intent()
        broadcastIntent.action = DetailFragment.PROCESS_RESPONSE
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT)

        try {
            val inputStream = imageUrl.openConnection().getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val resultBitmap = Bitmap.createScaledBitmap(bitmap, 256, 256, false)
            broadcastIntent.putExtra(DetailFragment.PARAM_STATUS, "ok")
            broadcastIntent.putExtra(DetailFragment.PARAM_RESULT, resultBitmap)
            sendBroadcast(broadcastIntent)
        } catch (e: IOException) {
            broadcastIntent.putExtra(DetailFragment.PARAM_STATUS, "fail")
            sendBroadcast(broadcastIntent)
            Log.e("image_loader", e.toString())
        } catch (e: IllegalArgumentException) {
            broadcastIntent.putExtra(DetailFragment.PARAM_STATUS, "fail")
            sendBroadcast(broadcastIntent)
            Log.e("image_loader", e.toString())
        }
    }
}