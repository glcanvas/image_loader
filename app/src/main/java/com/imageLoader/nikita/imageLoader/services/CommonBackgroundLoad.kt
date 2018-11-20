package com.imageLoader.nikita.imageLoader.services

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.imageLoader.nikita.imageLoader.utils.CommonData
import java.io.IOException
import java.net.URL

abstract class CommonBackgroundLoad : IntentService("PreviewImageLoad") {
    override fun onHandleIntent(intent: Intent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    protected fun loadFileFromInternet(imageUrl: URL, correctWay: String, broadcastIntent: Intent) {
        try {
            val inputStream = imageUrl.openConnection().getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val resultBitmap = Bitmap.createScaledBitmap(bitmap, 128, 128, false)
            broadcastIntent.putExtra(CommonData.PARAM_STATUS, "ok")
            broadcastIntent.putExtra(CommonData.PARAM_RESULT, resultBitmap)
            sendBroadcast(broadcastIntent)
            saveFileToStorage(correctWay, resultBitmap)
            Log.d("image_loader", "$correctWay load from internet")
        } catch (e: IOException) {
            broadcastIntent.putExtra(CommonData.PARAM_STATUS, "fail")
            sendBroadcast(broadcastIntent)
            Log.e("image_loader", e.toString())
        } catch (e: IllegalArgumentException) {
            broadcastIntent.putExtra(CommonData.PARAM_STATUS, "fail")
            sendBroadcast(broadcastIntent)
            Log.e("image_loader", e.toString())
        }
    }

    protected fun loadFileFromStorage(way: String, broadcastIntent: Intent) {
        try {

            val bitmap = BitmapFactory.decodeStream(openFileInput(way))
            broadcastIntent.putExtra(CommonData.PARAM_STATUS, "ok")
            broadcastIntent.putExtra(CommonData.PARAM_RESULT, bitmap)
            sendBroadcast(broadcastIntent)
            Log.d("image_loader", "file $way load")
        } catch (e: IOException) {
            broadcastIntent.putExtra(CommonData.PARAM_STATUS, "fail")
            sendBroadcast(broadcastIntent)
            Log.e("image_loader", e.toString())
        }
    }

    private fun saveFileToStorage(way: String, bitmap: Bitmap) {
        val fos = openFileOutput(way, Context.MODE_PRIVATE)
        fos.use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        }
        Log.d("image_loader", "file $way save")
    }

    protected  fun fileExist(way: String): Boolean {
        val file = baseContext.getFileStreamPath(way)
        return file.exists()
    }
}